My attempt to learn more about TCP/IP protocals and webservers

GOALS: Originally I just wanted to get a simple TCP echo server working which I have achieved!
       Currently I am looking to add more rigarous testing, as well as branching off the repo.

       Ideas I am currently thinking of persuing:
        simple chat functionality? 
        basic logging
        basic custom commands
        some kind of auth 


# Simple TCP Echo Server

This repository contains a simple TCP echo server implemented in Java. The server listens on a specified port for client connections and echoes back any data it receives. Each client connection is handled in a separate thread, allowing for concurrent processing of multiple clients.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher.

### Running the Server

1. **Clone the Repository**

If you haven't already, clone the repo :)

'git clone <repo_url>'

2. **Compile the server**

We must compile to generate our class file to run
'cd path/to/directory'
'javac Connection.java'

3. **Start the server**

Now we can finally run our 'Connection.class' file to start the server 
Make sure you are still in the project directory, and then run
'java Connection'

You know you succeeded if you see The server is listening to PORT 6789
"WAITING FOR CONNECTION... in your terminal"

4. **Connect to the server** 

Open a separate terminal, we can use a TCP client like telnet to test the server
'telnet localhost 6789'

Upon success you should see something like "Connected to <IP HERE>"
Now connected you can type any text into your terminal and click enter, and you should see your message echoed back! 