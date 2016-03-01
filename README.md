# j-rmi

Agenda : Understaing java RMI with the help of demo example for login with authentication details on remote host.

The usernames and passwords are stored in a file which the server can read from and write to. 
This file contain a username "admin" by default and a password "password".

When the server gets request for registration
* If the username is already in the database, send a message to the client saying “Username already exists. Try a different one."
* If the username is not in the database, send a message to the client saying “Registration successful” and create acorresponding entry in the database.

After the registration is successful, then the client program should ask the user if he/she wants to login. If yes, then follow the flow mentioned below.

When the server gets request for a login:
* If the username and password match, send a message to the client saying “Welcome
<username>!” (without quotes) where <username> is the username entered by the user.
* If the username and password match for the user „admin‟, send a message to the client
saying “Welcome admin! Number of registered users are <number_of_users>”, where
<number_of_users> are the number of users in the file .

The program exits as soon as the flow for login is complete.

The program contains a RMIServer and a RMICient . Both communicates on a common agreed upon port .
