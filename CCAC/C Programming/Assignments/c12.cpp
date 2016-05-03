/* Michael Bowen
   CIT 145
   Chapter 10,12 Assignment
   Dev-C++ 4.9.9.2 */
   
   
#include <stdio.h>
#include <stdlib.h>

/* self-referential structure */
struct listNode {            
   char data; /* each listNode contains a character */
   struct listNode *nextPtr; /* pointer to next node*/ 
   struct listNode *prevPtr; /* pointer to previous node */
}; /* end structure listNode */

typedef struct listNode ListNode; /* synonym for struct listNode */
typedef ListNode *ListNodePtr; /* synonym for ListNode* */

/* prototypes */
void insert( ListNodePtr *sPtr, char value );
char deleteNode( ListNodePtr *sPtr, char value );
int isEmpty( ListNodePtr sPtr );
void printList( ListNodePtr currentPtr );
void printReverse(ListNodePtr currentPtr);
void instructions( void );

int main( void )
{ 
   ListNodePtr startPtr = NULL; /* initially there are no nodes */
   int choice; /* user's choice */
   char item; /* char entered by user */

   instructions(); /* display the menu */
   printf( "? " );
   scanf( "%d", &choice );

   /* loop while user does not choose 3 */
   while ( choice != 3 ) { 

      switch ( choice ) { 
         case 1:
            printf( "Enter a character: " );
            scanf( "\n%c", &item );
            insert( &startPtr, item ); /* insert item in list */
            printList( startPtr );
            printReverse(startPtr);
            break;
         case 2:
            /* if list is not empty */
            if ( !isEmpty( startPtr ) ) { 
               printf( "Enter character to be deleted: " );
               scanf( "\n%c", &item );

               /* if character is found, remove it */
               if ( deleteNode( &startPtr, item ) ) { /* remove item */
                  printf( "%c deleted.\n", item );
                  printList( startPtr );
                  printReverse(startPtr);
               } /* end if */
               else {
                  printf( "%c not found.\n\n", item );
               } /* end else */
            } /* end if */
            else {
               printf( "List is empty.\n\n" );
            } /* end else */

            break;
         default:
            printf( "Invalid choice.\n\n" );
            instructions();
            break;
      } /* end switch */

      printf( "? " );
      scanf( "%d", &choice );
   } /* end while */

   printf( "End of run.\n" );
   /* keep dos window open */
   printf("\n");
   system("pause");
   return 0;  
} /* end main */

/* display program instructions to user */
void instructions( void )
{ 
   printf( "Enter your choice:\n"
      "   1 to insert an element into the list.\n"
      "   2 to delete an element from the list.\n"
      "   3 to end.\n" );
} /* end function instructions */

/* Insert a new value into the list in sorted order */
void insert( ListNodePtr *sPtr, char value )
{ 
   ListNodePtr newPtr; /* pointer to new node */
   ListNodePtr previousPtr; /* pointer to previous node in list */
   ListNodePtr currentPtr; /* pointer to current node in list */

   newPtr = (ListNode *) malloc(sizeof(ListNode)); /* create node */

   if ( newPtr != NULL ) { /* is space available */
      newPtr->data = value; /* place value in node */
      newPtr->nextPtr = NULL; /* node does not link to another node */
      newPtr->prevPtr = NULL; /* previous node does not link to another node */

      previousPtr = NULL;
      currentPtr = *sPtr;

      /* loop to find the correct location in the list */
      while ( currentPtr != NULL && value > currentPtr->data ) { 
         previousPtr = currentPtr; /* walk to ...   */
         currentPtr = currentPtr->nextPtr; /* ... next node */
      } /* end while */

      /* insert new node at beginning of list */
      if ( previousPtr == NULL ) { 
         newPtr->nextPtr = *sPtr;
         newPtr->prevPtr = previousPtr; /* link previous pointer */
         *sPtr = newPtr;
      } /* end if */
      else { /* insert new node between previousPtr and currentPtr */
         previousPtr->nextPtr = newPtr;
         newPtr->nextPtr = currentPtr;
         newPtr->prevPtr = previousPtr; /* link to previous node */
         if(currentPtr != NULL)
            currentPtr->prevPtr = newPtr;
      } /* end else */
   } /* end if */
   else {
      printf( "%c not inserted. No memory available.\n", value );
   } /* end else */
} /* end function insert */

/* Delete a list element */
char deleteNode( ListNodePtr *sPtr, char value )
{ 
   ListNodePtr previousPtr; /* pointer to previous node in list */
   ListNodePtr currentPtr; /* pointer to current node in list */
   ListNodePtr tempPtr; /* temporary node pointer */
   ListNodePtr nextPtr; /* pointer to next node in the list */

   /* delete first node */
   if ( value == ( *sPtr )->data ) { 
      tempPtr = *sPtr; /* hold onto node being removed */
      *sPtr = ( *sPtr )->nextPtr; /* de-thread the node */
      if(*sPtr != NULL)
         (*sPtr)->prevPtr = NULL; /* first node deleted, previous pointer of first node should be NULL */
      free( tempPtr ); /* free the de-threaded node */
      return value;
   } /* end if */
   else { 
      previousPtr = *sPtr;
      currentPtr = ( *sPtr )->nextPtr;
      if(currentPtr != NULL)
         nextPtr = currentPtr->nextPtr; /* assign the next pointer in the list to nextPtr */

      /* loop to find the correct location in the list */
      while ( currentPtr != NULL && currentPtr->data != value ) { 
         previousPtr = currentPtr; /* walk to ...   */
         currentPtr = nextPtr; /* ... next node */  
         if(nextPtr != NULL)
            nextPtr = nextPtr->nextPtr; /* move next pointer if not already NULL */
      } /* end while */

      /* delete node at currentPtr */
      if ( currentPtr != NULL ) { 
         tempPtr = currentPtr;
         previousPtr->nextPtr = currentPtr->nextPtr;
         /* link the next pointer to previous pointer in reverse */
         if(nextPtr != NULL)
             nextPtr->prevPtr = currentPtr->prevPtr;
             
         free( tempPtr );
         return value;
      } /* end if */
   } /* end else */

   return '\0';
} /* end function delete */

/* Return 1 if the list is empty, 0 otherwise */
int isEmpty( ListNodePtr sPtr )
{ 
   return sPtr == NULL;
} /* end function isEmpty */

/* Print the list */
void printList( ListNodePtr currentPtr )
{ 
   /* if list is empty */
   if ( currentPtr == NULL ) {
      printf( "List is empty.\n\n" );
   } /* end if */
   else { 
      printf( "The list is:\n" );

      /* while not the end of the list */
      while ( currentPtr != NULL ) { 
         printf( "%c --> ", currentPtr->data );
         currentPtr = currentPtr->nextPtr;   
      } /* end while */

      printf( "NULL\n\n" );
   } /* end else */
} /* end function printList */

void printReverse(ListNodePtr currentPtr) {
   if(currentPtr == NULL) {
      printf("List is empty.\n\n");
   } /* end if */
   else {
      /* move currentPtr to the end of list */
      while(currentPtr->nextPtr != NULL)
         currentPtr = currentPtr->nextPtr;
         
      /* print list in reverse */
      printf("The list in reverse is:\n");
      while(currentPtr != NULL) {
         printf("%c --> ", currentPtr->data);
         currentPtr = currentPtr->prevPtr;
      } /* ends while */
      
      printf("NULL\n\n");
   } /* ends else */
} /* ends printReverse */
