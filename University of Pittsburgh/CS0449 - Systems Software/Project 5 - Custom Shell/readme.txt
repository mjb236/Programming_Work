//Michael Bowen
//CS0449 Tues/Thurs 4:00-5:15pm  Rec: Fri 1:00pm
//
//Project 5 - custom shell

The shell was tested and working on both Ubuntu and thoth.cs.pitt.edu.

Program juses fgets() and strtok() as required to process the user input.

Internal commands supports are exit and cd.

All other external commands are run via fork() and execvp().

Additional commands supported are showpath and hidepath. These commands will show or hide
the current directory. When shown, the directory will be part of the shell prompt.

I also added --color to the ls command by default.
