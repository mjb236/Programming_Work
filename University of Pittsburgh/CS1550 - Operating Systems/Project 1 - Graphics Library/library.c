//Michael Bowen
//CS1550 Tues/Thurs 2:30pm  Recitation Fri 12:00pm
//
//Project 1 - graphics library

#include "iso_font.h"	//for printing letters
#include <unistd.h>		//read
#include <errno.h>		//error numbers
#include <sys/types.h>	//open etc
#include <sys/stat.h>	//open etd
#include <sys/ioctl.h>	//ioctl
#include <sys/mman.h>	//mmap and munmap
#include <sys/select.h> //select
#include <sys/time.h>	//time structures
#include <fcntl.h>		//open
#include <stdlib.h>		//exit - MAY NEED TO REMOVE THESE
#include <linux/fb.h>	
#include <termios.h>	//termios structure

typedef unsigned short color_t;

int fd;									//file descriptor for screen
int tFile;								//file descriptor for terminal
int xRes, yRes;							//resolutions for x and y	
int pixelSize, mapSize;					//size of pixel in bits
int result;								//error checking variable
struct fb_var_screeninfo vScreen;		//struct for virtual resolution
struct fb_fix_screeninfo fScreen;		//struct to determine bit depth
color_t * screenMap;					//map of pixels on screen
struct termios terminal;				//struct for terminal settings

//Initializes the graphics for use.
void init_graphics()
{
	//open the framebuffer and ensure it was opened correctly
	fd = open("/dev/fb0", O_RDWR);
	if(fd == EACCES)
	{
		return;
	}
	
	//get vScreen and the x/y resolutions
	result = ioctl(fd, FBIOGET_VSCREENINFO, &vScreen);
	if(result == -1)
	{
		return;
	}	
	xRes = vScreen.xres;
	yRes = vScreen.yres;
	
	//get fScreen and figure out the mapSize
	result = ioctl(fd, FBIOGET_FSCREENINFO, &fScreen);
	if(result == -1)
	{
		return;
	}	
	mapSize = vScreen.yres_virtual * fScreen.line_length;
	pixelSize = fScreen.line_length / xRes;
	
	//map the screen with mmap
	screenMap = (color_t *) mmap(0, mapSize, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
	
	//disable echo and canonical modes
	//open the terminal file
	result = ioctl(STDIN_FILENO, TCGETS, &terminal);
	if(result == -1)
	{
		return;
	}	
	terminal.c_lflag &= ~ICANON;			//disable canonical bit
	terminal.c_lflag &= ~ECHO;				//disable echo bit
	//set the terminal to the results
	result = ioctl(STDIN_FILENO, TCSETS, &terminal);
	if(result == -1)
	{
		return;
	}
}

//clear the screen
void clear_screen()
{
	write(STDOUT_FILENO, "\033[2J", 4);
}

//get the key press from the user
char getkey()
{
	//fd_set and timeval structures
	fd_set fSet;
	struct timeval tVal;
	
	//set up the fSet
	FD_ZERO(&fSet);
	FD_SET(0, &fSet);
	
	//set time out values to 0
	tVal.tv_sec = 0;
	tVal.tv_usec = 0;
	
	//use the select syscall to get data coming in
	result = select(1, &fSet, NULL, NULL, &tVal);
	if(result == -1)
	{
		return 'q';
	} 
	else if(result)
	{
		//there is data to read - read it
		char c;
		result = read(STDIN_FILENO, &c, 1);
		return c;
	}
	else
	{
		//no data to read - do nothing
	}
}

//make the system sleep for the given amount of milliseconds
void sleep_ms(long ms)
{
	struct timespec sleepTime;
	sleepTime.tv_sec = 0;
	sleepTime.tv_nsec = ms * 1000000;
	
	nanosleep(sleepTime, NULL);
}

//draw a pixel at coordinates (x,y) with color color
void draw_pixel(int x, int y, color_t color)
{
	//ensure that pixel doesn't draw to an invalid memory location
	int offset = y * ((fScreen.line_length) / pixelSize) + x;
	if(offset < (xRes * yRes) && offset > 0)
		*(screenMap + offset) = color;
}

//function that returns a color_t (16 bit color in form of 5 red, 6 green, 5 blue)
//when provided with RBG values
color_t getColor(unsigned short r, unsigned short g, unsigned short b)
{
	color_t color = 0x0000;
	r <<= 11;					//shift to red bits
	color |= r;				
	g <<= 6;					//shift to green bits
	color |= g;
	color |= b;					//blue bits do not require shift
	
	return color;
}

//function that draws a line from (x,y) of length length and color c.
//vertical is used to distinguish between vertical and horizontal lines.
//function not meant for diagonal lines.
void draw_line(int x, int y, int length, int vertical, color_t c)
{
	int i;
	
	for(i = 0; i < length; i++)
	{
		if(vertical)
			draw_pixel(x, y + i, c);
		else
			draw_pixel(x + i, y, c);
	}
}

//uses the draw_line function to draw an unfilled rectangle starting at 
//(x,y) and with specified width, height and color
void draw_rect(int x1, int y1, int width, int height, color_t c)
{
	if(width <= 0 || height <= 0)
		return;
	else
	{
		draw_line(x1, y1, width, 0, c);
		draw_line(x1, y1, height, 1, c);
		draw_line(x1 + width - 1, y1, height, 1, c);
		draw_line(x1, y1 + height - 1, width, 0, c);
	}
}

//uses the draw_line function to draw a filled rectangle starting at 
//(x,y) and with specified width, height and color
void fill_rect(int x1, int y1, int width, int height, color_t c)
{
	if(width <= 0 || height <= 0)
		return;
	else
	{
		int i;
		for(i = 0; i < width; i++)
			draw_line(x1 + i, y1, height, 1, c);
	}
}

//uses the iso_font array to draw the text specified starting at 
//(x,y) with the specified color c
void draw_text(int x, int y, const char * text, color_t c)
{
	unsigned char bits = 0x00;
	char ch;
	int i = 0, j, k;				//loop control variables
	
	while(*(text + i) != '\0')
	{
		ch = *(text + i);
		for(j = 0; j < ISO_CHAR_HEIGHT; j++)
		{
			bits = iso_font[(int)ch * 16 + j];
			for(k = 0; k <= 7; k++)
			{
				unsigned char pixel;
				
				//shift the bits down and & with 1 to determine if pixel should be drawn
				//starts at the least signifcant bit and works toward most significant
				pixel = (bits >> k);
				pixel &= 0x01;
				if(pixel)
				{
					draw_pixel((x + k), (y + j), c);
				}
			}
		}
		
		i++;
		x += 8;
	}	
}

//return the graphics to original state before exit
void exit_graphics()
{
	//reset canonical and echo to enabled
	terminal.c_lflag |= ICANON;
	terminal.c_lflag |= ECHO;
	result = ioctl(STDIN_FILENO, TCSETS, &terminal);
	
	//unmap memory and close files
	munmap(screenMap, mapSize);
	close(fd);
}
