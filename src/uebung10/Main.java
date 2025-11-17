package uebung10;

public class Main {

    static void main() {
        Flight f1 = new Flight("LX 735");
        f1.addPassenger("Gerber", "CH3456");
        f1.addPassenger("Müller", "CH4567");
        f1.addPassenger("Keller", "CH5678");
        f1.boardPassenger("CH3456");
        f1.boardPassenger("CH5678");
        IO.println(f1.boardingCompleted());
        f1.boardPassenger("CH4567");
        IO.println(f1.boardingCompleted());
    }
}
