package uebung10;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class Flight {

    private  String flightNumber;
    private HashMap<String, Passenger> passengers = new HashMap<>();

    public Flight(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void addPassenger(String name, String passnumber) {
        passengers.put(passnumber, new Passenger(name, passnumber));
    }

    public void printPassengers() {
        for (Passenger p : passengers.values()) {
            System.out.println(p.getName() + " (" + p.getPassportId() + ") Boarded: " + p.isBoarded() );
        }
    }

    public int boardPassenger(String passportId){
        Passenger p = passengers.get(passportId);

        if (p == null) {
            return 2;
        }

        if (!p.isBoarded()) {
            p.setBoardedTrue();
            return 0;
        }

        return 1;
    }

    public void announcement() {

        for (Passenger p : passengers.values()) {
            if (!p.isBoarded()) {
                System.out.println("Final call for passenger "+ p.getName() +"\n" +
                        "Please proceed to gate immediately!");
            }
        }
    }

    public boolean boardingCompleted() {
        for (Passenger p : passengers.values()) {
            if (!p.isBoarded()) {
                return false;
            }
        }
        return true;
    }
}
