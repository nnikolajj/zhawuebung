package uebung03;

import java.util.Scanner;

public class Pegelrechner {

    public static void main(String[] args) {

        // Daten Einlesen
        double startWert = 50000;
        double schadensgrenze = 61500;
        double [] zufluss1 = ZhawCsvLib.readDoubleArray("zufluss1.csv");
        double [] zufluss2 = ZhawCsvLib.readDoubleArray("zufluss2.csv");
        double [] abfluss1 = ZhawCsvLib.readDoubleArray("abfluss1.csv");
        double [] wasserImSee = new double[zufluss1.length+1];
        double maxFuellstand = 0;
        short zeitUeberSchadensgrenze = 0;

        wasserImSee[0] = startWert;

        // Länge der Datensätze ausgeben
        System.out.println("Werte Zufluss 1: "+zufluss1.length);
        System.out.println("Werte Zufluss 2: "+zufluss2.length);
        System.out.println("Werte Abfluss 1: "+abfluss1.length);

        for (int i = 0; i < zufluss1.length; i++) {
            double pegel = startWert + (zufluss1[i] + zufluss2[i] - abfluss1[i]) * 60;

            if (pegel > maxFuellstand) {
                maxFuellstand = pegel;
            }

            if (pegel > schadensgrenze) {
                zeitUeberSchadensgrenze++;
            }
            if (pegel>55000){
                pegel = startWert + (zufluss1[i] + zufluss2[i] - abfluss1[i] -4) * 60;
            }

            wasserImSee[i+1] = pegel;

            startWert = wasserImSee[i+1];

            String a = "nutte";

            if (String.valueOf(a.toLowerCase().charAt(i))== "e"){
                System.out.println(2);
            }

            char b = a.charAt(a.length()-1);

            System.out.println(b);
        }

        System.out.println("Maximaler Füllstand: "+maxFuellstand + " m3");
        System.out.println("Zeit über Schadensgrenze: "+ zeitUeberSchadensgrenze + " h");

        ZhawCsvLib.writeDoubleArray("src/uebung03/ohneStollen.csv", wasserImSee);
    }
}
