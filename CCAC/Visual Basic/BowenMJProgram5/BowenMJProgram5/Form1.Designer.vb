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
Me.Label1 = New System.Windows.Forms.Label()
Me.lstKayak = New System.Windows.Forms.ListBox()
Me.KayakTypesBindingSource = New System.Windows.Forms.BindingSource(Me.components)
Me.KayaksDataSet = New BowenMJProgram5.KayaksDataSet()
Me.KayakTypesTableAdapter = New BowenMJProgram5.KayaksDataSetTableAdapters.KayakTypesTableAdapter()
Me.TableAdapterManager = New BowenMJProgram5.KayaksDataSetTableAdapters.TableAdapterManager()
Me.DescriptionLabel1 = New System.Windows.Forms.Label()
CType(Me.KayakTypesBindingSource, System.ComponentModel.ISupportInitialize).BeginInit()
CType(Me.KayaksDataSet, System.ComponentModel.ISupportInitialize).BeginInit()
Me.SuspendLayout()
'
'Label1
'
Me.Label1.AutoSize = True
Me.Label1.Location = New System.Drawing.Point(27, 30)
Me.Label1.Name = "Label1"
Me.Label1.Size = New System.Drawing.Size(250, 16)
Me.Label1.TabIndex = 0
Me.Label1.Text = "Select a kayak type to view a description"
'
'lstKayak
'
Me.lstKayak.DataSource = Me.KayakTypesBindingSource
Me.lstKayak.DisplayMember = "Name"
Me.lstKayak.FormattingEnabled = True
Me.lstKayak.ItemHeight = 16
Me.lstKayak.Location = New System.Drawing.Point(30, 72)
Me.lstKayak.Name = "lstKayak"
Me.lstKayak.Size = New System.Drawing.Size(163, 164)
Me.lstKayak.TabIndex = 1
'
'KayakTypesBindingSource
'
Me.KayakTypesBindingSource.DataMember = "KayakTypes"
Me.KayakTypesBindingSource.DataSource = Me.KayaksDataSet
'
'KayaksDataSet
'
Me.KayaksDataSet.DataSetName = "KayaksDataSet"
Me.KayaksDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema
'
'KayakTypesTableAdapter
'
Me.KayakTypesTableAdapter.ClearBeforeFill = True
'
'TableAdapterManager
'
Me.TableAdapterManager.BackupDataSetBeforeUpdate = False
Me.TableAdapterManager.KayakTypesTableAdapter = Me.KayakTypesTableAdapter
Me.TableAdapterManager.UpdateOrder = BowenMJProgram5.KayaksDataSetTableAdapters.TableAdapterManager.UpdateOrderOption.InsertUpdateDelete
'
'DescriptionLabel1
'
Me.DescriptionLabel1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
Me.DescriptionLabel1.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.KayakTypesBindingSource, "Description", True))
Me.DescriptionLabel1.Location = New System.Drawing.Point(235, 72)
Me.DescriptionLabel1.Name = "DescriptionLabel1"
Me.DescriptionLabel1.Size = New System.Drawing.Size(206, 164)
Me.DescriptionLabel1.TabIndex = 3
Me.DescriptionLabel1.Text = "Label2"
'
'Form1
'
Me.AutoScaleDimensions = New System.Drawing.SizeF(8.0!, 16.0!)
Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
Me.BackColor = System.Drawing.Color.LightSalmon
Me.ClientSize = New System.Drawing.Size(476, 272)
Me.Controls.Add(Me.DescriptionLabel1)
Me.Controls.Add(Me.lstKayak)
Me.Controls.Add(Me.Label1)
Me.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
Me.Margin = New System.Windows.Forms.Padding(4)
Me.Name = "Form1"
Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
Me.Text = "Kayak Browser"
CType(Me.KayakTypesBindingSource, System.ComponentModel.ISupportInitialize).EndInit()
CType(Me.KayaksDataSet, System.ComponentModel.ISupportInitialize).EndInit()
Me.ResumeLayout(False)
Me.PerformLayout()

End Sub
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents lstKayak As System.Windows.Forms.ListBox
    Friend WithEvents KayaksDataSet As BowenMJProgram5.KayaksDataSet
    Friend WithEvents KayakTypesBindingSource As System.Windows.Forms.BindingSource
    Friend WithEvents KayakTypesTableAdapter As BowenMJProgram5.KayaksDataSetTableAdapters.KayakTypesTableAdapter
    Friend WithEvents TableAdapterManager As BowenMJProgram5.KayaksDataSetTableAdapters.TableAdapterManager
    Friend WithEvents DescriptionLabel1 As System.Windows.Forms.Label

End Class
