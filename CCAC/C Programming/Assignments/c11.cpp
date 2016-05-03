/* Michael Bowen
   CIT 145
   Chapter 11 Assignment
   Dev-C++ 4.9.9.2 */
   
#include <stdio.h>
#include <stdlib.h>

/* create and initialize the file if no file is present */
void createFile(FILE **filePtr);
/* show instructions and return user choice */
int instructions(void);
/* enter tools into the database */
void enterTools(FILE *filePtr);
/* delete tools from the database */
void deleteTool(FILE *filePtr);
/* print the database */
void listTools(FILE *filePtr);

/* structure to represent a tool */
struct tool {
    int toolNum;
    char toolName[20];
    int quantity;
    double cost;
};

int main(void) {
    FILE *filePtr;
    int choice = 0;
    
    /* if no file, create and initialize file. otherwise open file for editing */
    if((filePtr = fopen("tools.dat", "rb")) == NULL) {
        printf("File doesn't exist.\n");
        createFile(&filePtr);
    } /* ends if */
    else {
        printf("File exists.\n");
        fclose(filePtr);
        filePtr = fopen("tools.dat", "rb+");
    } /* ends else */
    
    //direct flow of control based on user input
    while((choice != 4) && (filePtr != NULL)) {
        choice = instructions();
        switch(choice) {
            case 1:
                enterTools(filePtr);
                break;
            case 2:
                deleteTool(filePtr);
                break;
            case 3:
                listTools(filePtr);  
                break;
            case 4:
                break;
            default:
                choice = 0;
                printf("Invalid choice\n");
                break;
        } /* ends switch */
    } /* ends while */
    
    /* close file */
    fclose(filePtr);
    
    /* keep dos window open */
    printf("\n");
    system("pause");
    return 0;     
} /* ends main */

void createFile(FILE **filePtr) {
    int i; /* loop counter */
    struct tool newTool = {0, "", 0, 0.0}; /* empty tool */
    
    if((*filePtr = fopen("tools.dat", "wb")) == NULL){
        printf("Error with file.");
    } /* ends if */
    else {
        printf("Creating file...\n");
        //fill file with empty tools
        for(i = 0; i < 100; i++)
            fwrite(&newTool, sizeof(struct tool), 1, *filePtr);
        fclose(*filePtr);
        *filePtr = fopen("tools.dat", "rb+");
    } /* ends else */
} /* ends createFile */

int instructions(void) {
    int choice = 0;
    
    /* instructions */
    printf("1 - Input new tool or updated existing tool.\n");
    printf("2 - Delete a tool.\n");
    printf("3 - List all tools.\n");
    printf("4 - Exit.\n");
    printf("? ");
    scanf("%d", &choice);
    
    return choice;
} /* ends instructions */

void enterTools(FILE *filePtr) {
    /* new tool */
    struct tool newTool = {0, "", 0, 0.0};
    
    /* get first record number */
    printf("Enter record number (1 to 100, 0 to return to main menu)\n? ");
    scanf("%d", &newTool.toolNum);
    
    while(newTool.toolNum != 0) {
        /* get tool information */
        printf("Enter tool name, quantity, cost\n? ");
        fscanf(stdin, "%s%d%lf", newTool.toolName, &newTool.quantity, &newTool.cost);
        
        /* find position to write record to */
        fseek(filePtr, (newTool.toolNum - 1) * sizeof( struct tool), SEEK_SET);
        /* write record */
        fwrite(&newTool, sizeof(struct tool), 1, filePtr);
        
        /* get next record number */
        printf("Enter record number (1 to 100, 0 to return to main menu)\n? ");
        scanf("%d", &newTool.toolNum);
    } /* ends while */        
} /* ends enterTools */

void deleteTool(FILE *filePtr) {
    struct tool newTool;
    struct tool blankTool = {0, "", 0, 0.0};
    int toolNumber;
    
    printf("Enter record number to delete\n?");
    scanf("%d", &toolNumber);
    
    /* find record to delete */
    fseek(filePtr, (toolNumber - 1) * sizeof(struct tool), SEEK_SET);
    /* read record */
    fread(&newTool, sizeof(struct tool), 1, filePtr);
    
    if(newTool.toolNum == 0) {
        printf("Account does not yet exist.\n");
    } /* end if */
    else {
        /* move pointer to record to delete */
        fseek(filePtr, (toolNumber - 1) * sizeof(struct tool), SEEK_SET);
        /* overwrite record with a blank record */
        fwrite(&blankTool, sizeof(struct tool), 1, filePtr);
    } /* end else */
} /* ends deleteTool */

void listTools(FILE *filePtr) {
    struct tool newTool = {0, "", 0, 0.0};
    
    rewind(filePtr);
    
    printf("%-10s%-20s%-10s%-6s\n", "Record #", "Tool name", "Quantity", "Cost");
    while(!feof(filePtr)) {
        /* read file */
        fread(&newTool, sizeof(struct tool), 1, filePtr);
        
        /* display record */
        if(newTool.toolNum != 0) {
            printf("%-10d%-20s%-10d%-6.2f\n", newTool.toolNum, newTool.toolName,
                   newTool.quantity, newTool.cost);
        } /* end if */
    } /* end while */       
} /* ends listTools */
