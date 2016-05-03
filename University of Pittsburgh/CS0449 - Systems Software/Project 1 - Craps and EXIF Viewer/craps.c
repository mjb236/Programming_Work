//Michael Bowen
//CS449 - Tues/Thurs 4:00-5:15  Rec: Fri 1:00-1:50
//
//Program that allows the user to play craps

#include <stdio.h>

//function that prints a greeting upon program startup
void greetUser(void)
{
	printf("Welcome to CS0449s Casino!\n");
} //ends greetUser

//say goodbye to user
void sayGoodbye(char *name)
{
	printf("Goodbye, %s!\n", name);
} //ends sayGoodbye

//function that will get the name of the user
void getName(char *name)
{
	printf("Please enter your name: ");
	scanf("%[^\n]s", name);
} //ends getName

//get user input as to whether s/he would like to play or quit
void playOrQuit(char *choice, const char *name)
{
	printf("%s, would you like to play or quit? ", name);
	scanf("%s", choice);
	printf("\n");
} //ends playOrQuit

//play craps
void playGame(void)
{
	int die1, die2, result;

	//roll the dice
	die1 = rollDie();
	die2 = rollDie();
	result = die1 + die2;

	if((result == 7) || (result == 11)) //player wins with 7 or 11
	{
		printf("You have rolled %i + %i = %i\n", die1, die2, result);
		printf("You Win!\n\n");
	} else if((result == 2) || (result ==3) || (result == 12)) //player loses with 2, 3, 12
	{
		printf("You have rolled %i + %i = %i\n", die1, die2, result);
		printf("You Lose!\n\n");
	} else if(result <= 0) //this should not be possible
	{
		printf("Uh oh. Something went wrong.\n\n");
	} else  //point - play again until point or 7
	{
		int point = result;
		printf("You have rolled %i + %i = %i (point)\n", die1, die2, result);
		result = 0;
		
		//reroll until user rolls the point value or 7
		while((result != point) && (result != 7))
		{
			die1 = rollDie();
			die2 = rollDie();
			result = die1 + die2;

			printf("You have rolled %i + %i + %i\n", die1, die2, result);
		}

		if(result == point) //player wins if rolls point
		{
			printf("You Win!\n\n");
		} else if(result == 7) //player loses if rolling 7
		{
			printf("You Lose!\n\n");
		} else //this should not be possible
		{
			printf("Uh oh. Something went wrong.\n\n");
		}
	}
} //ends playGame

//function that rolls one 6 sided die and returns value as integer
int rollDie(void)
{
	return (rand() % 6) + 1;
} //ends rollDice

//determine if the user would like to replay the game
int replay(void)
{
	char choice[10];
	int result = -1;
	
	while(result < 0)
	{
		printf("Would you like to play again? ");
		scanf("%s", choice);
		
		if(strcmp(choice, "yes") == 0)
		{
			return 1;
		} else if(strcmp(choice, "no") == 0)
		{
			return 0;
		} else
		{
			printf("Invalid choice.\n");
		}				
	} //ends while

	return result;	
} //ends replay

int main(void)
{
	char name[50];
	char choice[10];
	int playAgain = -1;

	//setup
	greetUser();
	getName(name);	
	
	//determine whether the user would like to play or not
	while(playAgain < 0)
	{
		playOrQuit(choice, name);
		if(strcmp(choice, "play") == 0)
		{
			//seed random number generator
			srand((unsigned int)time(NULL));
			playAgain = 1;
		} 
		else if(strcmp(choice, "quit") == 0)
		{
			playAgain = 0;
		}
		else
		{
			printf("Invalid choice.\n\n");
			playAgain = -1;
		}
	} //ends while
	
	//play the game until user does not want to continue
	while(playAgain)
	{		
		playGame();
		playAgain = replay();
	} //ends while

	//say goodbye to user and exit program
	sayGoodbye(name);
	return 0;
}
