<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class DisplayBillForm
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
Me.Label1 = New System.Windows.Forms.Label()
Me.lblDate = New System.Windows.Forms.Label()
Me.Label2 = New System.Windows.Forms.Label()
Me.lblSubtotal = New System.Windows.Forms.Label()
Me.lblTax = New System.Windows.Forms.Label()
Me.lblTotal = New System.Windows.Forms.Label()
Me.Label6 = New System.Windows.Forms.Label()
Me.Label7 = New System.Windows.Forms.Label()
Me.btnClose = New System.Windows.Forms.Button()
Me.ToolTip1 = New System.Windows.Forms.ToolTip(Me.components)
Me.SuspendLayout()
'
'Label1
'
Me.Label1.Font = New System.Drawing.Font("Comic Sans MS", 11.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.Label1.Location = New System.Drawing.Point(12, 0)
Me.Label1.Name = "Label1"
Me.Label1.Size = New System.Drawing.Size(355, 35)
Me.Label1.TabIndex = 1
Me.Label1.Text = "The Fair Trade Coffee Bazaar Customer Bill"
Me.Label1.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
'
'lblDate
'
Me.lblDate.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
Me.lblDate.Location = New System.Drawing.Point(143, 44)
Me.lblDate.Name = "lblDate"
Me.lblDate.Size = New System.Drawing.Size(93, 23)
Me.lblDate.TabIndex = 2
Me.lblDate.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
'
'Label2
'
Me.Label2.AutoSize = True
Me.Label2.Location = New System.Drawing.Point(107, 95)
Me.Label2.Name = "Label2"
Me.Label2.Size = New System.Drawing.Size(60, 16)
Me.Label2.TabIndex = 3
Me.Label2.Text = "Subtotal:"
'
'lblSubtotal
'
Me.lblSubtotal.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
Me.lblSubtotal.Location = New System.Drawing.Point(200, 92)
Me.lblSubtotal.Name = "lblSubtotal"
Me.lblSubtotal.Size = New System.Drawing.Size(93, 23)
Me.lblSubtotal.TabIndex = 4
Me.lblSubtotal.TextAlign = System.Drawing.ContentAlignment.MiddleRight
'
'lblTax
'
Me.lblTax.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
Me.lblTax.Location = New System.Drawing.Point(200, 126)
Me.lblTax.Name = "lblTax"
Me.lblTax.Size = New System.Drawing.Size(93, 23)
Me.lblTax.TabIndex = 5
Me.lblTax.TextAlign = System.Drawing.ContentAlignment.MiddleRight
'
'lblTotal
'
Me.lblTotal.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
Me.lblTotal.Location = New System.Drawing.Point(200, 182)
Me.lblTotal.Name = "lblTotal"
Me.lblTotal.Size = New System.Drawing.Size(93, 23)
Me.lblTotal.TabIndex = 6
Me.lblTotal.TextAlign = System.Drawing.ContentAlignment.MiddleRight
'
'Label6
'
Me.Label6.AutoSize = True
Me.Label6.Location = New System.Drawing.Point(133, 129)
Me.Label6.Name = "Label6"
Me.Label6.Size = New System.Drawing.Size(34, 16)
Me.Label6.TabIndex = 7
Me.Label6.Text = "Tax:"
'
'Label7
'
Me.Label7.AutoSize = True
Me.Label7.Location = New System.Drawing.Point(125, 185)
Me.Label7.Name = "Label7"
Me.Label7.Size = New System.Drawing.Size(42, 16)
Me.Label7.TabIndex = 8
Me.Label7.Text = "Total:"
'
'btnClose
'
Me.btnClose.Location = New System.Drawing.Point(143, 232)
Me.btnClose.Name = "btnClose"
Me.btnClose.Size = New System.Drawing.Size(93, 28)
Me.btnClose.TabIndex = 9
Me.btnClose.Text = "C&lose"
Me.ToolTip1.SetToolTip(Me.btnClose, "Click here to close this form and return to the main form.")
Me.btnClose.UseVisualStyleBackColor = True
'
'DisplayBillForm
'
Me.AutoScaleDimensions = New System.Drawing.SizeF(8.0!, 16.0!)
Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
Me.BackColor = System.Drawing.SystemColors.ControlLight
Me.ClientSize = New System.Drawing.Size(379, 272)
Me.ControlBox = False
Me.Controls.Add(Me.btnClose)
Me.Controls.Add(Me.Label7)
Me.Controls.Add(Me.Label6)
Me.Controls.Add(Me.lblTotal)
Me.Controls.Add(Me.lblTax)
Me.Controls.Add(Me.lblSubtotal)
Me.Controls.Add(Me.Label2)
Me.Controls.Add(Me.lblDate)
Me.Controls.Add(Me.Label1)
Me.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.Margin = New System.Windows.Forms.Padding(4)
Me.Name = "DisplayBillForm"
Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
Me.Text = "DisplayBillForm"
Me.ResumeLayout(False)
Me.PerformLayout()

End Sub
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents lblDate As System.Windows.Forms.Label
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents lblSubtotal As System.Windows.Forms.Label
    Friend WithEvents lblTax As System.Windows.Forms.Label
    Friend WithEvents lblTotal As System.Windows.Forms.Label
    Friend WithEvents Label6 As System.Windows.Forms.Label
    Friend WithEvents Label7 As System.Windows.Forms.Label
    Friend WithEvents btnClose As System.Windows.Forms.Button
    Friend WithEvents ToolTip1 As System.Windows.Forms.ToolTip
End Class
