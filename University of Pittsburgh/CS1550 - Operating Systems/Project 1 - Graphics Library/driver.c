//Michael Bowen
//CS1550 Tues/Thurs 2:30-3:45 Recitation Fri 12:00pm
//
//Project 1 - graphics library

typedef unsigned short color_t;

//function declarations from the graphics library
void init_graphics();
void clear_screen();
void exit_graphics();
char getkey();
void sleep_ms(long);
color_t getColor(unsigned short, unsigned short, unsigned short);
void draw_pixel(int, int, color_t);
void draw_line(int, int, int, int, color_t);
void draw_rect(int, int, int, int, color_t);
void fill_rect(int, int, int, int, color_t);

//function declarations from the driver file
void draw_mario(int, int);
void clear_mario(int, int);
void draw_header();
void draw_footer();
void set_colors();
void change_text(int, color_t);

//array of colors to be used
color_t colors[8]; 

int main(void)
{
	char key;				//current key press
	int i = 0, j = 1;		//color tracking variable and quote tracking variable
	int x = 300, y = 200;	//starting coordinates
	color_t c;				//current color
	
	//set up the screen
	init_graphics();	
	clear_screen();
	set_colors();	
	draw_mario(x, y);
	draw_header();
	draw_footer();
	
	//begin color with red
	c = colors[i++];
	
	do
	{
		key = getkey();
		if(key == 'a')
		{
			clear_mario(x, y);
			x -= 20;							//move mario left
			draw_mario(x, y);
		}
		else if(key == 's')
		{
			clear_mario(x, y);
			y += 30;							//move mario down
			draw_mario(x, y);
		}
		else if(key == 'd')
		{
			clear_mario(x, y);
			x += 20;							//move mario right
			draw_mario(x, y);
		}
		else if(key == 'w')
		{
			clear_mario(x, y);
			y -= 30;							//move mario up
			draw_mario(x, y);
		}
		else if(key == 'r')
		{
			draw_rect(x+52, y, 20, 30, c);		//draw a rectangle
		}
		else if(key == 'f')
		{
			fill_rect(x+52, y, 20, 30, c);		//draw a filled rectangle
		}
		else if(key == '/')
		{
			c = colors[i++];					//cycle colors
			if(i == 8)
				i = 0;
		}
		else if(key == ' ')
		{
			//reset counter if needed
			if(j == 8)
				j = 0;
			change_text(j++, c);				//change the quote in the header
		}
		sleep_ms(20);
	} while(key != 'q');
	
	exit_graphics();
	
	return 0;
}

//set up the color array with some basic colors
void set_colors()
{
	colors[0] = getColor(31, 0, 0);
	colors[1] = getColor(0, 63, 0);
	colors[2] = getColor(0, 0, 31);
	colors[3] = getColor(20, 40, 0);
	colors[4] = getColor(31, 63, 0);
	colors[5] = getColor(0, 0, 0);
	colors[6] = getColor(17, 17, 2);
	colors[7] = getColor(31, 56, 22);
}

