package uebung05;

import java.util.Arrays;

public class MethodTour {

    public int[] numbers = new int[3];

    public void main() {

        MethodTour methodTour = new MethodTour();

        IO.print("Bitte drei ganze Zahlen eingeben: ");
        int a = Integer.parseInt(IO.readln());
        int b = Integer.parseInt(IO.readln());
        int c = Integer.parseInt(IO.readln());

        numbers = new int[]{a, b, c};

        printSum(a,b,c);
        IO.println("Product: "+ getProduct(a,b,c));

        IO.println("Average: "+ getAverage(a,b,c));
        IO.println("Two Equal: "+ twoAreEqual(a,b,c));
        IO.println("Even: "+ allAreEven(a,b,c));
        IO.println("Consecutive: "+ allAreConsecutive());
        IO.println("Max: "+ getMax());
        IO.println("Directon: "+ getDirection(a,b,c));

        printSortet();

    }

    private void printSum(int a, int b, int c) {
        System.out.println("Sum: " + (a +b+c));
    }

    private int getProduct(int a, int b, int c) {

        return (a*b*c);
    }

    private double getAverage(int a, int b, int c) {

        return (double) (a + b + c) /3;
    }

    private boolean twoAreEqual(int a, int b, int c) {

        return a == b || a == c || b == c;
    }

    private boolean allAreEven (int a, int b, int c) {

        return a%2 == 0 && b%2 == 0 && c%2 == 0;
    }

    private boolean allAreConsecutive () {

        /** MIT HILFSMITTEL:
         *
         numbers = Arrays.stream(numbers).sorted().toArray();
         */

        int[] sortedList = sort(numbers);

        return sortedList[2]%sortedList[1] == 1 && sortedList[1]%sortedList[0] == 1;
    }

    private int getMax () {

    /** MIT HILFSMITTEL
    *
    * Arrays.stream(numbers).max().orElseThrow()
    */
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];

            }
        }

        return max;
    }

    private int getDirection (int a, int b, int c) {

        return Integer.compare(a, c) * -1;
    }

    private void printSortet() {
        /** MIT HILFSMITEL
         *
        System.out.println("Sorted: " +
                Arrays.toString(Arrays.stream(numbers).sorted().toArray())
                        .replace("[", "").replace("]", ""));
        */

        System.out.println("Sorted: " + Arrays.toString(sort(numbers)).replace("[", "").replace("]", ""));

    }

    private int[] sort(int[] list){
        for (int j = 0; j < list.length; j++) {
            for (int i = 0; i < list.length-1; i++) {
                if (list[i] > list[i + 1]) {
                    int bigger = list[i];

                    list[i] = list[i+1];
                    list [i+1] = bigger;
                }
            }
        }


        return list;
    }
}