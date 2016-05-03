'Michael Bowen
'
'Program 3
'
'This program uses multiple forms to calculate and display a bill for a coffee order.
'The "Regular" and "Decaf" buttons will bring up the appropriate order forms. The "Display
'Bill" button will display the bill for the total coffee order. The "Exit" Button will 
'close the program. The Regular and Decaf order forms will allow the user to select the
'brand of coffee, size of the package and number of units to order. The user can then use
'the "Add to Order" button to add the order to the total order, "Clear" button to clear
'input fields before another order, or "Close" button to close the order form and return to
'the main form.

Option Strict On

Public Class MainForm

'********************************************************************************************
'This procedure creates an instance of the RegularCoffeeForm and displays it for the user.
'********************************************************************************************
    Private Sub btnRegular_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnRegular.Click
                'create a new RegularCoffeeForm instance
        Dim frmRegular As New RegularCoffeeForm
                'display the form to the user
        frmRegular.ShowDialog()
    End Sub

'********************************************************************************************
'This procedure creates an instance of the DecafCoffeeForm and displays it for the user.
'********************************************************************************************
    Private Sub btnDecaf_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnDecaf.Click
                'create a new DecafCoffeeForm instance
        Dim frmDecaf As New DecafCoffeeForm
                'display the form to the user
        frmDecaf.ShowDialog()
    End Sub

'********************************************************************************************
'This procedure creates an instance of the DisplayBillForm and displays it for the user.
'********************************************************************************************
    Private Sub btnDisplayBill_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnDisplayBill.Click
                'ensure coffee has been ordered before displaying the bill form
        If g_decSubTotal > 0.0 Then
                'create a new instance of the DisplayBillForm
            Dim frmDisplay As New DisplayBillForm
                'display the form to the user
            frmDisplay.ShowDialog()
        Else
                'display amessage informing the user that no coffee has been ordered
            MessageBox.Show("No coffee has been ordered.", "Place Order", MessageBoxButtons.OK,
                            MessageBoxIcon.Information)
        End If
        
    End Sub

'********************************************************************************************
'This procedure closes the program
'********************************************************************************************
    Private Sub btnExit_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnExit.Click
                'close the form
        Me.Close()
    End Sub
End Class
