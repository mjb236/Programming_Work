'Michael Bowen
'
'Program 4
'
'This program will allow the user to order a product, in quantities from 1 to 5,
'provided there is enough inventory to accommodate the order. If there is an
'insufficient quantity, an error message is displayed. The inventory is loaded
'from a text file on application start and orders are saved to another text files
'upon being placed. The product codes are displayed in a list box, number to order
'is selected using a numeric up/down, and the price of each product is displayed in 
'a label when that product is selected. The Order button places the order and displays
'the product ordered and the total cost. The Print button generates a print preview of
'the order placed. The Report button displays a print preview of the current inventory,
'including product code, number remaining, and cost per. The Exit button closes the
'program.

Option Strict On

                'import needed for file reading/writing
Imports System.IO

Public Class Form1
                'declare a Structure to store product information
    Structure ProductRecord
        Dim strProduct As String
        Dim intQuantityOnHand As Integer
        Dim decPrice As Decimal
    End Structure

                'declare an array of the ProductRecord structure to store product info
    Dim prcProducts() As ProductRecord

'--------------------------------------------------------------------------------------
'This procedure will update the product list box with the product codes using the 
'prcProducts array
'--------------------------------------------------------------------------------------
    Private Sub LoadProductList()
                'clear the product list of any previous data
        lstProduct.Items.Clear()
                'use a for loop to populate lstProduct with product codes
        For intCount = 0 To (prcProducts.Length - 1)
            lstProduct.Items.Add(prcProducts(intCount).strProduct)
        Next
    End Sub

'--------------------------------------------------------------------------------------
'This procedure will remove a product from the prcProducts array by shifting prcProducts in
'the array at a higher subscript to a lower subscript. It will also redim the array to
'it's new, correct size.
'--------------------------------------------------------------------------------------
    Private Sub RemoveProduct(ByVal intIndex As Integer)
                'declare a loop count variable
        Dim intCount As Integer

                'ensure there are at least two items in the array before proceeding
        If prcProducts.Length >= 2 Then
                'shift prcProducts in the array to remove the out of stock product
            For intCount = intIndex To (prcProducts.Length - 2)
                With prcProducts(intCount)
                    .strProduct = prcProducts(intCount + 1).strProduct
                    .intQuantityOnHand = prcProducts(intCount + 1).intQuantityOnHand
                    .decPrice = prcProducts(intCount + 1).decPrice
                End With
            Next

                're-declare the prcProducts array with its new size and preserve it's data
            ReDim Preserve prcProducts(prcProducts.Length - 2)
        Else
                'if the prcProducts array has only 1 remaining item, it is by default the
                'item needing to be removed. ReDim, but do not preserve the array data,
                'set the strProduct field to String.Empty, and disable controls that
                'will no longer be usable as there is no inventory remaining
            ReDim prcProducts(0)
            prcProducts(0).strProduct = String.Empty

            With lstProduct
                .Items.Clear()
                .Enabled = False
            End With
            nudQuantity.Enabled = False
            btnOrder.Enabled = False
        End If
    End Sub

'***************************************************************************************
'This procedure will read productfile.txt, store the product info in the prcProducts array
'and populate lstProduct with the product codes.
'***************************************************************************************
    Private Sub Form1_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
                'declare constants number of prcProducts and input file name
        Const intNUM_PRODUCTS As Integer = 5
        Const strPRODUCT_FILE As String = "Products.txt"
                'declare variables needed to read the file
        Dim productFile As StreamReader
        Dim intCount As Integer

                're-declare the product array using the number of prcProducts
        ReDim prcProducts(intNUM_PRODUCTS - 1)

        Try
                'open the file
            productFile = File.OpenText(strPRODUCT_FILE)

            Try
                'use a for loop to read the data from the file and store into the product array
                For intCount = 0 To (intNUM_PRODUCTS - 1)
                    With prcProducts(intCount)
                        .strProduct = productFile.ReadLine()
                        .intQuantityOnHand = CInt(productFile.ReadLine())
                        .decPrice = CDec(productFile.ReadLine())
                    End With
                Next
            Catch ex As Exception
                'error message for invalid data type
                MessageBox.Show("Invalid data type in " & strPRODUCT_FILE, "Invalid Data",
                                MessageBoxButtons.OK, MessageBoxIcon.Error)
            End Try

                'close the file
            productFile.Close()
        Catch ex As Exception
                'error message if unable to open the file
            MessageBox.Show("Unable to open " & strPRODUCT_FILE, "File Error",
                            MessageBoxButtons.OK, MessageBoxIcon.Error)
        End Try

                'call the LoadProductList procedure to update the list box
        LoadProductList()
    End Sub

