package ElevatorChallenge;

import ElevatorSystem.Core.Elevator;
import ElevatorSystem.Core.ElevatorControlSystem;
import ElevatorSystem.Core.ElevatorStatus;

import java.util.List;
import java.util.Random;

/**
 * Created by mohamed on 4/14/16.
 */
public class ElevatorChallenge {
    public static void main(String[] args) {
        ElevatorControlSystem system = new ElevatorControlSystem();

        //total number of elevator = 16, total number of floors = 40
        for (int i = 0; i < 16; ++i) {
            system.addElevator(new Elevator(1, 40));
        }

        //simulate for 120 seconds
        Random rand = new Random();
        int direction = -1;
        for(int i = 0; i < 120 ; ++i) {
            printStatus(system.status());
            System.out.println();
            try {
                int random = rand.nextInt(2);
                System.out.println(random);
                switch(random) {
                    case 0: {
                        //pickup
                        System.out.println("pickup");
                        int floor  = rand.nextInt(39) + 1;
                        system.pickup(floor, direction);
                        direction *= -1;
                    }
                    break;
                    case 1: {
                        //update
                        System.out.println("update");
                        system.update(rand.nextInt(16), rand.nextInt(39) +1 , rand.nextInt(39) + 1);
                    }
                    break;

                }
                system.step();

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printStatus(List<ElevatorStatus> statuses) {
        for (ElevatorStatus status: statuses) {
            System.out.println(status.toString());
        }
    }
}

