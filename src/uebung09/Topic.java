package uebung09;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Topic {

    private String topicName;
    private ArrayList<QuizItem> items = new ArrayList<>();

    public Topic(String topicName) {
        this.topicName = topicName;
    }

    public void addItem(QuizItem item) {
        items.add(item);
    }

    public int getItemCount() {
        return items.size();
    }

    public QuizItem getItem(int index) {
        return items.get(index);
    }
}
