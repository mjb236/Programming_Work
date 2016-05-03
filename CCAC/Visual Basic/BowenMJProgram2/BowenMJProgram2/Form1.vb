'Michael Bowen
'
'Program 2
'
'This program calulates the total average of temperatures entered by two technicians.
'The user enters Farenheit temperatures and selects one of two technicians. When the 
'"Calculate" button is accessed, the average temperature is diplayed and a check box
'is displayed that will allow the user to view the entered temperature as Celsius. This
'check box will calculate and display the Celsius temperature when it is clicked.
'The "Clear" button will clear input and output text boxes and labels and return the
'focus to the input text box. The "Exit" button will display a message box with summary
'statistics and then end the program.

Option Strict On

Public Class Form1
                'declare global variables for number of total entries for each technician
                'and the total temperature accumulated
    Dim dblTotalTemperature As Double = 0.0
    Dim intFirstTechEntries As Integer = 0
    Dim intSecondTechEntries As Integer = 0

'************************************************************************************************
'This procedure will calculate and display the Celsius temperature when the Celsius check box
'is checked
'************************************************************************************************
    Private Sub chkCelsius_CheckedChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles chkCelsius.CheckedChanged
                'declare variables to hold the input temperature and Celsius temperature
        Dim dblCelsiusTemperature As Double
        Dim dblInputTemperature As Double

        Try
                'make sure the checkchange event made the check box selected before proceeding
            If chkCelsius.Checked = True Then
                'get the user's input from the temperature text box
                dblInputTemperature = CDbl(txtTemperature.Text)

                'calcuate the Celsius temperature and display result
                dblCelsiusTemperature = (5.0 / 9.0) * (dblInputTemperature - 32)
                lblCelsius.Text = dblCelsiusTemperature.ToString("n1")
                lblCelsius.Visible = True

                'set focus to the clear button
                btnClear.Focus()
            End If
        Catch ex As Exception
                'display a generic error message
            MessageBox.Show("Error", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error)
        End Try
    End Sub

'************************************************************************************************
'This procedure will accept the user's Farenheit temp input, calculate and display the running
'average temperature, increment the appropriate counter for the technician that entered the data 
'and make the Celsius check box visible should the user wish to know the temperature in Celsius. 
'In addition, the procedure will display suitable error messages for invalid input.
'************************************************************************************************
    Private Sub btnCalculate_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnCalculate.Click
                'declare local variables for input temperature and average temperature
        Dim dblAverage As Double = 0.0
        Dim dblInputTemperature As Double = 0.0

        Try
                'check for input validity and perform calculations if input valid
            If txtTemperature.Text <> String.Empty Then
                'assign user input to the dblInputTemperate variable
                dblInputTemperature = CDbl(txtTemperature.Text)

                'make sure the input temperature is within the valid range
                If dblInputTemperature >= 32.0 And dblInputTemperature <= 80.0 Then
                'determine if a techinician is selected - if so, calculate average and display
                    If radFirstTech.Checked = True Or radSecondTech.Checked = True Then
                'add input temperature to the running total temperature
                    dblTotalTemperature += dblInputTemperature

                'determine which technician is selected and increment the correct entries variable 
                        If radFirstTech.Checked = True Then
                            intFirstTechEntries += 1
                        Else
                            intSecondTechEntries += 1
                        End If

                'calculate and display the current average temperature
                        dblAverage = dblTotalTemperature / (intFirstTechEntries + intSecondTechEntries)
                        lblAverage.Text = dblAverage.ToString("n1")

                'display the Celsius checkbox
                        chkCelsius.Visible = True

                'set focus to the clear button
                        btnClear.Focus()
                    Else
                'display an error message if neither technician is selected
                    MessageBox.Show("Please select a technician.", "Technician Required", MessageBoxButtons.OK,
                                    MessageBoxIcon.Error)
                    End If
                Else
                'display an error message if the temperature is out of the correct range
                        MessageBox.Show("Please enter a temperature between 32.0 and 80.0.", "Invalid Temperature",
                                        MessageBoxButtons.OK, MessageBoxIcon.Error)
                        txtTemperature.Focus()
                End If
            Else
                'display error message if user did not enter a temperature
                MessageBox.Show("Please enter a temperature.", "Temperature Required", MessageBoxButtons.OK,
                                MessageBoxIcon.Error)
                txtTemperature.Focus()
            End If
        Catch ex As Exception
                'display an error message if the input is not numeric
            MessageBox.Show("Please enter a valid numeric value.", "Numeric Temperature Required",
                            MessageBoxButtons.OK, MessageBoxIcon.Error)
            txtTemperature.Focus()
        End Try
    End Sub

'************************************************************************************************
'This procedure will clear the text box, output labels and radio buttons. It will hide the Celsius 
'check box and label and return the focus to the text box to accept more input from the user.
'************************************************************************************************
    Private Sub btnClear_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnClear.Click
                'clear labels and text boxes
        txtTemperature.Clear()
        lblAverage.Text = String.Empty
        lblCelsius.Text = String.Empty

                'hide the Celsius check box and label
        chkCelsius.Visible = False
        lblCelsius.Visible = False

                'uncheck both radio buttons and the check box
        chkCelsius.Checked = False
        radFirstTech.Checked = False
        radSecondTech.Checked = False
                'ensure the radio buttons can be tabbed to post-clear
        radFirstTech.TabStop = True

                'return focus to the input text box
        txtTemperature.Focus()
    End Sub
'************************************************************************************************
'This procedure will display a message box with the how many temperature entries each technician
'entered and the overall average temperature and then it will end the program.
'************************************************************************************************
    Private Sub btnExit_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnExit.Click
                'declare local variable for the average temperature
        Dim dblAverageTemperature As Double = 0.0

                'calculate the average temperature if there were entries given
                'this will prevent possible division by 0
        If (intSecondTechEntries + intFirstTechEntries) <> 0 Then
            dblAverageTemperature = dblTotalTemperature / (intSecondTechEntries + intFirstTechEntries)
        End If

                'display output message box
        MessageBox.Show("Chris entered " & intFirstTechEntries & " temperature(s)." & ControlChars.CrLf &
                        "Bill entered " & intSecondTechEntries & " temperature(s)." & ControlChars.CrLf &
                        "The average temperature is " & dblAverageTemperature.ToString("n1"), "Summary Statistics",
                        MessageBoxButtons.OK, MessageBoxIcon.Information)

                'close the form
        Me.Close()
    End Sub
End Class
