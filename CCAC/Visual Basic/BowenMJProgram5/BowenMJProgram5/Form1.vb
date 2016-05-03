'Michael Bowen
'
'Program 5
'
'This program will connect to a kayak database to allow the user to view
'information about different types of kayaks. The list box will display 
'the names of the different types of kayaks. When the user selects a 
'kayak from the list, a description of that type of kayak will be displayed
'in the label to the right.

Option Strict On

Public Class Form1

Private Sub Form1_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
'TODO: This line of code loads data into the 'KayaksDataSet.KayakTypes' table. You can move, or remove it, as needed.
Me.KayakTypesTableAdapter.Fill(Me.KayaksDataSet.KayakTypes)

End Sub
End Class
