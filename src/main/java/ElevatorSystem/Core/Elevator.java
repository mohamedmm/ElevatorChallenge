package ElevatorSystem.Core;

/**
 * Created by mohamed on 4/14/16.
 */

//import com.sun.tools.javac.util.Assert;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Elevator {
    private static int id_counter = 0;
    protected int id;
    protected int floorNumber;
    protected Set<Integer> goalFloors;
    protected int direction;
    private int minFloor;
    private int maxFloor;

    public Elevator( int minFloor, int maxFloor) {
        synchronized (this) {
            id = ++id_counter;
        }
        floorNumber = 1;
        goalFloors = Collections.synchronizedSet(new HashSet());

        //0 for direction means not moving, positive means up , negative means down
        direction = 0;

        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public int getId() {
        return id;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Set<Integer> getGoalFloors() {
        return goalFloors;
    }

    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public ElevatorStatus getStatus() {
        int goalFloor = -1;
        //if no goal floor, return current floor
         if(goalFloors.isEmpty()) {
             goalFloor = floorNumber;
         }
        else {

             // get the next goalFloor. which is the next floor on the set of goals that is on the same direction
             //if direction is up then we look for minimum positive direction. if it is down then min negative direction
             int minDiff = Integer.MAX_VALUE;
             boolean firstItem = true;
             for (Integer floor : goalFloors) {
                 if(firstItem) {
                     goalFloor = floor;
                     minDiff = Math.abs(floor - floorNumber);
                 }
                 else {
                     int diff = floor - floorNumber;
                     //if both diff and direction positive or both negative
                     if (direction * diff > 0) {
                         int absDiff = Math.abs(diff);
                         if (diff < minDiff) {
                             minDiff = absDiff;
                             goalFloor = floor;
                         }
                     }
                 }
             }
         }
        //Assert.check((-1 != goalFloor));
        return new ElevatorStatus(id, floorNumber, goalFloor);
    }

    public void setStatus(int floorNumber, int goalFloorNumber) {
        this.floorNumber = floorNumber;
        this.addFloorGoal(goalFloorNumber);
        //only change the direction if the elevator is not moving. if the elevator is moving then keep the same direction
        if( this.direction == 0) {
            this.direction = goalFloorNumber - floorNumber;
        }
    }

    public void addFloorGoal(int floorNumber) {
        goalFloors.add(floorNumber);
    }

    public void step() {
        //if there is no direction
        if (direction == 0) return;

        // if current floor is on goal list then remove it.
        if( goalFloors.contains(floorNumber)) {
            goalFloors.remove(floorNumber);
        }

        // if no goal floors any more then stay put
        if( goalFloors.isEmpty()) {
            direction = 0;
            return;
        }

        if(floorNumber == minFloor && direction < 0) {
            direction *=-1;
        }

        if(floorNumber == maxFloor && direction > 0) {
            direction *=-1;
        }

        int increment = (direction > 0) ? 1 : -1;
        floorNumber += increment;
    }
}