//draws mario to the screen using fill_rect
void draw_mario(int x, int y)
{
	fill_rect(x+12, y+4, 24, 4, colors[0]);
	fill_rect(x+8, y+8, 40, 4, colors[0]);
	
	fill_rect(x+8, y+12, 12, 4, colors[6]);
	fill_rect(x+20, y+12, 20, 4, colors[7]);
	fill_rect(x+32, y+12, 4, 4, colors[5]);
	
	fill_rect(x+4, y+16, 4, 4, colors[6]);
	fill_rect(x+8, y+16, 40, 4, colors[7]);
	fill_rect(x+12, y+16, 4, 4, colors[6]);
	fill_rect(x+32, y+16, 4, 4, colors[5]);
	
	fill_rect(x+4, y+20, 4, 4, colors[6]);
	fill_rect(x+8, y+20, 44, 4, colors[7]);
	fill_rect(x+12, y+20, 8, 4, colors[6]);
	fill_rect(x+36, y+20, 4, 4, colors[5]);
	
	fill_rect(x+4, y+24, 8, 4, colors[6]);
	fill_rect(x+8, y+24, 20, 4, colors[7]);
	fill_rect(x+36, y+24, 16, 4, colors[5]);
	
	fill_rect(x+12, y+28, 32, 4, colors[7]);
	
	fill_rect(x+8, y+32, 28, 4, colors[0]);
	fill_rect(x+16, y+32, 4, 4, colors[2]);
	
	fill_rect(x+4, y+36, 40, 4, colors[0]);
	fill_rect(x+16, y+36, 4, 4, colors[2]);
	fill_rect(x+28, y+36, 4, 4, colors[2]);
	
	fill_rect(x, y+40, 48, 4, colors[0]);
	fill_rect(x+16, y+40, 16, 4, colors[2]);
	
	fill_rect(x, y+44, 8, 4, colors[7]);
	fill_rect(x+8, y+44, 4, 4, colors[0]);
	fill_rect(x+12, y+44, 4, 4, colors[2]);
	fill_rect(x+16, y+44, 4, 4, colors[4]);
	fill_rect(x+20, y+44, 8, 4, colors[2]);
	fill_rect(x+28, y+44, 4, 4, colors[4]);
	fill_rect(x+32, y+44, 4, 4, colors[2]);
	fill_rect(x+36, y+44, 4, 4, colors[0]);
	fill_rect(x+40, y+44, 8, 4, colors[7]);
	
	fill_rect(x, y+48, 48, 4, colors[7]);
	fill_rect(x+12, y+48, 24, 4, colors[2]);
	
	fill_rect(x, y+48, 48, 4, colors[7]);
	fill_rect(x+8, y+48, 32, 4, colors[2]);
	
	fill_rect(x+8, y+52, 12, 4,  colors[2]);
	fill_rect(x+8, y+52, 28, 4,  colors[2]);
	
	fill_rect(x+4, y+56, 12, 4, colors[6]);
	fill_rect(x+32, y+56, 12, 4, colors[6]);
	
	fill_rect(x, y+60, 16, 4, colors[6]);
	fill_rect(x+32, y+60, 16, 4, colors[6]);	
}

//clear mario from the screen in preparation for move
void clear_mario(int x, int y)
{
	fill_rect(x, y, 52, 64, colors[5]);
}

//draw a header with draw_rect and draw_text
void draw_header()
{
	draw_rect(40, 20, 560, 60, colors[0]);
	draw_rect(50, 30, 540, 40, colors[2]);
	draw_rect(60, 40, 520, 20, colors[0]);
	draw_text(64, 44, "It'sa meeee!", colors[4]);
}

//change the text appearing in the header
void change_text(int j, color_t c)
{
	//clear text
	fill_rect(61, 41, 518, 18, colors[5]);
	//if color is black, make it white
	if(c == colors[5])
		c = getColor(31, 63, 31);
	//draw new text
	switch(j)
	{
		case 0:
			draw_text(64, 44, "It'sa meeee!", c);
			break;
		case 1:
			draw_text(64, 44, "Princess in another castle.", c);
			break;
		case 2:
			draw_text(64, 44, "Stomp more goombas.", c);
			break;
		case 3:
			draw_text(64, 44, "Where's Luigi?", c);
			break;
		case 4:
			draw_text(64, 44, "I sure wish I had a gokart.", c);
			break;
		case 5:
			draw_text(64, 44, "It's dangerous to go alone.", c);
			break;
		case 6:
			draw_text(64, 44, "Bowser is a jerk.", c);
			break;
		case 7:
			draw_text(64, 44, "Have I ever done any actual plumbing?", c);
			break;
	}
}

//instructions for program use
void draw_footer()
{
	draw_text(40, 440, "Move with wasd. Cycle colors with /. Space to change quote.", colors[4]);
	draw_text(40, 460, "Draw rect with r. Fill rect with f. Quit with q", colors[4]);
}