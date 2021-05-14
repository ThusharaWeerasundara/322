***************************************************************************
*		Project 2 - AUCTION SERVER - CO2255:Software Construction         *
*	      	    by GROUP 19 (E/16/388, E/16/389, E/16/377)                *
***************************************************************************

# Contains: 10 .java files, 1 .csv file and README.md file

# Instruction for run the programe,
	I.) Get a terminal or cmd from current derectory

    II.) Run the Server (to compile and run Server.java file like belowe)
    		javac Server.java
    		java Server

    III.) Get another terminal or cmd

    IV.) Run the client (to compile and run ClientApp.java file like belowe)
    		javac ClientApp.java
    		java ClientApp


When testing on nc, user should provide valid name 1st. If that name is inuse server sends -2. After sending name user should
send valid item else server sends -1.	Then server provide item current bid value and then user can bid. Server updates the
value when a new valid bid happens and it sends the new value to user as well.

A client cant change his name/item and 2 clients cant have the same name. Also after client loggedout, he needs a different name to
log in again.
