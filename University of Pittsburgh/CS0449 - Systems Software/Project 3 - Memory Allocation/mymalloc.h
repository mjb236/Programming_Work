//Michael Bowen
//CS0449 Tues/Thurs 4:00 - 5:15  Rec: Friday 1:00pm
//
//Project 3 - custom malloc and free

#include <unistd.h>

void *my_firstfit_malloc(int size);
void my_free(void *ptr);

struct node
{
	struct node *prev;
	unsigned int allocated;
	unsigned int size;
	struct node *next;
};
