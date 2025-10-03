import java.util.Scanner;

public class Entscheidungsspiel {
    public static void main(String[] args) {
        Scanner keyScan = new Scanner(System.in);
        System.out.println("Bitte vier Zahlen eingeben:");
        int a1 = keyScan.nextInt();
        int a2 = keyScan.nextInt();
        int a3 = keyScan.nextInt();
        int a4 = keyScan.nextInt();

        int zahl = a1;

        int[] werte = {a1, a2, a3, a4};
        for(int i = 0; i < werte.length -1; i++){
            if(werte[i] < werte[i+1]){
                zahl = werte[i];
            }
        }


        System.out.println("Die grösste eingegebene Zahl ist: ");

        keyScan.close();
    }
}
