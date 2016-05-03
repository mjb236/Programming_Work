'DisplayBillForm

Option Strict On

Public Class DisplayBillForm
'********************************************************************************************
'This procedure will display the date, subtotal, tax and total order cost when the form loads
'********************************************************************************************
    Private Sub DisplayBillForm_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
                'declare variable for total order
        Dim decTotal As Decimal

                'display current date in the date label
        lblDate.Text = Today().ToString("d")
                'display subtotal
        lblSubtotal.Text = g_decSubTotal.ToString("c")
                'display tax
        lblTax.Text = Taxes().ToString("c")
                'calculate and display total
        decTotal = g_decSubTotal + Taxes()
        lblTotal.Text = decTotal.ToString("c")
    End Sub

'********************************************************************************************
'This procedure will reset the subtotal for a new order and close the form to return to main
'********************************************************************************************
    Private Sub btnClose_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnClose.Click
                'reset the subtotal
        g_decSubTotal = 0D
                'close the form
        Me.Close()
    End Sub
End Class