//Michael Bowen
//CS1550 Tues/Thurs 2:30-3:45 Recitation Fri 12:00
//
//Project 2 - Producer/Consumer

#include <stdio.h>				//printf for debugging
#include <linux/unistd.h>		//syscalls
#include <stdlib.h>				//strtol - for converting cmd line args to integers
#include <sys/mman.h>			//mmap and munmap
#include <sys/wait.h>			//wait function to let all forks finish
#include <sys/types.h>			//wait function

struct cs1550_sem {
	int value;
	struct processNode *head;
	struct processNode *tail;
};

//wrapper function for the down syscall
void down(struct cs1550_sem *sem) {
	syscall(__NR_cs1550_down, sem);
}

//wrapper function for the up syscall
void up(struct cs1550_sem *sem) {
	syscall(__NR_cs1550_up, sem);
}

int main(int argc, char *argv[]) {
	int numProd, numCons, bufferSize;				//arguments passed into the program
	struct cs1550_sem *mutex, *full, *empty;		//semaphores
	struct processNode *head, *curr, *prev;			//variables for queue setup and destruction
	int *buffer, *buffSize, *currProd, *currCons;	//buffer for produced numbers and current prod/cons spot on buffer
	int i;											//loop control
	int waitStat;									//argument for wait function - need to let all forks finish before main
	
	//check for number of arguments, exit if incorrect
	if(argc != 4) {
		printf("Incorrect number of arguments. Provide producers, consumers and buffer size.\n");
		return -1;
	}
	
	//convert arguments to integers
	numProd = (int) strtol(argv[1], NULL, 10);
	numCons = (int) strtol(argv[2], NULL, 10);
	bufferSize = (int) strtol(argv[3], NULL, 10);
	
	//set up buffer
	buffer = (int *) mmap(NULL, sizeof(int) * bufferSize, PROT_READ|PROT_WRITE, MAP_SHARED|MAP_ANONYMOUS, 0, 0);
	for(i = 0; i < bufferSize; i++) {
		*(buffer + i) = 0;
	}
	
	//set up shared memory for currProd, currCons and the buffer size since
	//those variables will need to be accessed in multiple threads
	buffSize = (int *) mmap(NULL, sizeof(int), PROT_READ|PROT_WRITE, MAP_SHARED|MAP_ANONYMOUS, 0, 0);
	*buffSize = bufferSize;
	currProd = (int *) mmap(NULL, sizeof(int), PROT_READ|PROT_WRITE, MAP_SHARED|MAP_ANONYMOUS, 0, 0);
	*currProd = 0;
	currCons = (int *) mmap(NULL, sizeof(int), PROT_READ|PROT_WRITE, MAP_SHARED|MAP_ANONYMOUS, 0, 0);
	*currProd = 0;	
	
	//set up the semaphores
	mutex = (struct cs1550_sem *) mmap(NULL, sizeof(struct cs1550_sem), PROT_READ|PROT_WRITE, MAP_SHARED|MAP_ANONYMOUS, 0, 0);
	empty = (struct cs1550_sem *) mmap(NULL, sizeof(struct cs1550_sem), PROT_READ|PROT_WRITE, MAP_SHARED|MAP_ANONYMOUS, 0, 0);
	full = (struct cs1550_sem *) mmap(NULL, sizeof(struct cs1550_sem), PROT_READ|PROT_WRITE, MAP_SHARED|MAP_ANONYMOUS, 0, 0);
	mutex->value = 1;				//one critical section
	mutex->head = NULL;
	mutex->tail = NULL;
	empty->value = bufferSize;		//bufferSize empty spots
	empty->head = NULL;
	empty->tail = NULL;
	full->value = 0;
	full->head = NULL;
	full->tail = NULL;				//0 full spots
	
	//create the producer threads using fork
	for(i = 0; i < numProd; i++) {
		if(fork() == 0) {
			//child thread - infinitely produce items into the buffer
			while(1) {
				down(empty);	//decrease number of empty spaces
				down(mutex);	//lock the mutex
				
				//put the produced item into the buffer - in this case the produced item is
				//simply the index in the buffer
				*(buffer + *currProd) = *currProd;
				
				//tell user that item was produced - add 65 to account for ASCII
				printf("Producer %c produced %i.\n", (i  +65), *currProd);
				
				//increment the index (produced item)
				*currProd = (*currProd + 1) % *buffSize;
				
				up(mutex);		//unlock the mutex
				up(full);		//increase number of full spaces
			}			
		}
	}
	
	//create the producer threads using fork
	for(i = 0; i < numCons; i++) {
		if(fork() == 0) {
			//child thread - infinitely consume items from the buffer
			while(1) {
				int consumedItem;
				
				down(full);		//decrease number of full spaces
				down(mutex);	//lock the mutex
				
				//remove the item at index *currCons
				consumedItem = *(buffer + *currCons);
				
				//tell user that item was consumed - add 65 to account for ASCII
				printf("Consumer %c consumed %i.\n", (i+65), consumedItem);
				
				//increment the index from which to consume
				*currCons = (*currCons + 1) % *buffSize;
				
				up(mutex);		//unlock the mutex
				up(empty);		//increase number of empty spaces
			}			
		}
	}
	
	//wait for children process to finish
	wait(&waitStat);
	
	//I suppose the program will never get here, but might as well unmap the memory
	munmap(mutex, sizeof(struct cs1550_sem));
	munmap(empty, sizeof(struct cs1550_sem));
	munmap(full, sizeof(struct cs1550_sem));
	munmap(buffSize, sizeof(int));
	munmap(buffer, sizeof(int) * bufferSize);
	munmap(currProd, sizeof(int));
	munmap(currCons, sizeof(int));
		
	return 0;
}