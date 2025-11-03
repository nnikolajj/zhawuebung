package uebung07;

import lombok.Getter;
import lombok.Setter;

public class Person {
    private String name;
    private String haarfarbe;
    private String haarlaenge;
    private String augenfarbe;

    public Person(String name, String haarfarbe, String haarlaenge, String augenfarbe) {
        this.name = name;
        this.haarfarbe = haarfarbe;
        this.haarlaenge = haarlaenge;
        this.augenfarbe = augenfarbe;
    }

    public String getName() {
        return name;
    }
    public String getHaarfarbe() {
        return haarfarbe;
    }

    public String getHaarlaenge() {
        return haarlaenge;
    }

    public String getAugenfarbe() {
        return augenfarbe;
    }

    public void printPerson() {
        IO.println(name + " hat " + haarlaenge + "e " + haarfarbe + "e Haare und " + augenfarbe + "e Augen.");
    }

    public boolean hatMerkmal(String merkmal) {
        String[] merkmalArt= merkmal.split(" ");

        switch (merkmalArt[0]) {
            case "Haarfarbe":
                return haarfarbe.equals(merkmalArt[1]);
            case "Haarlaenge":
                return haarlaenge.equals(merkmalArt[1]);
            case "Augenfarbe":
                return augenfarbe.equals(merkmalArt[1]);
            default:
                return false;
        }

    }
}

