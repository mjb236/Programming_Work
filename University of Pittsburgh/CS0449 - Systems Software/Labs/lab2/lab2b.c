#include <stdio.h>

int main(void)
{
	//declare necessary variables
	int numItems = 0;
	int counter;
	const float TAX = 0.07;
	float totalTax = 0.0;
	float subtotal = 0.0;
	float total = 0.0;

	//get number of items from the user
	printf("How many items do you wish to enter? : ");
	scanf("%d", &numItems);

	//get cost of each item and add it to the subtotal
	for(counter = 0; counter < numItems; counter++)
	{
		float itemCost = 0.0;
		printf("Enter the price of item number %d: ", counter + 1);
		scanf("%f", &itemCost);

		subtotal += itemCost;
	}

	//calculate tax and total
	totalTax = TAX * subtotal;
	total = subtotal + totalTax;

	//display results to user
	printf("\nYou have entered %d items.\n", numItems);
	printf("The sub-total is $%.2f\n", subtotal);
	printf("The tax is $%.2f.\n", totalTax);
	printf("The total is $%.2f.\n", total);

	return 0;
}
