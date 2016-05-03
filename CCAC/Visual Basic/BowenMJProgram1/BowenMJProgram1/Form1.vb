'Michael Bowen
'
'Program 1
'
'This program will calculate the number of quarters, dimes, nickels and pennies to
'return to a customer based on how many cents of one dollar they have spent. The user
'will enter the amount spent in cents. The change is calculated and displayed as the
'number of each coin to return when the user clicks or otherwise accesses the 
'"Calculate" button. The "Clear" button or the escape key will clear the input textbox 
'and output labels and return focus to the input textbox. The "Exit" button will close 
'the program.

Option Strict On

Public Class Form1
'**************************************************************************************************************
'This procedure calculates the number of each coin that should be returned to the customer
'and displays the results in the appropriate labels.
'**************************************************************************************************************
    Private Sub btnCalculate_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnCalculate.Click
                'declare constants for the values for quaters, nickels and dimes
        Const intQUARTER_VALUE As Integer = 25
        Const intDIME_VALUE As Integer = 10
        Const intNICKEL_VALUE As Integer = 5

                'declare variables used to calculate and store the number of coins to return
        Dim intRemainingChange As Integer = 100
        Dim intAmountSpent As Integer
        Dim intQuarters As Integer
        Dim intDimes As Integer
        Dim intNickels As Integer
        Dim intPennies As Integer

        Try
                'get the amount spent from the user
            intAmountSpent = CInt(txtAmountSpent.Text)
                'calculate the total change remaining
            intRemainingChange -= intAmountSpent
                'calculate the number of quarters
            intQuarters = intRemainingChange \ intQUARTER_VALUE
                'calcuate the amount of change remaining after quarters
            intRemainingChange = intRemainingChange Mod intQUARTER_VALUE
                'calculate the number of dimes
            intDimes = intRemainingChange \ intDIME_VALUE
                'calculate the amount of change remaining after dimes
            intRemainingChange = intRemainingChange Mod intDIME_VALUE
                'calculate the number of nickels
            intNickels = intRemainingChange \ intNICKEL_VALUE
                'calculate the amount of change remaining after nickels
                'by default, this value will also be the number of pennies
            intPennies = intRemainingChange Mod intNICKEL_VALUE

                'display the results in the appropriate labels
            lblQuarters.Text = intQuarters.ToString()
            lblDimes.Text = intDimes.ToString()
            lblNickels.Text = intNickels.ToString()
            lblPennies.Text = intPennies.ToString()
        Catch ex As Exception
                'display an error message if the input is invalid
            MessageBox.Show("Please enter a valid numeric value.")
        End Try
    End Sub

'**************************************************************************************************************
'This procedure will clear the output and input areas of all data and return focus to the input textbox
'**************************************************************************************************************
    Private Sub btnClear_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnClear.Click
                'clear the input textbox
        txtAmountSpent.Text = String.Empty
                'clear the output labels
        lblQuarters.Text = String.Empty
        lblDimes.Text = String.Empty
        lblNickels.Text = String.Empty
        lblPennies.Text = String.Empty

                'return focus to the input textbox
        txtAmountSpent.Focus()
    End Sub

'**************************************************************************************************************
'This procedure exits the program
'**************************************************************************************************************
    Private Sub btnExit_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnExit.Click
                'close the form
        Me.Close()
    End Sub
End Class
