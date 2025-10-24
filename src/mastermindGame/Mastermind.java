package mastermindGame;

import java.util.Random;

public class Mastermind {

    public void main() {

        Random rn = new Random();
        String [] colors = {"S","W","R","B","G","O"};
        StringBuilder solution = new StringBuilder();

        for (int i=0; i<4; i++) {
            solution.append(colors[rn.nextInt(0, colors.length)]);
        }
        int richtige = 0;
        int weiss;

        while (richtige != 4) {
            String input = IO.readln("Dein Zug: ");
            input = input.trim().toUpperCase();

            if (input.toUpperCase().matches("[SWRBGO]{4}$")) {
                richtige = 0;
                weiss = 0;

                for (int i=0; i<colors.length; i++) {
                    int countInput = input.length()-input.replace(colors[i],"").length();
                    int countSolut = solution.length()- solution.toString().replace(colors[i],"").length();
                    IO.println(colors[i]+": "+countInput+", "+countSolut);
                }

                for (int i = 0; i < solution.length(); i++) {
                    if (input.charAt(i) == solution.charAt(i)){
                        richtige++;

                    } else if (solution.toString().contains(String.valueOf(input.charAt(i)))) {
                        weiss++;
                    }
                }

                IO.println("Schwarz: "+ richtige);
                IO.println("Weiss: "+ weiss);

            } else {
                IO.println("Ungültige Eingabe. Bitte wiederholen.");
            }

        }
        IO.println("Gratuliere, du hast gewonnen.");

    }
}
