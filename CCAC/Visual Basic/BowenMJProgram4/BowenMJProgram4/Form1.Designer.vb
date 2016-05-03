<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Form1
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
Me.components = New System.ComponentModel.Container()
Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(Form1))
Me.Label1 = New System.Windows.Forms.Label()
Me.lstProduct = New System.Windows.Forms.ListBox()
Me.Label2 = New System.Windows.Forms.Label()
Me.nudQuantity = New System.Windows.Forms.NumericUpDown()
Me.Label3 = New System.Windows.Forms.Label()
Me.lblPrice = New System.Windows.Forms.Label()
Me.Label5 = New System.Windows.Forms.Label()
Me.lblItem = New System.Windows.Forms.Label()
Me.Label7 = New System.Windows.Forms.Label()
Me.lblCost = New System.Windows.Forms.Label()
Me.btnOrder = New System.Windows.Forms.Button()
Me.btnPrint = New System.Windows.Forms.Button()
Me.btnReport = New System.Windows.Forms.Button()
Me.btnExit = New System.Windows.Forms.Button()
Me.ShapeContainer1 = New Microsoft.VisualBasic.PowerPacks.ShapeContainer()
Me.LineShape2 = New Microsoft.VisualBasic.PowerPacks.LineShape()
Me.LineShape1 = New Microsoft.VisualBasic.PowerPacks.LineShape()
Me.ToolTip1 = New System.Windows.Forms.ToolTip(Me.components)
Me.pdPrintOrderReport = New System.Drawing.Printing.PrintDocument()
Me.pvPrintPreviewOrder = New System.Windows.Forms.PrintPreviewDialog()
Me.pdPrintInventoryReport = New System.Drawing.Printing.PrintDocument()
Me.pvPrintPreviewInventory = New System.Windows.Forms.PrintPreviewDialog()
CType(Me.nudQuantity, System.ComponentModel.ISupportInitialize).BeginInit()
Me.SuspendLayout()
'
'Label1
'
Me.Label1.AutoSize = True
Me.Label1.Location = New System.Drawing.Point(10, 20)
Me.Label1.Name = "Label1"
Me.Label1.Size = New System.Drawing.Size(61, 16)
Me.Label1.TabIndex = 1
Me.Label1.Text = "Product"
'
'lstProduct
'
Me.lstProduct.FormattingEnabled = True
Me.lstProduct.ImeMode = System.Windows.Forms.ImeMode.NoControl
Me.lstProduct.ItemHeight = 16
Me.lstProduct.Location = New System.Drawing.Point(13, 42)
Me.lstProduct.Name = "lstProduct"
Me.lstProduct.Size = New System.Drawing.Size(58, 84)
Me.lstProduct.TabIndex = 0
'
'Label2
'
Me.Label2.AutoSize = True
Me.Label2.Location = New System.Drawing.Point(122, 20)
Me.Label2.Name = "Label2"
Me.Label2.Size = New System.Drawing.Size(64, 16)
Me.Label2.TabIndex = 3
Me.Label2.Text = "Quantity"
'
'nudQuantity
'
Me.nudQuantity.Location = New System.Drawing.Point(125, 42)
Me.nudQuantity.Maximum = New Decimal(New Integer() {5, 0, 0, 0})
Me.nudQuantity.Minimum = New Decimal(New Integer() {1, 0, 0, 0})
Me.nudQuantity.Name = "nudQuantity"
Me.nudQuantity.Size = New System.Drawing.Size(61, 22)
Me.nudQuantity.TabIndex = 1
Me.nudQuantity.Value = New Decimal(New Integer() {1, 0, 0, 0})
'
'Label3
'
Me.Label3.AutoSize = True
Me.Label3.Location = New System.Drawing.Point(235, 20)
Me.Label3.Name = "Label3"
Me.Label3.Size = New System.Drawing.Size(44, 16)
Me.Label3.TabIndex = 5
Me.Label3.Text = "Price"
'
'lblPrice
'
Me.lblPrice.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
Me.lblPrice.Location = New System.Drawing.Point(238, 42)
Me.lblPrice.Name = "lblPrice"
Me.lblPrice.Size = New System.Drawing.Size(54, 22)
Me.lblPrice.TabIndex = 6
'
'Label5
'
Me.Label5.AutoSize = True
Me.Label5.Location = New System.Drawing.Point(122, 166)
Me.Label5.Name = "Label5"
Me.Label5.Size = New System.Drawing.Size(41, 16)
Me.Label5.TabIndex = 7
Me.Label5.Text = "Item:"
'
'lblItem
'
Me.lblItem.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
Me.lblItem.Location = New System.Drawing.Point(125, 195)
Me.lblItem.Name = "lblItem"
Me.lblItem.Size = New System.Drawing.Size(71, 22)
Me.lblItem.TabIndex = 8
'
'Label7
'
Me.Label7.AutoSize = True
Me.Label7.Location = New System.Drawing.Point(235, 166)
Me.Label7.Name = "Label7"
Me.Label7.Size = New System.Drawing.Size(43, 16)
Me.Label7.TabIndex = 9
Me.Label7.Text = "Cost:"
'
'lblCost
'
Me.lblCost.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
Me.lblCost.Location = New System.Drawing.Point(238, 195)
Me.lblCost.Name = "lblCost"
Me.lblCost.Size = New System.Drawing.Size(71, 22)
Me.lblCost.TabIndex = 10
'
'btnOrder
'
Me.btnOrder.Enabled = False
Me.btnOrder.ForeColor = System.Drawing.SystemColors.ControlText
Me.btnOrder.Location = New System.Drawing.Point(12, 265)
Me.btnOrder.Name = "btnOrder"
Me.btnOrder.Size = New System.Drawing.Size(75, 23)
Me.btnOrder.TabIndex = 2
Me.btnOrder.Text = "&Order"
Me.ToolTip1.SetToolTip(Me.btnOrder, "Click to order the selected item.")
Me.btnOrder.UseVisualStyleBackColor = True
'
'btnPrint
'
Me.btnPrint.Enabled = False
Me.btnPrint.ForeColor = System.Drawing.SystemColors.ControlText
Me.btnPrint.Location = New System.Drawing.Point(93, 265)
Me.btnPrint.Name = "btnPrint"
Me.btnPrint.Size = New System.Drawing.Size(74, 23)
Me.btnPrint.TabIndex = 3
Me.btnPrint.Text = "&Print"
Me.ToolTip1.SetToolTip(Me.btnPrint, "Click to print order.")
Me.btnPrint.UseVisualStyleBackColor = True
'
'btnReport
'
Me.btnReport.ForeColor = System.Drawing.SystemColors.ControlText
Me.btnReport.Location = New System.Drawing.Point(173, 265)
Me.btnReport.Name = "btnReport"
Me.btnReport.Size = New System.Drawing.Size(74, 23)
Me.btnReport.TabIndex = 4
Me.btnReport.Text = "&Report"
Me.ToolTip1.SetToolTip(Me.btnReport, "Click to print current inventory report.")
Me.btnReport.UseVisualStyleBackColor = True
'
'btnExit
'
Me.btnExit.ForeColor = System.Drawing.SystemColors.ControlText
Me.btnExit.Location = New System.Drawing.Point(253, 265)
Me.btnExit.Name = "btnExit"
Me.btnExit.Size = New System.Drawing.Size(75, 23)
Me.btnExit.TabIndex = 5
Me.btnExit.Text = "E&xit"
Me.ToolTip1.SetToolTip(Me.btnExit, "Click to end the program.")
Me.btnExit.UseVisualStyleBackColor = True
'
'ShapeContainer1
'
Me.ShapeContainer1.Location = New System.Drawing.Point(0, 0)
Me.ShapeContainer1.Margin = New System.Windows.Forms.Padding(0)
Me.ShapeContainer1.Name = "ShapeContainer1"
Me.ShapeContainer1.Shapes.AddRange(New Microsoft.VisualBasic.PowerPacks.Shape() {Me.LineShape2, Me.LineShape1})
Me.ShapeContainer1.Size = New System.Drawing.Size(340, 305)
Me.ShapeContainer1.TabIndex = 0
Me.ShapeContainer1.TabStop = False
'
'LineShape2
'
Me.LineShape2.BorderWidth = 3
Me.LineShape2.Name = "LineShape2"
Me.LineShape2.X1 = 11
Me.LineShape2.X2 = 323
Me.LineShape2.Y1 = 242
Me.LineShape2.Y2 = 242
'
'LineShape1
'
Me.LineShape1.BorderWidth = 3
Me.LineShape1.Name = "LineShape1"
Me.LineShape1.X1 = 13
Me.LineShape1.X2 = 325
Me.LineShape1.Y1 = 147
Me.LineShape1.Y2 = 147
'
'pdPrintOrderReport
'
'
'pvPrintPreviewOrder
'
Me.pvPrintPreviewOrder.AutoScrollMargin = New System.Drawing.Size(0, 0)
Me.pvPrintPreviewOrder.AutoScrollMinSize = New System.Drawing.Size(0, 0)
Me.pvPrintPreviewOrder.ClientSize = New System.Drawing.Size(400, 300)
Me.pvPrintPreviewOrder.Enabled = True
Me.pvPrintPreviewOrder.Icon = CType(resources.GetObject("pvPrintPreviewOrder.Icon"), System.Drawing.Icon)
Me.pvPrintPreviewOrder.Name = "pvPrintPreviewOrder"
Me.pvPrintPreviewOrder.Visible = False
'
'pdPrintInventoryReport
'
'
'pvPrintPreviewInventory
'
Me.pvPrintPreviewInventory.AutoScrollMargin = New System.Drawing.Size(0, 0)
Me.pvPrintPreviewInventory.AutoScrollMinSize = New System.Drawing.Size(0, 0)
Me.pvPrintPreviewInventory.ClientSize = New System.Drawing.Size(400, 300)
Me.pvPrintPreviewInventory.Enabled = True
Me.pvPrintPreviewInventory.Icon = CType(resources.GetObject("pvPrintPreviewInventory.Icon"), System.Drawing.Icon)
Me.pvPrintPreviewInventory.Name = "pvPrintPreviewInventory"
Me.pvPrintPreviewInventory.Visible = False
'
'Form1
'
Me.AcceptButton = Me.btnOrder
Me.AutoScaleDimensions = New System.Drawing.SizeF(9.0!, 16.0!)
Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
Me.BackColor = System.Drawing.Color.DarkSeaGreen
Me.ClientSize = New System.Drawing.Size(340, 305)
Me.Controls.Add(Me.btnExit)
Me.Controls.Add(Me.btnReport)
Me.Controls.Add(Me.btnPrint)
Me.Controls.Add(Me.btnOrder)
Me.Controls.Add(Me.lblCost)
Me.Controls.Add(Me.Label7)
Me.Controls.Add(Me.lblItem)
Me.Controls.Add(Me.Label5)
Me.Controls.Add(Me.lblPrice)
Me.Controls.Add(Me.Label3)
Me.Controls.Add(Me.nudQuantity)
Me.Controls.Add(Me.Label2)
Me.Controls.Add(Me.lstProduct)
Me.Controls.Add(Me.Label1)
Me.Controls.Add(Me.ShapeContainer1)
Me.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.Margin = New System.Windows.Forms.Padding(4)
Me.Name = "Form1"
Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
Me.Text = "Order Entry"
CType(Me.nudQuantity, System.ComponentModel.ISupportInitialize).EndInit()
Me.ResumeLayout(False)
Me.PerformLayout()

