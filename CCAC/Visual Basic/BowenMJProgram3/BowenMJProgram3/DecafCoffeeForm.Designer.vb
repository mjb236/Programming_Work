<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class DecafCoffeeForm
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
Me.btnClose = New System.Windows.Forms.Button()
Me.btnClear = New System.Windows.Forms.Button()
Me.btnAdd = New System.Windows.Forms.Button()
Me.nudQuantity = New System.Windows.Forms.NumericUpDown()
Me.Label3 = New System.Windows.Forms.Label()
Me.lstSize = New System.Windows.Forms.ListBox()
Me.Label2 = New System.Windows.Forms.Label()
Me.cboBrand = New System.Windows.Forms.ComboBox()
Me.Label1 = New System.Windows.Forms.Label()
Me.ToolTip1 = New System.Windows.Forms.ToolTip(Me.components)
CType(Me.nudQuantity, System.ComponentModel.ISupportInitialize).BeginInit()
Me.SuspendLayout()
'
'btnClose
'
Me.btnClose.Location = New System.Drawing.Point(255, 212)
Me.btnClose.Name = "btnClose"
Me.btnClose.Size = New System.Drawing.Size(95, 37)
Me.btnClose.TabIndex = 17
Me.btnClose.Text = "C&lose"
Me.ToolTip1.SetToolTip(Me.btnClose, "Click here to close this form and return to the main form.")
Me.btnClose.UseVisualStyleBackColor = True
'
'btnClear
'
Me.btnClear.Location = New System.Drawing.Point(142, 212)
Me.btnClear.Name = "btnClear"
Me.btnClear.Size = New System.Drawing.Size(95, 37)
Me.btnClear.TabIndex = 16
Me.btnClear.Text = "&Clear"
Me.ToolTip1.SetToolTip(Me.btnClear, "Click here to clear input entries for another selection.")
Me.btnClear.UseVisualStyleBackColor = True
'
'btnAdd
'
Me.btnAdd.Location = New System.Drawing.Point(28, 212)
Me.btnAdd.Name = "btnAdd"
Me.btnAdd.Size = New System.Drawing.Size(95, 37)
Me.btnAdd.TabIndex = 15
Me.btnAdd.Text = "&Add to Order"
Me.ToolTip1.SetToolTip(Me.btnAdd, "Click here to include selected coffee in order.")
Me.btnAdd.UseVisualStyleBackColor = True
'
'nudQuantity
'
Me.nudQuantity.Location = New System.Drawing.Point(176, 156)
Me.nudQuantity.Minimum = New Decimal(New Integer() {1, 0, 0, 0})
Me.nudQuantity.Name = "nudQuantity"
Me.nudQuantity.Size = New System.Drawing.Size(51, 22)
Me.nudQuantity.TabIndex = 14
Me.nudQuantity.Value = New Decimal(New Integer() {1, 0, 0, 0})
'
'Label3
'
Me.Label3.AutoSize = True
Me.Label3.Location = New System.Drawing.Point(50, 158)
Me.Label3.Name = "Label3"
Me.Label3.Size = New System.Drawing.Size(98, 16)
Me.Label3.TabIndex = 13
Me.Label3.Text = "Select quantity:"
'
'lstSize
'
Me.lstSize.FormattingEnabled = True
Me.lstSize.ItemHeight = 16
Me.lstSize.Items.AddRange(New Object() {"One Pound", "Two Pounds", "Ten Pounds"})
Me.lstSize.Location = New System.Drawing.Point(176, 73)
Me.lstSize.Name = "lstSize"
Me.lstSize.Size = New System.Drawing.Size(121, 52)
Me.lstSize.TabIndex = 12
'
'Label2
'
Me.Label2.AutoSize = True
Me.Label2.Location = New System.Drawing.Point(72, 73)
Me.Label2.Name = "Label2"
Me.Label2.Size = New System.Drawing.Size(76, 16)
Me.Label2.TabIndex = 11
Me.Label2.Text = "Select size:"
'
'cboBrand
'
Me.cboBrand.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
Me.cboBrand.FormattingEnabled = True
Me.cboBrand.Location = New System.Drawing.Point(176, 24)
Me.cboBrand.Name = "cboBrand"
Me.cboBrand.Size = New System.Drawing.Size(121, 24)
Me.cboBrand.TabIndex = 10
'
'Label1
'
Me.Label1.AutoSize = True
Me.Label1.Location = New System.Drawing.Point(61, 27)
Me.Label1.Name = "Label1"
Me.Label1.Size = New System.Drawing.Size(87, 16)
Me.Label1.TabIndex = 9
Me.Label1.Text = "Select brand:"
'
'DecafCoffeeForm
'
Me.AutoScaleDimensions = New System.Drawing.SizeF(8.0!, 16.0!)
Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
Me.BackColor = System.Drawing.Color.Beige
Me.ClientSize = New System.Drawing.Size(379, 272)
Me.ControlBox = False
Me.Controls.Add(Me.btnClose)
Me.Controls.Add(Me.btnClear)
Me.Controls.Add(Me.btnAdd)
Me.Controls.Add(Me.nudQuantity)
Me.Controls.Add(Me.Label3)
Me.Controls.Add(Me.lstSize)
Me.Controls.Add(Me.Label2)
Me.Controls.Add(Me.cboBrand)
Me.Controls.Add(Me.Label1)
Me.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.Margin = New System.Windows.Forms.Padding(4)
Me.Name = "DecafCoffeeForm"
Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
Me.Text = "Decaf Coffee Order"
CType(Me.nudQuantity, System.ComponentModel.ISupportInitialize).EndInit()
Me.ResumeLayout(False)
Me.PerformLayout()

End Sub
    Friend WithEvents btnClose As System.Windows.Forms.Button
    Friend WithEvents btnClear As System.Windows.Forms.Button
    Friend WithEvents btnAdd As System.Windows.Forms.Button
    Friend WithEvents nudQuantity As System.Windows.Forms.NumericUpDown
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents lstSize As System.Windows.Forms.ListBox
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents cboBrand As System.Windows.Forms.ComboBox
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents ToolTip1 As System.Windows.Forms.ToolTip
End Class
