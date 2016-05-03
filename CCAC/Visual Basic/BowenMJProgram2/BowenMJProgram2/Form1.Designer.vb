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
Me.txtTemperature = New System.Windows.Forms.TextBox()
Me.GroupBox1 = New System.Windows.Forms.GroupBox()
Me.radSecondTech = New System.Windows.Forms.RadioButton()
Me.radFirstTech = New System.Windows.Forms.RadioButton()
Me.Label2 = New System.Windows.Forms.Label()
Me.lblAverage = New System.Windows.Forms.Label()
Me.chkCelsius = New System.Windows.Forms.CheckBox()
Me.lblCelsius = New System.Windows.Forms.Label()
Me.btnCalculate = New System.Windows.Forms.Button()
Me.btnClear = New System.Windows.Forms.Button()
Me.btnExit = New System.Windows.Forms.Button()
Me.PictureBox1 = New System.Windows.Forms.PictureBox()
Me.GroupBox1.SuspendLayout()
CType(Me.PictureBox1, System.ComponentModel.ISupportInitialize).BeginInit()
Me.SuspendLayout()
'
'Label1
'
Me.Label1.AutoSize = True
Me.Label1.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.Label1.Location = New System.Drawing.Point(21, 21)
Me.Label1.Name = "Label1"
Me.Label1.Size = New System.Drawing.Size(117, 16)
Me.Label1.TabIndex = 0
Me.Label1.Text = "Enter &temperature:"
'
'txtTemperature
'
Me.txtTemperature.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.txtTemperature.Location = New System.Drawing.Point(209, 18)
Me.txtTemperature.Name = "txtTemperature"
Me.txtTemperature.Size = New System.Drawing.Size(67, 22)
Me.txtTemperature.TabIndex = 1
'
'GroupBox1
'
Me.GroupBox1.Controls.Add(Me.radSecondTech)
Me.GroupBox1.Controls.Add(Me.radFirstTech)
Me.GroupBox1.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.GroupBox1.Location = New System.Drawing.Point(308, 18)
Me.GroupBox1.Name = "GroupBox1"
Me.GroupBox1.Size = New System.Drawing.Size(107, 100)
Me.GroupBox1.TabIndex = 2
Me.GroupBox1.TabStop = False
Me.GroupBox1.Text = "Technician"
'
'radSecondTech
'
Me.radSecondTech.AutoSize = True
Me.radSecondTech.Location = New System.Drawing.Point(22, 63)
Me.radSecondTech.Name = "radSecondTech"
Me.radSecondTech.Size = New System.Drawing.Size(44, 20)
Me.radSecondTech.TabIndex = 1
Me.radSecondTech.TabStop = True
Me.radSecondTech.Text = "&Bill"
Me.radSecondTech.UseVisualStyleBackColor = True
'
'radFirstTech
'
Me.radFirstTech.AutoSize = True
Me.radFirstTech.Location = New System.Drawing.Point(22, 27)
Me.radFirstTech.Name = "radFirstTech"
Me.radFirstTech.Size = New System.Drawing.Size(56, 20)
Me.radFirstTech.TabIndex = 0
Me.radFirstTech.TabStop = True
Me.radFirstTech.Text = "C&hris"
Me.radFirstTech.UseVisualStyleBackColor = True
'
'Label2
'
Me.Label2.AutoSize = True
Me.Label2.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.Label2.Location = New System.Drawing.Point(21, 72)
Me.Label2.Name = "Label2"
Me.Label2.Size = New System.Drawing.Size(138, 16)
Me.Label2.TabIndex = 3
Me.Label2.Text = "Average temperature:"
'
'lblAverage
'
Me.lblAverage.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.lblAverage.Location = New System.Drawing.Point(206, 69)
Me.lblAverage.Name = "lblAverage"
Me.lblAverage.Size = New System.Drawing.Size(70, 23)
Me.lblAverage.TabIndex = 4
Me.lblAverage.TextAlign = System.Drawing.ContentAlignment.MiddleLeft
'
'chkCelsius
'
Me.chkCelsius.AutoSize = True
Me.chkCelsius.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.chkCelsius.Location = New System.Drawing.Point(24, 124)
Me.chkCelsius.Name = "chkCelsius"
Me.chkCelsius.Size = New System.Drawing.Size(149, 20)
Me.chkCelsius.TabIndex = 5
Me.chkCelsius.Text = "C&elsius temperature:"
Me.chkCelsius.UseVisualStyleBackColor = True
Me.chkCelsius.Visible = False
'
'lblCelsius
'
Me.lblCelsius.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.lblCelsius.Location = New System.Drawing.Point(206, 122)
Me.lblCelsius.Name = "lblCelsius"
Me.lblCelsius.Size = New System.Drawing.Size(70, 23)
Me.lblCelsius.TabIndex = 6
Me.lblCelsius.TextAlign = System.Drawing.ContentAlignment.MiddleLeft
Me.lblCelsius.Visible = False
'
'btnCalculate
'
Me.btnCalculate.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.btnCalculate.Location = New System.Drawing.Point(24, 177)
Me.btnCalculate.Name = "btnCalculate"
Me.btnCalculate.Size = New System.Drawing.Size(75, 32)
Me.btnCalculate.TabIndex = 7
Me.btnCalculate.Text = "&Calculate"
Me.btnCalculate.UseVisualStyleBackColor = True
'
'btnClear
'
Me.btnClear.DialogResult = System.Windows.Forms.DialogResult.Cancel
Me.btnClear.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.btnClear.Location = New System.Drawing.Point(122, 177)
Me.btnClear.Name = "btnClear"
Me.btnClear.Size = New System.Drawing.Size(75, 32)
Me.btnClear.TabIndex = 8
Me.btnClear.Text = "C&lear"
Me.btnClear.UseVisualStyleBackColor = True
'
'btnExit
'
Me.btnExit.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.btnExit.Location = New System.Drawing.Point(219, 177)
Me.btnExit.Name = "btnExit"
Me.btnExit.Size = New System.Drawing.Size(75, 32)
Me.btnExit.TabIndex = 9
Me.btnExit.Text = "E&xit"
Me.btnExit.UseVisualStyleBackColor = True
'
'PictureBox1
'
Me.PictureBox1.Image = Global.BowenMJProgram2.My.Resources.Resources.Thermometer
Me.PictureBox1.Location = New System.Drawing.Point(308, 136)
Me.PictureBox1.Name = "PictureBox1"
Me.PictureBox1.Size = New System.Drawing.Size(107, 73)
Me.PictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage
Me.PictureBox1.TabIndex = 10
Me.PictureBox1.TabStop = False
'
'Form1
'
Me.AcceptButton = Me.btnCalculate
Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
Me.BackColor = System.Drawing.SystemColors.ControlLight
Me.CancelButton = Me.btnClear
Me.ClientSize = New System.Drawing.Size(433, 227)
Me.Controls.Add(Me.PictureBox1)
Me.Controls.Add(Me.btnExit)
Me.Controls.Add(Me.btnClear)
Me.Controls.Add(Me.btnCalculate)
Me.Controls.Add(Me.lblCelsius)
Me.Controls.Add(Me.chkCelsius)
Me.Controls.Add(Me.lblAverage)
Me.Controls.Add(Me.Label2)
Me.Controls.Add(Me.GroupBox1)
Me.Controls.Add(Me.txtTemperature)
Me.Controls.Add(Me.Label1)
Me.Name = "Form1"
Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
Me.Text = "Temperature Program"
Me.GroupBox1.ResumeLayout(False)
Me.GroupBox1.PerformLayout()
CType(Me.PictureBox1, System.ComponentModel.ISupportInitialize).EndInit()
Me.ResumeLayout(False)
Me.PerformLayout()

End Sub
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents txtTemperature As System.Windows.Forms.TextBox
    Friend WithEvents GroupBox1 As System.Windows.Forms.GroupBox
    Friend WithEvents radSecondTech As System.Windows.Forms.RadioButton
    Friend WithEvents radFirstTech As System.Windows.Forms.RadioButton
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents lblAverage As System.Windows.Forms.Label
    Friend WithEvents chkCelsius As System.Windows.Forms.CheckBox
    Friend WithEvents lblCelsius As System.Windows.Forms.Label
    Friend WithEvents btnCalculate As System.Windows.Forms.Button
    Friend WithEvents btnClear As System.Windows.Forms.Button
    Friend WithEvents btnExit As System.Windows.Forms.Button
    Friend WithEvents PictureBox1 As System.Windows.Forms.PictureBox

End Class
