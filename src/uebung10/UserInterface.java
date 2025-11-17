package uebung10;

import uebung10.Flight;

public class UserInterface {
	
	public void main() {
		String flugnummer = IO.readln("Flight number: ");
		Flight f = new Flight(flugnummer);
		
		String input = "";
		while (!input.equals("6")) {
			IO.println("\n1) Check in 2) Boarding 3) Announcement 4) Boarding completed 5) List passengers 6) exit");
			input = IO.readln("cmd> ");
			if (input.equals("1")) {
				String name = IO.readln("Name passenger: ");
				String passnummer = IO.readln("Passport ID: ");
				f.addPassenger(name, passnummer);
			} else if (input.equals("2")) {
				String passnummer = IO.readln("Passport ID: ");
				int status = f.boardPassenger(passnummer);
				if (status == 0) {
					IO.println("Passenger boarded successfully");
				} else if (status == 1) {
					IO.println("Boarding failed: passenger already boarded (Security Alert)");
				} else if (status == 2) {
					IO.println("Boarding failed: unknown passenger");
				}
			} else if (input.equals("3")) {
				f.announcement();
			} else if (input.equals("4")) {
				if (f.boardingCompleted()) {
					IO.println("Boarding completed");
				} else {
					IO.println("Boarding NOT completed");
				}
			} else if (input.equals("5")) {
				f.printPassengers();
			}
		}
	}
}