'***************************************************************************************
'This procedure sets the focus to the exit button once the form is finished loading
'to ensure the desired tab order is followed.
'***************************************************************************************
    Private Sub Form1_Shown(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Shown
        btnExit.Focus()
    End Sub

'***************************************************************************************
'This procedure will display the price of the selected product when the user selects a 
'product code or changes the product selection. Also enables the Order button.
'***************************************************************************************
    Private Sub lstProduct_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles lstProduct.SelectedIndexChanged
                'update price label if selected index is valid
        If lstProduct.SelectedIndex <> -1 Then
            lblPrice.Text = prcProducts(lstProduct.SelectedIndex).decPrice.ToString("c")
                'enable the Order button
            btnOrder.Enabled = True
        End If
    End Sub

'***************************************************************************************
'This procedure place's the user's order and writes the order to the output text file.
'***************************************************************************************
    Private Sub btnOrder_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnOrder.Click
                'declare variables for cost, index of selected product and number to order
        Dim decOrderCost As Decimal
        Dim intIndex As Integer
        Dim intNumOrdered As Integer
                'declare variable for writing output to the file and constant for out file name
        Dim outputFile As StreamWriter
        Const strOUTPUT_FILE As String = "OrderOutput.txt"

                'get the index of the selected product and number ordered
        intIndex = lstProduct.SelectedIndex
        intNumOrdered = CInt(nudQuantity.Value)
                'make sure there is enough inventory to fulfill the order before proceeding

        If prcProducts(intIndex).intQuantityOnHand < intNumOrdered Then
            MessageBox.Show("Insufficient quantity in inventory.", "Order cannot be processed", MessageBoxButtons.OK,
                             MessageBoxIcon.Error)
        Else
                'calculate order cost
            decOrderCost = prcProducts(intIndex).decPrice * intNumOrdered
                'display order information to appropriate labels
            lblCost.Text = decOrderCost.ToString("c")
            lblItem.Text = prcProducts(intIndex).strProduct
                'enable/disable appropriate controls
            btnOrder.Enabled = False
            lstProduct.Enabled = False
            nudQuantity.Enabled = False
            btnPrint.Enabled = True

            Try
                'open the output file
                outputFile = File.AppendText(strOUTPUT_FILE)

                'write the order to the file
                outputFile.WriteLine(prcProducts(intIndex).strProduct)
                outputFile.WriteLine(decOrderCost.ToString("n2"))

                'close the file
                outputFile.Close()
            Catch ex As Exception
                'error message for file creation error
                MessageBox.Show("Error creating file " & strOUTPUT_FILE, "File Creation Error", MessageBoxButtons.OK,
                                MessageBoxIcon.Error)
            End Try

                'call the RemoveProduct procedure if the order depleted the entire reamining stock of
                'the selected product, otherwise subtract the number ordered from the quantity on hand
            If prcProducts(intIndex).intQuantityOnHand = intNumOrdered Then
                RemoveProduct(intIndex)
                'call the LoadProductList procedure to update the list box
                LoadProductList()
            Else
                prcProducts(intIndex).intQuantityOnHand -= intNumOrdered
            End If

                'set focus to the print button
            btnPrint.Focus()
        End If
    End Sub

'***************************************************************************************
'This procedure creates the document for printing the customer order
'***************************************************************************************
    Private Sub pdPrintOrderReport_PrintPage(ByVal sender As System.Object, ByVal e As System.Drawing.Printing.PrintPageEventArgs) Handles pdPrintOrderReport.PrintPage
                'declare a font object
        Dim fntPrintFont As New Font("Courier New", 10)
                'declare variables for position on screen and line height
        Dim sngX As Single = e.MarginBounds.Left
        Dim sngY As Single = e.MarginBounds.Top
        Dim sngLineHeight As Single = fntPrintFont.GetHeight() + 2
                'declare a constant for the string format
        Const strFORMAT As String = "{0,-7}{1,5}{2,-6}"

                'draw the order to the page
        e.Graphics.DrawString(String.Format(strFORMAT, String.Empty, "Order", String.Empty),
                              fntPrintFont, Brushes.Black, sngX, sngY)
        sngY += (sngLineHeight * 2)
        e.Graphics.DrawString(String.Format(strFORMAT, "Product", String.Empty, "Cost"),
                              fntPrintFont, Brushes.Black, sngX, sngY)
        sngY += sngLineHeight
        e.Graphics.DrawString(String.Format(strFORMAT, "-------", "-----", "------"),
                              fntPrintFont, Brushes.Black, sngX, sngY)
        sngY += sngLineHeight
        e.Graphics.DrawString(String.Format(strFORMAT, lblItem.Text, String.Empty, lblCost.Text),
                              fntPrintFont, Brushes.Black, sngX, sngY)
    End Sub

'***************************************************************************************
'This procedure generates a print preview screen of the order and enable/resets controls
'as appropriate.
'***************************************************************************************
    Private Sub btnPrint_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnPrint.Click
                'create and display the print preview dialog
        pvPrintPreviewOrder.Document = pdPrintOrderReport
        pvPrintPreviewOrder.PrintPreviewControl.Zoom = 1
        pvPrintPreviewOrder.ShowDialog()

                'reset selections for the list box and numeric up/down
        lstProduct.SelectedIndex = -1
        nudQuantity.Value = 1
                'clear labels
        lblCost.Text = String.Empty
        lblItem.Text = String.Empty
        lblPrice.Text = String.Empty
                'disable the print button
        btnPrint.Enabled = False
                'enable the list box and numeric up/down if there are prcProducts remaining
        If prcProducts(0).strProduct <> String.Empty Then
            With lstProduct
                .ClearSelected()
                .Enabled = True
            End With
            nudQuantity.Enabled = True
        End If
                'set focus to the exit button
        btnExit.Focus()
    End Sub

'***************************************************************************************
'This procedure creates the document for printing the current inventory
'***************************************************************************************
    Private Sub pdPrintInventoryReport_PrintPage(ByVal sender As System.Object, ByVal e As System.Drawing.Printing.PrintPageEventArgs) Handles pdPrintInventoryReport.PrintPage
                'declare a font object
        Dim fntPrintFont As New Font("Courier New", 10)
                'declare variables for position on screen and line height
        Dim sngX As Single = e.MarginBounds.Left
        Dim sngY As Single = e.MarginBounds.Top
        Dim sngLineHeight As Single = fntPrintFont.GetHeight() + 2
                'declare a loop counter
        Dim intCount As Integer

                'draw the report header
        e.Graphics.DrawString("        Inventory Report", fntPrintFont, Brushes.Black, sngX, sngY)
        sngY += (sngLineHeight * 2)
        e.Graphics.DrawString("Product" & vbTab & "Quantity" & vbTab & "Price", fntPrintFont,
                              Brushes.Black, sngX, sngY)
        sngY += sngLineHeight
        e.Graphics.DrawString("-------------------------------", fntPrintFont,
                              Brushes.Black, sngX, sngY)

                'make sure there is still inventory left to display before printing data
        If prcProducts(0).strProduct <> String.Empty Then
                'use a for loop to draw the body of the report from the product array
            For intCount = 0 To (prcProducts.Length - 1)
                sngY += sngLineHeight
                e.Graphics.DrawString(prcProducts(intCount).strProduct & vbTab & vbTab &
                                      prcProducts(intCount).intQuantityOnHand.ToString() & vbTab & vbTab &
                                      prcProducts(intCount).decPrice.ToString("c"), fntPrintFont, Brushes.Black,
                                      sngX, sngY)
            Next
        End If
    End Sub

'***************************************************************************************
'This procedure generates a print preview screen showing the current prcProducts in stock,
'the number of each product remaining and how much each product costs
'***************************************************************************************
    Private Sub btnReport_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnReport.Click
                'create and display the print preview dialog
        pvPrintPreviewInventory.Document = pdPrintInventoryReport
        pvPrintPreviewInventory.PrintPreviewControl.Zoom = 1
        pvPrintPreviewInventory.ShowDialog()
    End Sub

'***************************************************************************************
'This procedure closes the program
'***************************************************************************************
    Private Sub btnExit_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnExit.Click
                'close the form
        Me.Close()
    End Sub
End Class
