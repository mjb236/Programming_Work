//Michael Bowen
//CS0449 Tues/Thurs 4:00 - 5:15  Rec: Friday 1:00pm
//
//Project 3 - custom malloc and free

#include "mymalloc.h"
#include <stdio.h>

struct node *ptr;
int *test, *test2, *test3, *test4, *test5;
extern struct node *firstNode;

//allocate memory for the test numbers
void allocateTestNumbers()
{
	test = my_firstfit_malloc(sizeof(int));
	test2 = my_firstfit_malloc(sizeof(int));
	test3 = my_firstfit_malloc(sizeof(int));
	test4 = my_firstfit_malloc(sizeof(int));
	test5 = my_firstfit_malloc(sizeof(int));

	*test = 1;
	*test2 = 2;
	*test3 = 3;
	*test4 = 4;
	*test5 = 5;
}

//frees the first node	
void freeFirst()
{
	my_free(test);
}

//frees the second node
void freeSecond()
{
	my_free(test2);
}

//frees the third node
void freeThird()
{
	my_free(test3);
}

//frees the fourth node
void freeFourth()
{
	my_free(test4);
}

//frees the last node
void freeLast()
{
	my_free(test5);
}

//function that prints out pertinent information from the list of memory allocations
void printList()
{
	struct node *curr = firstNode;
	int num = 1;
	if(curr == NULL)
	{
		printf("List is empty.\n");
	}
	else
	{
		while(curr != NULL)
		{
			if(curr->prev == NULL && curr->next != NULL)
			{
				printf("Node %i - Prev: NULL ", num);
				printf("Curr: %p ", curr);
				printf("Alloc: %i ", curr->allocated);
				printf("Size: %i ", curr->size);
				printf("Next: %p ", curr->next);
				printf("DataLoc: %p\n", ((void *) curr) + sizeof(struct node));
			}
			else if(curr->prev == NULL && curr->next == NULL)
			{
				printf("Node %i - Prev: NULL ", num);
				printf("Curr: %p ", curr);
				printf("Alloc: %i ", curr->allocated);
				printf("Size: %i ", curr->size);
				printf("Next: NULL ");
				printf("DataLoc: %p\n", ((void *) curr) + sizeof(struct node));
			}
			else if(curr->prev != NULL && curr->next == NULL)
			{
				printf("Node %i - Prev: %p ", num, curr->prev);
				printf("Curr: %p ", curr);
				printf("Alloc: %i ", curr->allocated);
				printf("Size: %i ", curr->size);
				printf("Next: NULL ");
				printf("DataLoc: %p\n", ((void *) curr) + sizeof(struct node));
			}
			else
			{
				printf("Node %i - Prev: %p ", num, curr->prev);
				printf("Curr: %p ", curr);
				printf("Alloc: %i ", curr->allocated);
				printf("Size: %i ", curr->size);
				printf("Next: %p ", curr->next);
				printf("DataLoc: %p\n", ((void *) curr) + sizeof(struct node));
			}

			curr = curr->next;
			num++;
		}
	}

	printf("brk: %p\n", sbrk(0));
}

//frees the first node
void testFreeFirst()
{
	printf("TESTING REMOVE FIRST NODE\n");
	printList();
	freeFirst();
	printList();
	printf("END TEST FIRST NODE\n");
}

//test freeing the list from the end
void testFreeFromEnd()
{
	allocateTestNumbers();

	printf("TESTING REMOVE FROM END\n");
	printList();
	freeLast();
	printList();
	freeFourth();
	printList();
	freeThird();
	printList();
	freeSecond();
	printList();
	freeFirst();
	printList();
	printf("END TEST REMOVE FROM END\n");	
}

//test freeing from the middle
void testFreeMiddle()
{
	allocateTestNumbers();

	printf("TESTING REMOVE FROM MIDDLE\n");
	printList();
	freeThird();
	printList();
	freeFourth();
	printList();
	freeSecond();
	printList();
	freeLast();
	printList();
	freeFirst();
	printList();
	printf("END TEST REMOVE FROM MIDDLE\n");
}

int main(void)
{
	allocateTestNumbers();
	
	testFreeFirst();
	//reset test
	freeSecond();
	printList();	
	freeThird();
	printList();
	freeFourth();
	printList();
	freeLast();
	printList();

	testFreeFromEnd();

	testFreeMiddle();	

	return 0;
}
