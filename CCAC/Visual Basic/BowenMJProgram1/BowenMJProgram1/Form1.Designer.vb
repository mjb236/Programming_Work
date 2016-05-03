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
Me.Label1 = New System.Windows.Forms.Label()
Me.txtAmountSpent = New System.Windows.Forms.TextBox()
Me.GroupBox1 = New System.Windows.Forms.GroupBox()
Me.lblPennies = New System.Windows.Forms.Label()
Me.lblNickels = New System.Windows.Forms.Label()
Me.lblDimes = New System.Windows.Forms.Label()
Me.Label5 = New System.Windows.Forms.Label()
Me.Label4 = New System.Windows.Forms.Label()
Me.Label3 = New System.Windows.Forms.Label()
Me.Label2 = New System.Windows.Forms.Label()
Me.lblQuarters = New System.Windows.Forms.Label()
Me.btnCalculate = New System.Windows.Forms.Button()
Me.btnClear = New System.Windows.Forms.Button()
Me.btnExit = New System.Windows.Forms.Button()
Me.GroupBox1.SuspendLayout()
Me.SuspendLayout()
'
'Label1
'
Me.Label1.AutoSize = True
Me.Label1.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.Label1.Location = New System.Drawing.Point(23, 19)
Me.Label1.Name = "Label1"
Me.Label1.Size = New System.Drawing.Size(162, 16)
Me.Label1.TabIndex = 0
Me.Label1.Text = "Enter total spent (in cents):"
Me.Label1.TextAlign = System.Drawing.ContentAlignment.MiddleLeft
'
'txtAmountSpent
'
Me.txtAmountSpent.Location = New System.Drawing.Point(191, 19)
Me.txtAmountSpent.Name = "txtAmountSpent"
Me.txtAmountSpent.Size = New System.Drawing.Size(69, 20)
Me.txtAmountSpent.TabIndex = 0
Me.txtAmountSpent.TextAlign = System.Windows.Forms.HorizontalAlignment.Right
'
'GroupBox1
'
Me.GroupBox1.Controls.Add(Me.lblPennies)
Me.GroupBox1.Controls.Add(Me.lblNickels)
Me.GroupBox1.Controls.Add(Me.lblDimes)
Me.GroupBox1.Controls.Add(Me.Label5)
Me.GroupBox1.Controls.Add(Me.Label4)
Me.GroupBox1.Controls.Add(Me.Label3)
Me.GroupBox1.Controls.Add(Me.Label2)
Me.GroupBox1.Controls.Add(Me.lblQuarters)
Me.GroupBox1.Location = New System.Drawing.Point(23, 68)
Me.GroupBox1.Name = "GroupBox1"
Me.GroupBox1.Size = New System.Drawing.Size(145, 156)
Me.GroupBox1.TabIndex = 2
Me.GroupBox1.TabStop = False
Me.GroupBox1.Text = "Change for $1"
'
'lblPennies
'
Me.lblPennies.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D
Me.lblPennies.Location = New System.Drawing.Point(20, 121)
Me.lblPennies.Name = "lblPennies"
Me.lblPennies.Size = New System.Drawing.Size(33, 20)
Me.lblPennies.TabIndex = 7
Me.lblPennies.TextAlign = System.Drawing.ContentAlignment.MiddleRight
'
'lblNickels
'
Me.lblNickels.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D
Me.lblNickels.Location = New System.Drawing.Point(20, 88)
Me.lblNickels.Name = "lblNickels"
Me.lblNickels.Size = New System.Drawing.Size(33, 20)
Me.lblNickels.TabIndex = 6
Me.lblNickels.TextAlign = System.Drawing.ContentAlignment.MiddleRight
'
'lblDimes
'
Me.lblDimes.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D
Me.lblDimes.Location = New System.Drawing.Point(20, 55)
Me.lblDimes.Name = "lblDimes"
Me.lblDimes.Size = New System.Drawing.Size(33, 20)
Me.lblDimes.TabIndex = 5
Me.lblDimes.TextAlign = System.Drawing.ContentAlignment.MiddleRight
'
'Label5
'
Me.Label5.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.Label5.Location = New System.Drawing.Point(73, 121)
Me.Label5.Name = "Label5"
Me.Label5.Size = New System.Drawing.Size(70, 23)
Me.Label5.TabIndex = 4
Me.Label5.Text = "Pennies"
Me.Label5.TextAlign = System.Drawing.ContentAlignment.MiddleLeft
'
'Label4
'
Me.Label4.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.Label4.Location = New System.Drawing.Point(73, 88)
Me.Label4.Name = "Label4"
Me.Label4.Size = New System.Drawing.Size(70, 23)
Me.Label4.TabIndex = 3
Me.Label4.Text = "Nickels"
Me.Label4.TextAlign = System.Drawing.ContentAlignment.MiddleLeft
'
'Label3
'
Me.Label3.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.Label3.Location = New System.Drawing.Point(73, 55)
Me.Label3.Name = "Label3"
Me.Label3.Size = New System.Drawing.Size(70, 23)
Me.Label3.TabIndex = 2
Me.Label3.Text = "Dimes"
Me.Label3.TextAlign = System.Drawing.ContentAlignment.MiddleLeft
'
'Label2
'
Me.Label2.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.Label2.Location = New System.Drawing.Point(73, 20)
Me.Label2.Name = "Label2"
Me.Label2.Size = New System.Drawing.Size(70, 23)
Me.Label2.TabIndex = 1
Me.Label2.Text = "Quarters"
Me.Label2.TextAlign = System.Drawing.ContentAlignment.MiddleLeft
'
'lblQuarters
'
Me.lblQuarters.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D
Me.lblQuarters.Location = New System.Drawing.Point(20, 22)
Me.lblQuarters.Name = "lblQuarters"
Me.lblQuarters.Size = New System.Drawing.Size(33, 20)
Me.lblQuarters.TabIndex = 0
Me.lblQuarters.TextAlign = System.Drawing.ContentAlignment.MiddleRight
'
'btnCalculate
'
Me.btnCalculate.Location = New System.Drawing.Point(185, 76)
Me.btnCalculate.Name = "btnCalculate"
Me.btnCalculate.Size = New System.Drawing.Size(75, 23)
Me.btnCalculate.TabIndex = 1
Me.btnCalculate.Text = "&Calculate"
Me.btnCalculate.UseVisualStyleBackColor = True
'
'btnClear
'
Me.btnClear.DialogResult = System.Windows.Forms.DialogResult.Cancel
Me.btnClear.Location = New System.Drawing.Point(185, 138)
Me.btnClear.Name = "btnClear"
Me.btnClear.Size = New System.Drawing.Size(75, 23)
Me.btnClear.TabIndex = 2
Me.btnClear.Text = "C&lear"
Me.btnClear.UseVisualStyleBackColor = True
'
'btnExit
'
Me.btnExit.Location = New System.Drawing.Point(185, 201)
Me.btnExit.Name = "btnExit"
Me.btnExit.Size = New System.Drawing.Size(75, 23)
Me.btnExit.TabIndex = 3
Me.btnExit.Text = "E&xit"
Me.btnExit.UseVisualStyleBackColor = True
'
'Form1
'
Me.AcceptButton = Me.btnCalculate
Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
Me.BackColor = System.Drawing.SystemColors.ControlLight
Me.CancelButton = Me.btnClear
Me.ClientSize = New System.Drawing.Size(280, 245)
Me.Controls.Add(Me.btnExit)
Me.Controls.Add(Me.btnClear)
Me.Controls.Add(Me.btnCalculate)
Me.Controls.Add(Me.GroupBox1)
Me.Controls.Add(Me.txtAmountSpent)
Me.Controls.Add(Me.Label1)
Me.Name = "Form1"
Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
Me.Text = "Change Maker for $1"
Me.GroupBox1.ResumeLayout(False)
Me.ResumeLayout(False)
Me.PerformLayout()

End Sub
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents txtAmountSpent As System.Windows.Forms.TextBox
    Friend WithEvents GroupBox1 As System.Windows.Forms.GroupBox
    Friend WithEvents lblPennies As System.Windows.Forms.Label
    Friend WithEvents lblNickels As System.Windows.Forms.Label
    Friend WithEvents lblDimes As System.Windows.Forms.Label
    Friend WithEvents Label5 As System.Windows.Forms.Label
    Friend WithEvents Label4 As System.Windows.Forms.Label
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents lblQuarters As System.Windows.Forms.Label
    Friend WithEvents btnCalculate As System.Windows.Forms.Button
    Friend WithEvents btnClear As System.Windows.Forms.Button
    Friend WithEvents btnExit As System.Windows.Forms.Button

End Class
