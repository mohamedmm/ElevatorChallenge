package ElevatorSystem.Core;

/**
 * Created by mohamed on 4/14/16.
 */
public class ElevatorStatus {
    protected int id;
    protected int floorNumber;
    protected int goalFloorNumber;

    public ElevatorStatus(int id, int floorNumber, int direction) {
        this.id = id;
        this.floorNumber = floorNumber;
        this.goalFloorNumber = direction;
    }

    public int getId() {
        return id;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getGoalFloorNumber() {
        return goalFloorNumber;
    }

    public String toString() {
        return "Id: " + id + " floor number: " + floorNumber + " goal floor:" + goalFloorNumber;
    }
}
