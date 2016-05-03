<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class MainForm
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
Me.Label2 = New System.Windows.Forms.Label()
Me.GroupBox1 = New System.Windows.Forms.GroupBox()
Me.btnDecaf = New System.Windows.Forms.Button()
Me.btnRegular = New System.Windows.Forms.Button()
Me.btnDisplayBill = New System.Windows.Forms.Button()
Me.btnExit = New System.Windows.Forms.Button()
Me.ToolTip1 = New System.Windows.Forms.ToolTip(Me.components)
Me.GroupBox1.SuspendLayout()
Me.SuspendLayout()
'
'Label1
'
Me.Label1.Font = New System.Drawing.Font("Comic Sans MS", 11.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.Label1.Location = New System.Drawing.Point(12, 9)
Me.Label1.Name = "Label1"
Me.Label1.Size = New System.Drawing.Size(355, 35)
Me.Label1.TabIndex = 0
Me.Label1.Text = "The Fair Trade Coffee Bazaar Order System"
Me.Label1.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
'
'Label2
'
Me.Label2.BackColor = System.Drawing.SystemColors.Control
Me.Label2.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D
Me.Label2.Font = New System.Drawing.Font("Arial", 9.75!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.Label2.Location = New System.Drawing.Point(55, 55)
Me.Label2.Name = "Label2"
Me.Label2.Size = New System.Drawing.Size(268, 64)
Me.Label2.TabIndex = 1
Me.Label2.Text = "For each customer, enter the type of" & Global.Microsoft.VisualBasic.ChrW(13) & Global.Microsoft.VisualBasic.ChrW(10) & "coffee, the brand of coffee, the size" & Global.Microsoft.VisualBasic.ChrW(13) & Global.Microsoft.VisualBasic.ChrW(10) & "and " & _
    "the quantity."
'
'GroupBox1
'
Me.GroupBox1.BackColor = System.Drawing.SystemColors.Control
Me.GroupBox1.Controls.Add(Me.btnDecaf)
Me.GroupBox1.Controls.Add(Me.btnRegular)
Me.GroupBox1.Font = New System.Drawing.Font("Arial", 9.75!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.GroupBox1.Location = New System.Drawing.Point(55, 140)
Me.GroupBox1.Name = "GroupBox1"
Me.GroupBox1.Size = New System.Drawing.Size(268, 62)
Me.GroupBox1.TabIndex = 2
Me.GroupBox1.TabStop = False
Me.GroupBox1.Text = "Type of Coffee"
'
'btnDecaf
'
Me.btnDecaf.BackColor = System.Drawing.SystemColors.ControlLight
Me.btnDecaf.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.btnDecaf.Location = New System.Drawing.Point(142, 21)
Me.btnDecaf.Name = "btnDecaf"
Me.btnDecaf.Size = New System.Drawing.Size(103, 27)
Me.btnDecaf.TabIndex = 1
Me.btnDecaf.Text = "&Decaf"
Me.ToolTip1.SetToolTip(Me.btnDecaf, "Click here to order decaf coffee.")
Me.btnDecaf.UseVisualStyleBackColor = False
'
'btnRegular
'
Me.btnRegular.BackColor = System.Drawing.SystemColors.ControlLight
Me.btnRegular.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.btnRegular.Location = New System.Drawing.Point(22, 21)
Me.btnRegular.Name = "btnRegular"
Me.btnRegular.Size = New System.Drawing.Size(103, 27)
Me.btnRegular.TabIndex = 0
Me.btnRegular.Text = "&Regular"
Me.ToolTip1.SetToolTip(Me.btnRegular, "Click here to order regular coffee.")
Me.btnRegular.UseVisualStyleBackColor = False
'
'btnDisplayBill
'
Me.btnDisplayBill.BackColor = System.Drawing.SystemColors.Control
Me.btnDisplayBill.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.btnDisplayBill.Location = New System.Drawing.Point(77, 224)
Me.btnDisplayBill.Name = "btnDisplayBill"
Me.btnDisplayBill.Size = New System.Drawing.Size(103, 27)
Me.btnDisplayBill.TabIndex = 3
Me.btnDisplayBill.Text = "Dis&play Bill"
Me.ToolTip1.SetToolTip(Me.btnDisplayBill, "Click here to display bill for ordered coffee.")
Me.btnDisplayBill.UseVisualStyleBackColor = False
'
'btnExit
'
Me.btnExit.BackColor = System.Drawing.SystemColors.Control
Me.btnExit.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.btnExit.Location = New System.Drawing.Point(197, 224)
Me.btnExit.Name = "btnExit"
Me.btnExit.Size = New System.Drawing.Size(103, 27)
Me.btnExit.TabIndex = 4
Me.btnExit.Text = "E&xit"
Me.ToolTip1.SetToolTip(Me.btnExit, "Click here to end the program.")
Me.btnExit.UseVisualStyleBackColor = False
'
'MainForm
'
Me.AcceptButton = Me.btnRegular
Me.AutoScaleDimensions = New System.Drawing.SizeF(8.0!, 16.0!)
Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
Me.BackColor = System.Drawing.SystemColors.ControlLight
Me.ClientSize = New System.Drawing.Size(379, 272)
Me.ControlBox = False
Me.Controls.Add(Me.btnExit)
Me.Controls.Add(Me.btnDisplayBill)
Me.Controls.Add(Me.GroupBox1)
Me.Controls.Add(Me.Label2)
Me.Controls.Add(Me.Label1)
Me.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.Margin = New System.Windows.Forms.Padding(4)
Me.MaximizeBox = False
Me.MinimizeBox = False
Me.Name = "MainForm"
Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
Me.Text = "The Fair Trade Coffee Bazaar"
Me.GroupBox1.ResumeLayout(False)
Me.ResumeLayout(False)

End Sub
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents GroupBox1 As System.Windows.Forms.GroupBox
    Friend WithEvents btnDecaf As System.Windows.Forms.Button
    Friend WithEvents btnRegular As System.Windows.Forms.Button
    Friend WithEvents btnDisplayBill As System.Windows.Forms.Button
    Friend WithEvents btnExit As System.Windows.Forms.Button
    Friend WithEvents ToolTip1 As System.Windows.Forms.ToolTip

End Class
