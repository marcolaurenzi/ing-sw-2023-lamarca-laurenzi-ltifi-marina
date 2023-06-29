![Icona](src/main/resources/assets/Publisher%20material/Icon%2050x50px.png)
# My Shelfie Board Game

A game by Phil Walker-Harding and Matthew Dunstan. You've just brought your new **shelf** home, and it's time to fill it with your favourite objects: collect books, plants, board games, portraits, prizes... and don't forget to leave some space for your cats! Who will manage to have the best organized **shelf**?

The game is a _Cranio Creations s.r.l._'s property

## ing-sw-2023-lamarca-laurenzi-ltifi-marina

This is the **Ingegneria del Software**'s final test held at **Politecnico di Milano** (A.Y. 2022/2023)

## Team Members

The Group 5 team members are: [Riccardo Lamarca](https://github.com/Riccardo250), [Marco Laurenzi](https://github.com/marcolaurenzi), [Mouadh Ltifi](https://github.com/mouadhltifi), [Alessandro Aldo Marina](https://github.com/Hackingale) <br>
Professor: Pierluigi San Pietro

## Implementation

The follwing functionalities have been implemented.

| Functionality | State |
|:-----------------------|:------------------------------------:|
| Complete rules | Done |
| TUI | Done |
| Socket | Done |
| RMI | Done |
| GUI | Done |
| Multiple games | Done |
| Client reconnection | Done |


# Building instructions
The project can be built using the following Maven command
```
mvn clean package
```
The resulting jar executables can be found in the deliverables folder as `Server.jar` and `CLient.jar`

# Execution instructions
Java 19 is required to run.

## Running the Server
To start the server, run the jar the following arguments
```
java -jar Server.jar
```
Once started the Server will ask you to provide your personal IP address. Be careful, a wrong IP will lead to an unsuccessful process.

## Running the Client
The Client is the same for the TUI and for the GUI, once started the Client will ask you to choose.

To start the client, run the jar the following arguments
```
java -jar Client.jar
```
Once started the client wil ask you to provide your personal IP address and the Server's one. Be careful, a wrong IP will lead to an unsuccessful process.


