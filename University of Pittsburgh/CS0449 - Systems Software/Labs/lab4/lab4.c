//Michael Bowen
//CS0449 - Tues/Thurs 4-5:15  Rec: Fri 1:00pm
//
//Lab4

#include <stdio.h>
#include <stdlib.h>

struct list
{
	int numberOfEntries;
	struct node *firstNode;
};

struct node
{
	int data;
	struct node *next;
};

/* Allocates a chunk of memory the same size as struct list,
 * initializes the numberOfEntries to 0, initialize the fistNode
 * to NULL, and returns the address of type pointer to struct list.
 */
struct list *constructList()
{

	struct list *aList = (struct list *) malloc(sizeof(struct list));
	aList->numberOfEntries = 0;
	aList->firstNode = NULL;

	return aList;
} //ends constructList

/* Add newEntry to the end of of the given aList.
 */
void add(struct list *aList, int newEntry)
{
	//create a new node
	struct node *newNode = (struct node *) malloc(sizeof(struct node));
	newNode->data = newEntry;
	newNode->next = NULL;

	//if list is empty, set the new node to be the first entry
	//otherwise, traverse to end of list and add new node
	if(aList->numberOfEntries == 0)
	{
		aList->firstNode = newNode;
		aList->numberOfEntries++;
	} 
	else
	{
		struct node *end = aList->firstNode;
		while(end->next != NULL)
		{
			end = end->next;
		} //ends while
		
		end->next = newNode;
		aList->numberOfEntries++;
	} //ends if-else	
} //ends add

/* Returns the address of the node associate with the
 * given index of the given aList.
 */
struct node *getNodeAt(struct list *aList, int index)
{
	struct node *targetNode;
	
	//return NULL if list is empty
	if(aList->numberOfEntries == 0)
	{
		printf("List is empty!\n");
		targetNode = NULL;
	}
	//return NULL if index is out of the list bounds
	else if((index >= aList->numberOfEntries) || (index < 0))
	{
		printf("Index out of bounds!\n");
		targetNode = NULL;
	}
	else
	{
		int i;
		targetNode = aList->firstNode;
		
		//traverse to index specified
		for(i = 0; i < index; i++)
		{
			targetNode = targetNode->next;
		} //ends for
	} //ends if-else

	return targetNode;	
} //ends getNodeAt

/* Returns the data at the given index of the given aList.
 * DO NOT MODIFY THIS FUNCTION.
 */
int get(struct list *aList, int index)
{
	return getNodeAt(aList, index)->data;
}

/* Removes an entry at the given index from the give aList,
 * and returns the data from the removed index.
 */
int removeEntry(struct list *aList, int index)
{
	struct node *targetNode;
	int data;
	
	//print error message/return 0 if list empty
	if(aList->numberOfEntries == 0)
	{
		printf("List is empty!\n");
		data = 0;
	}
	//print error messaget/return 0 if index OOB
	else if((index >= aList->numberOfEntries) || (index < 0))
	{
		printf("Index out of bounds!\n");
		data = 0;
	}
	else
	{
		targetNode = aList->firstNode;
		
		//if first item of list
		if(index == 0)
		{
			//fetch data and delete node
			data = targetNode->data;
			aList->firstNode = targetNode->next;
			free(targetNode);
			aList->numberOfEntries--;
		}
		//if not first item of list
		else
		{
			int i;
			struct node *last;

			//traverse to index
			for(i = 0; i < index; i++)
			{
				last = targetNode;
				targetNode = targetNode->next;
			}

			//fetch data and delete node
			data = targetNode->data;
			last->next = targetNode->next;
			free(targetNode);
			aList->numberOfEntries--;
		} //ends inner if-else
	} //ends if-else

	return data;
}

/* Print information and contents of a given aList.
 * DO NOT MODIFY THIS FUNCTION.
 */
void printList(struct list *aList)
{
	struct node *currentNode;
	currentNode = aList->firstNode;
	printf("The given list contains %i entries: ", aList->numberOfEntries);
	
	while(currentNode != NULL)
	{
		printf("%i ", currentNode->data);
		currentNode = currentNode->next;
	}
	printf("\n");
}

/* This is a simple test program. Note that you should write
 * your own test program when you are implementing above functions.
 * When you think that all your functions work correctly, use this
 * main function.
 */
int main(void)
{
	struct list *myList;
	int i;

	myList = constructList();
	
	for(i = 0; i < 5; i++)
	{
		printf("Adding %i\n", i);
		add(myList,i);
		printList(myList);
	}

	printf("\n");
	
	printf("Remove index 0 (first node on the link chain)\n");
	i = removeEntry(myList,0);
	printf("The function removeEntry returns %i.\n", i);
	printList(myList);

	printf("Remove index 3 (last node on the link chain)\n");
	i = removeEntry(myList,3);
	printf("The function removeEntry returns %i.\n", i);
	printList(myList);

	printf("Remove index 1 (middle node on the link chain)\n");
	i = removeEntry(myList,1);
	printf("The function removeEntry returns %i.\n", i);
	printList(myList);

	return 0;
}