End Sub
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents lstProduct As System.Windows.Forms.ListBox
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents nudQuantity As System.Windows.Forms.NumericUpDown
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents lblPrice As System.Windows.Forms.Label
    Friend WithEvents Label5 As System.Windows.Forms.Label
    Friend WithEvents lblItem As System.Windows.Forms.Label
    Friend WithEvents Label7 As System.Windows.Forms.Label
    Friend WithEvents lblCost As System.Windows.Forms.Label
    Friend WithEvents btnOrder As System.Windows.Forms.Button
    Friend WithEvents btnPrint As System.Windows.Forms.Button
    Friend WithEvents btnReport As System.Windows.Forms.Button
    Friend WithEvents btnExit As System.Windows.Forms.Button
    Friend WithEvents ShapeContainer1 As Microsoft.VisualBasic.PowerPacks.ShapeContainer
    Friend WithEvents LineShape2 As Microsoft.VisualBasic.PowerPacks.LineShape
    Friend WithEvents LineShape1 As Microsoft.VisualBasic.PowerPacks.LineShape
    Friend WithEvents ToolTip1 As System.Windows.Forms.ToolTip
    Friend WithEvents pdPrintOrderReport As System.Drawing.Printing.PrintDocument
    Friend WithEvents pvPrintPreviewOrder As System.Windows.Forms.PrintPreviewDialog
    Friend WithEvents pdPrintInventoryReport As System.Drawing.Printing.PrintDocument
    Friend WithEvents pvPrintPreviewInventory As System.Windows.Forms.PrintPreviewDialog

End Class
