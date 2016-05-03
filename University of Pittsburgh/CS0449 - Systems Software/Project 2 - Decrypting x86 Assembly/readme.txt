Pitt ID: mjb236

The session number is not one number, but an array of random numbers
	from 1 to 8, inclusive.

The first number is the sum of all 10 numbers in the session ID array.
The second number is the sum of all the ODD numbers in the session ID array.
The third number is the sum of two numbers from the session ID array. The 
	numbers are based upon the first and second numbers in the Pitt ID,
	in my case 2 and 3. The program uses these numbers as indicies for 
	the array and sums the numbers from those two locations.
The fourth number must be less than the LAST number in the session ID array.
The fifth number must be less than the FIRST number in the session ID array.

Example:
Session number: 5442886838
Number 1: 56 (sum of all numbers)
Number 2: 8  (sum of all ODD numbers - 5 + 3)
Number 3: 6  (sum of sessionID[2] and sessionID[3] - 4 + 2)
Number 4: 7  (any number less than the last number - 8)
Number 5: 4  (any number less than the first number - 5)
Congratulations!!! You just crack the code.
