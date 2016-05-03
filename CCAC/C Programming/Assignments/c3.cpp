/* Michael Bowen
   CIT 145
   Chapter 3, Assignment 1
   Dev-C++ 4.9.9.2 */
   
#include <stdio.h>
#include <stdlib.h>

int main(void) {
    /* variables needed for operation */
    int input, thousands, hundreds, tens, ones, action, temp;
    
    do {
       /* get user choice on to encode or decode */
       printf("Encode (1)  Decode (2): ");
       scanf("%d", &action);
       
       /* encode */
       if(action == 1) {
          /* get the number to encode */
          printf("Enter a four digit number: ");
          scanf("%d", &input);
          
          /* get individual digits from each place in the 4 digit number */
          thousands = input / 1000;
          input %= 1000;          
          hundreds = input / 100;
          input %= 100;          
          tens = input / 10;
          input %= 10;          
          ones = input;
          
          /* encrypt each digit */
          thousands = (thousands + 7) % 10;
          hundreds = (hundreds + 7) % 10;
          tens = (tens + 7) % 10;
          ones = (ones + 7) % 10;
          
          /* swap digits per encryption algorithm */
          temp = thousands;
          thousands = tens;
          tens = temp;
          
          temp = hundreds;
          hundreds = ones;
          ones = temp;
          
          /* display output */
          printf("Encoded Digits : %d%d%d%d\n", thousands, hundreds, tens, ones);
          
          /* continue or exit */
          printf("Continue (1)  Exit (0): ");
          scanf("%d", &action);
       } else if (action == 2) {
          /* get the number to decode */
          printf("Enter a four digit number: ");
          scanf("%d", &input);
          
          /* get individual digits from each place in the 4 digit number */
          thousands = input / 1000;
          input %= 1000;          
          hundreds = input / 100;
          input %= 100;          
          tens = input / 10;
          input %= 10;          
          ones = input;
          
          /* swap the digits per encrption algorithm */
          temp = thousands;
          thousands = tens;
          tens = temp;
          
          temp = hundreds;
          hundreds = ones;
          ones = temp;
          
          /* decrypt the digits. since modulus is difficult to reverse, analyzing
          the encryption shows that to decrypt, you add 3 if the number is
          less than 7, or subtract 7 if the digit is 7 or greater */
          if (thousands >= 7)
             thousands -= 7;
          else
             thousands += 3;
          if (hundreds >= 7)
             hundreds -= 7;
          else
             hundreds += 3;
          if (tens >= 7)
             tens -= 7;
          else
             tens += 3;
          if (ones >= 7)
             ones -= 7;
          else
             ones += 3;
             
          /* display output */
          printf("Decoded Digits : %d%d%d%d\n", thousands, hundreds, tens, ones);
          
          /* continue or exit */
          printf("Continue (1)  Exit (0): ");
          scanf("%d", &action);
       } else {
          printf("Invalid choice.\n");
       } /* end if-else block */
    }while(action != 0); /* end do-while */
    
    /* keep dos window open */
    printf("\n");
    system("pause");
    return 0;
} /* end main */
          
       
