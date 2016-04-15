package ElevatorSystem.Core;

/**
 * Created by mohamed on 4/14/16.
 */
//import com.sun.tools.javac.util.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ElevatorControlSystem {
    protected List<Elevator> elevators;

    public ElevatorControlSystem() {
        elevators = new ArrayList<Elevator>();
    }

    public List<ElevatorStatus> status() {
        List<ElevatorStatus> statuses = new ArrayList<ElevatorStatus>();
        for(Elevator elevator : elevators) {
            statuses.add(elevator.getStatus());
        }
        return statuses;
    }

    public void update( int id, int floorNumber, int goalFloorNumber) {
        for (Elevator elevator : elevators) {
            if( id == elevator.getId()) {
                elevator.setStatus(floorNumber, goalFloorNumber);
                break;
            }
        }
    }

    public void pickup(int pickupFloor, int direction) {
        int minDifference = Integer.MAX_VALUE;
        Elevator goalElevator = null;
        for (Elevator elevator : elevators) {
            // we need to find the nearest elevator.
            int currentFloor = elevator.getFloorNumber();
            int diff = pickupFloor - currentFloor;
            if ( diff == 0) {
                goalElevator = elevator;
                break;
            }
            if(Math.abs(diff) < minDifference) {
                if( elevator.getDirection() * diff <= 0) {
                    minDifference = Math.abs(diff);
                    goalElevator = elevator;
                }
            }
        }
        //Assert.checkNonNull(goalElevator);
        goalElevator.addFloorGoal(pickupFloor);
        goalElevator.setDirection(direction);
    }

    public void step() {
        for (Elevator elevator : elevators) {
            elevator.step();
        }
    }

    public void addElevator(Elevator elevator) {
        elevators.add(elevator);
    }
}
