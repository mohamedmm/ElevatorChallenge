# ElevatorChallenge
The scheduling algorithm works on finding the min distance between the elevator and the pickup floor. The distance is
calculated by taking into account the direction of the elevator. This results in favoring elevatos that will pass by
the pickup floor on the way to the the current list of goal floors or idle elevators over elevators that is going in
the opposite direction and will not pass by the pickup floor till it completes a whole round around the building

# Build Instructions
mvn compile

java -jar target/ElevatorChallenge-1.0-SNAPSHOT.jar
