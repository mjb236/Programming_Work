//Michael Bowen
//CS0449 Tues/Thurs 4:00 - 5:15  Rec: Friday 1:00pm
//
//Project 3 - custom malloc and free

#include "mymalloc.h"

struct node *firstNode = NULL;

//allocate memory based on first fit
void *my_firstfit_malloc(int size)
{
	void *dataLoc;
	struct node *curr;
	int totalSize = (int) sizeof(struct node) + size;

	//if first node is null - nothing in list yet. create first node
	if(firstNode == NULL)
	{
		firstNode = sbrk(totalSize);
		firstNode->prev = NULL;
		firstNode->next = NULL;
		firstNode->allocated = 1;
		firstNode->size = totalSize;
		dataLoc = ((void *) firstNode) + sizeof(struct node);

		return dataLoc;
	}

	//attempt to find an empty space big enough for the request
	curr = firstNode;
	while(curr->next != NULL)	//the last node in the chain cannot be free
	{
		//check current node for size and allocated
		if(curr->allocated)
		{
			//current node already being used - move to next node
			curr = curr->next;
		}
		else if((!curr->allocated) && (curr->size < (totalSize)))
		{
			//current node free, but not big enough - move to next node
			curr = curr->next;
		}
		else
		{
			//current node free and big enough
			if((curr->size - totalSize) > sizeof(struct node))
			{
				//split the node into an allocated node and a free node
				struct node *newNode;
				newNode = (struct node *) (((void *) curr) + totalSize);
				newNode->prev = curr;
				newNode->next = curr->next;
				newNode->size = curr->size - totalSize;
				newNode->allocated = 0;

				dataLoc = ((void *) curr) + sizeof(struct node);
				curr->next = newNode;
				curr->size = totalSize;
				curr->allocated = 1;
				return dataLoc;
				
			}
			else
			{
				//remaining size is less than size of a node - unusable space
				//mark the node allocated, return the location of the data
				curr->allocated = 1;
				dataLoc = ((void *) curr) + sizeof(struct node); 
				return dataLoc;
			}
		}
		
	}

	//we have reached the end of the linked list - allocate more memory
	curr->next = sbrk(totalSize);
	curr->next->prev = curr;
	curr->next->next = NULL;
	curr->next->allocated = 1;
	curr->next->size = totalSize;
	dataLoc = ((void *) curr->next) + sizeof(struct node);

	return dataLoc;	
}

//free the specified memory location
void my_free(void *ptr)
{
	struct node *curr;

	curr = (struct node *) (ptr - sizeof(struct node));

	//if prev is NULL, we are freeing first node
	if(curr->prev == NULL)
	{
		if(curr->next == NULL)
		{
			//curr is the only memory allocated - adjust the heap down
			int size = curr->size;
			firstNode = NULL;
			sbrk(-size);			
		}
		else
		{
			//check next allocation status
			if(!(curr->next->allocated))
			{
				//next is free as well - merge sizes, mark unallocated, and change next
				curr->allocated = 0;
				curr->size += curr->next->size;
				curr->next = curr->next->next;
			}
			else
			{
				//next is allocated - mark current unallocated
				curr->allocated = 0;
			}
		}
			
	}
	//if next is NULL, we are freeing the last node
	else if((curr->prev != NULL) && (curr->next == NULL))
	{
		int size;
		
		//check prev allocation status
		if(!(curr->prev->allocated))
		{
			//if previous is unallocated, merge sizes and move curr back
			curr->prev->size += curr->size;
			curr = curr->prev;
		}

		//set size, move curr back and adjust the heap
		size = curr->size;
		if(curr == firstNode)
		{
			firstNode = NULL;
		}
		else
		{
			curr->prev->next = NULL;
		}
	
		sbrk(-size);
	}
	//if neither next or prev is null, we are freeing a middle node
	else
	{
		//check prev allocation status
		if(!(curr->prev->allocated))
		{
			//previous is unallocated - merge sizes, merge curr and prev
			curr->prev->size += curr->size;
			curr->prev->next = curr->next;
			curr->next->prev = curr->prev;
			curr = curr->prev;
		}

		//check next allocation status
		if(!(curr->next->allocated))
		{
			//next is unalloacted - merge sizes, merge curr and next
			curr->size += curr->next->size;
			curr->next->next->prev = curr;
			curr->next = curr->next->next;
		}

		curr->allocated = 0;
	}
}
