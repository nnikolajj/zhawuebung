package uebung07;

public class WerIstEs {
    public void main() {
        Person p1 = new Person("Alex","schwarz","kurz","braun");
        Person p2 = new Person("Alfred","rot","lang","blau");
        Person p3 = new Person("Anita","blond","lang","blau");
        Person p4 = new Person("Anne","schwarz","kurz","braun");
        Person p5 = new Person("Maria","braun","lang","braun");
        Person p6 = new Person("Robert","braun","kurz","blau");

        IO.println(p1.getName()+" Augen: " + p1.getAugenfarbe());
        IO.println(p2.getName()+" Haarfarbe: "+p2.getHaarfarbe());
        IO.println(p3.getName()+" Haarelänge: "+p3.getHaarlaenge());

        p4.printPerson();
        p5.printPerson();
        p6.printPerson();

        IO.println(p1.hatMerkmal("Haarfarbe schwarz"));
        IO.println(p2.hatMerkmal("Haarfarbe braun"));
        IO.println(p3.hatMerkmal("Haarlaenge lang"));
        IO.println(p4.hatMerkmal("Haarlaenge lang"));
        IO.println(p5.hatMerkmal("Augenfarbe blau"));
        IO.println(p6.hatMerkmal("Augenfarbe blau"));
    }
}
