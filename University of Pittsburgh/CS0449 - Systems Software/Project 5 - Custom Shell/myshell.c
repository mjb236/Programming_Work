//Michael Bowen
//CS0449  Tues/Thurs 4:00-5:15pm  Red: Fri 1:00pm
//
//Project 5 - simple shell program

#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <error.h>
#include <string.h>

int main(void)
{
	char buffer[200];						//input from user
	char exitStr[5] = {'e','x','i','t','\0'};			//exit command string
	char showPath[9] = {'s','h','o','w','p','a','t','h','\0'};		//showpath command string
	char hidePath[9] = {'h','i','d','e','p','a','t','h','\0'};	//hide path command string
	char ls[3] = {'l','s','\0'};					//ls command string
	char color[8] = {'-','-','c','o','l','o','r','\0'};		//arg for ls to add color
	char cdStr[3] = {'c','d','\0'};					//cd command string
	char *args[20];							//tokenized arguments
	char path[200] = {'\0'};					//current working directory
	int i;								//loop control variable

	while(1)
	{
		//get input
		printf("mjb236's shell %s> ", path);
		fgets(buffer, 200, stdin);
		//remove \n
		i = 0;
		while(buffer[i] != '\n')
			i++;
		buffer[i] = '\0';

		//tokenize the input
		args[0] = strtok(buffer, " \t");
		i = 0;
		do
		{
			i++;			
			args[i] = strtok(NULL, " \t\n");
		} while(args[i] != NULL);

		//run shell while user does not issue exit command
		if(!strncmp(args[0], exitStr, 4))
		{
			//exit program
			exit(EXIT_SUCCESS);
		}
		else if(!strncmp(args[0], cdStr, 2))
		{
			//change directory
			if(chdir(args[1]) == -1)
				perror("mjb236's shell: ");
			//update current working directory
			if(getcwd(path, 200) == NULL)
				perror("mjb236's shell: ");
			continue;
		}
		else if(!strncmp(args[0], showPath, 8))
		{
			//get the current working directory
			if(getcwd(path, 200) == NULL)
				perror("mjb236's shell: ");
			continue;
		}
		else if(!strncmp(args[0], hidePath, 8))
		{
			//set current working directory to null character
			*path = '\0';
			continue;
		}
		else if(!strncmp(args[0], ls, 2))
		{
			//add color to the ls command by default
			i = 1;
			while(args[i] != NULL)
				i++;
			args[i] = color;
			args[i+1] = NULL;	

			//fork into two processes and attempt to run linux command
			if(fork() == 0)
			{
				if(execvp(args[0], args) == -1)
				{
					//exit process if there's an error
					perror("mjb236's shell: ");
					exit(0);
				}
			}
			else
			{
				//wait for process to finish before coming back to shell
				int status;
				wait(&status);
			}

			continue;		
		}
		else
		{
			//fork into two processes and attempt to run linux command
			if(fork() == 0)
			{
				if(execvp(args[0], args) == -1)
				{
					//exit process if there's an error
					perror("mjb236's shell: ");
					exit(0);
				}
			}
			else
			{
				//wait for process to finish before coming back to shell
				int status;
				wait(&status);
			}
		}	
	}

	return 0;
}	
