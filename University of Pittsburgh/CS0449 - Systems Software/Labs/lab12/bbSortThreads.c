//Michael Bowen
//CS0449 Tues/Thurs 4:00-5:15pm Rec: Fri 1:00pm
//
//Lab 12 - threaded sort

#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <pthread.h>

#define NUMEL 12000

//structure for storing arguments to send to the bubble sort algorithm
struct sortArgs
{
	int * array;
	int n;
};

/* void *bubbleSort(void * args)
 *
 * bubbleSort algorithm converted to be able to
 * use in multithreaded environment
 */
void *bubbleSort(void * args)
{
	int i, j, n, temp;
	int *array;

	array = ((struct sortArgs *) args)->array;
	n = ((struct sortArgs *) args)->n;

	for(i = 0; i < n - 1; i++)
	{
		for(j = 0; j < n - 1; j++)
		{
			if(array[j] > array[j + 1])
			{
				temp = array[j];
				array[j] = array[j + 1];
				array[j + 1] = temp;
			}
		}
	}
}

/* genRandomIntArray(int numElements, int upperBound)
 *
 * Generates an array size numElements and fills the array
 * with random numbers between 0 (inclusive) to upperBound
 * (exclusive). Then returns the pointer to the array.
 */
int *genRandomIntArray(int numElements, int upperBound)
{
	int i;
	int * result = (int *) malloc(numElements * sizeof(int));

	for(i = 0; i < numElements; i++)
	{
		result[i] = rand() % upperBound;
	}

	return result;
}

/* getElapsedTime(struct timeval start, struct timeval stop)
 *
 * Get the elapsed time from start to stop in the form of struct timeval.
 */
struct timeval getElapsedTime(struct timeval start, struct timeval stop)
{
	struct timeval elapsedTime;
	elapsedTime.tv_sec = stop.tv_sec - start.tv_sec;
	elapsedTime.tv_usec = stop.tv_usec - start.tv_usec;
	if(elapsedTime.tv_usec < 0)
	{
		elapsedTime.tv_usec = elapsedTime.tv_usec + 1000000;
		elapsedTime.tv_sec = elapsedTime.tv_sec - 1;
	}
	return elapsedTime;
}

/* printArray(int *array, int numElements)
 *
 * Prints the first numElements of the array.
 */
void printArray(int *array, int numElements)
{
	int i;
	for(i = 0; i < numElements; i++)
	{
		printf("%i ", array[i]);
	}
	printf("\n\n");
}

int main(void)
{
	int *array1;
	int *array2;
	int i;
	struct timeval startTime, endTime, elapsedTime;
	pthread_t thread1, thread2;			//thread variables
	struct sortArgs *args1, *args2;

	// Seed the random number generator

	srand(time(0));

	// Generate two integer array filled with random numbers
	args1 = (struct sortArgs *) malloc(sizeof(struct sortArgs));
	args2 = (struct sortArgs *) malloc(sizeof(struct sortArgs));
	args1->array = genRandomIntArray(NUMEL,10000);
	args2->array = genRandomIntArray(NUMEL,10000);
	args1->n = NUMEL;
	args2->n = NUMEL;

	// Sort two arrays

	gettimeofday(&startTime, NULL);
	//bubbleSort(array1, NUMEL);
	//bubbleSort(array2, NUMEL);
	pthread_create(&thread1, NULL, bubbleSort, args1);
	pthread_create(&thread2, NULL, bubbleSort, args2);
	pthread_join(thread1, NULL);
	pthread_join(thread2, NULL);
	gettimeofday(&endTime, NULL);

	// Print the first 100 elements of each array

	printf("array1: ");
	printArray(args1->array, 100);
	printf("array2: ");
	printArray(args2->array, 100);

	free(args1);
	free(args2);

	// Show how long does it takes to sort two arrays

	elapsedTime = getElapsedTime(startTime, endTime);

	printf("Sorting Time: %li.%.6li seconds\n", elapsedTime.tv_sec, elapsedTime.tv_usec);

	return 0;
}
