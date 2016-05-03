'RegularCoffeeForm

Option Strict On

Public Class RegularCoffeeForm
'***************************************************************************************************
'This procedure will use a for loop to populate the combo box with coffee brands when the form loads
'***************************************************************************************************
    Private Sub RegularCoffeeForm_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
                'declare a loop counter
        Dim intCounter As Integer = 1
                'populate the combo box with coffee brands
        For intCounter = 1 To 5
            cboBrand.Items.Add("Regular " & intCounter.ToString())
        Next
                'ensure the first item is the default selection
        cboBrand.SelectedIndex = 0
    End Sub

'***************************************************************************************************
'This procedure will gather user input and call the AddToOrder procedure with the appropriate values
'It will also validate user input and show a message box displaying the current subtotal
'***************************************************************************************************
    Private Sub btnAdd_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnAdd.Click
                'declare necessary constants
        Const decONE_POUND As Decimal = 8D
        Const decTWO_POUNDS As Decimal = 15D
        Const decTEN_POUNDS As Decimal = 70D
                'declare varables for the selected size index and quantity ordered
        Dim intSizeSelected As Integer = -1
        Dim intQuantity As Integer

        Try
                'get the index of the selected size and the quantity ordered from the user's input
            intSizeSelected = lstSize.SelectedIndex
            intQuantity = CInt(nudQuantity.Value)
                'make sure a size was selected
            If intSizeSelected <> -1 Then
                'use a select case statement to call the AddToOrder Procedure using the appropriate values
                Select Case intSizeSelected
                    Case 0
                        AddToOrder(decONE_POUND, intQuantity)
                    Case 1
                        AddToOrder(decTWO_POUNDS, intQuantity)
                    Case 2
                        AddToOrder(decTEN_POUNDS, intQuantity)
                End Select

                'display a message box with the current subtotal
                MessageBox.Show("The current subtotal is " & g_decSubTotal.ToString("c"), "Subtotal", MessageBoxButtons.OK,
                                MessageBoxIcon.Information)
            Else
                'display an error message instructing the user to select a size to order
                MessageBox.Show("Please select a size of package to order.", "Size Required", MessageBoxButtons.OK,
                                MessageBoxIcon.Error)
            End If
        Catch ex As Exception
                'display an error message instructing the user to enter valid numeric data
            MessageBox.Show("Please enter valid numeric data.", "Numeric Data Required", MessageBoxButtons.OK,
                            MessageBoxIcon.Error)
        End Try
    End Sub

'***************************************************************************************************
'This procedure will reset user input fields to allow the user to place another order
'***************************************************************************************************
    Private Sub btnClear_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnClear.Click
                'clear/reset input fields
        cboBrand.SelectedIndex = 0
        lstSize.SelectedIndex = -1
        nudQuantity.Value = 1
    End Sub

'***************************************************************************************************
'This procedure will close the form and return to the main form
'***************************************************************************************************
    Private Sub btnClose_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnClose.Click
                'close the form
        Me.Close()
    End Sub
End Class