package uebung08;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class WerIstEs {
	static Person [] personen;
	static String [] merkmale =  {"Haarfarbe schwarz","Haarlaenge kurz","Haarfarbe braun","Augenfarbe blau","Haarfarbe blond","Haarlaenge lang","Haarfarbe rot","Augenfarbe braun"};

	public void main() {
		personen = new Person[11];
		personen[0] = new Person("Tom","schwarz","kurz","blau");
		personen[1] = new Person("Alex","schwarz","kurz","braun");
		personen[2] = new Person("Maria","braun","lang","braun");
		personen[3] = new Person("Robert","braun","kurz","blau");
		personen[4] = new Person("Bernard","braun","kurz","braun");
		personen[5] = new Person("Anita","blond","lang","blau");
		personen[6] = new Person("David","blond","kurz","blau");
		personen[7] = new Person("Charles","blond","kurz","braun");
		personen[8] = new Person("Alfred","rot","lang","blau");
		personen[9] = new Person("Frans","rot","kurz","blau");
		personen[10] = new Person("Bill","rot","kurz","braun");

        Random rn = new Random();
        int mIndex = rn.nextInt(merkmale.length);

        while (countPersonenImSpiel()>1) {
            IO.println("Personen im Spiel: "+getPersonenImSpiel());
            // Merkmal auswählen
            String merkmal = merkmale[mIndex];
            mIndex = (mIndex+1)%merkmale.length;
            // Frage stellen
            String input = IO.readln("Ist deine "+merkmal+"? ");
            // Antwort auswerten
            if (input.equals("nein")) {
                removeWith(merkmal);
            } else if (input.equals("ja")) {
                removeWithout(merkmal);
            }
        }
        IO.println("Du bist " + getPersonenImSpiel());
	}
	
	static int countPersonenImSpiel() {
		return Math.toIntExact(Arrays.stream(personen).filter(Objects::nonNull).count());
	}
	
	static String getPersonenImSpiel() {

		return Arrays.stream(personen).filter(Objects::nonNull).map(Person::getName).collect(Collectors.joining(" "));
	}
	
	static void removeWith(String merkmal) {
        String[] words = merkmal.split(" ");
		personen = Arrays.stream(personen).filter(person -> {
            switch (words[0]) {
                case "Haarfarbe":
                    return !person.getHaarfarbe().equals(words[1]);
                case "Haarlaenge":
                    return !person.getHaarlaenge().equals(words[1]);
                case "Augenfarbe":
                     return !person.getAugenfarbe().equals(words[1]);
                default:
                    return true;
            }
        }).toArray(Person[]::new);
	}
	
	static void removeWithout(String merkmal) {
        String[] words = merkmal.split(" ");
        personen = Arrays.stream(personen).filter(person -> {
            switch (words[0]) {
                case "Haarfarbe":
                    return person.getHaarfarbe().equals(words[1]);
                case "Haarlaenge":
                    return person.getHaarlaenge().equals(words[1]);
                case "Augenfarbe":
                    return person.getAugenfarbe().equals(words[1]);
                default:
                    return true;
            }
        }).toArray(Person[]::new);
	}
}
