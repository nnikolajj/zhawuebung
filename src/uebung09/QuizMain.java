package uebung09;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class QuizMain {

    static void main() {

        ArrayList<Topic> topics = new ArrayList<Topic>();

        topics.add(new Topic("Paris"));
        topics.add(new Topic("Berlin"));

        topics.get(0).addItem(new QuizItem("Wie hoch ist der Eiffelturm?","324"));
        topics.get(0).addItem(new QuizItem("Wie lange ist die Champs-Élysées?","1910"));
        topics.get(0).addItem(new QuizItem("In welchem Jahr wurde Paris von den Nazis erobert?","1940"));
        topics.get(1).addItem(new QuizItem("Wie hoch ist der Fernsehturm?", "368"));
        topics.get(1).addItem(new QuizItem("In welchem Jahr die Berliner Mauer?", "1989"));

        for (Topic t : topics) {
            System.out.println("Topic: "+t.getTopicName());
            for(QuizItem item : t.getItems()) {
                System.out.println(item.getQuestion());

                String answer = IO.readln();

                if (item.checkAnswer(answer)) {
                    System.out.println("Richtig");
                } else {
                    System.out.println("Falsch");
                }
                System.out.println(" ");
            }
        }
    }
}
