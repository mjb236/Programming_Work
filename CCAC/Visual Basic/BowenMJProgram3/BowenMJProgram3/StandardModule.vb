'StandardModule

Option Strict On

Module StandardModule
                'declare global variable to store the subtotal
    Public g_decSubTotal As Decimal = 0D

'---------------------------------------------------------------------------------
'This procedure calculates a coffee order and adds it to the subtotal
'---------------------------------------------------------------------------------
    Public Sub AddToOrder(ByVal decUnitCost As Decimal, ByVal intNumUnits As Integer)
                'calulate the coffee order and add it to the subtotal
        g_decSubTotal += (decUnitCost * intNumUnits)
    End Sub

'---------------------------------------------------------------------------------
'This procedure calculates and returns the amount of tax on the subtotal
'---------------------------------------------------------------------------------
    Public Function Taxes() As Decimal
                'declare constant for tax rate and a variable to hold the amount of tax
        Const decTAX_RATE As Decimal = 0.07D
        Dim decTax As Decimal

                'calcuate tax
        decTax = g_decSubTotal * decTAX_RATE

                'return the calculated tax
        Return decTax
    End Function
End Module
