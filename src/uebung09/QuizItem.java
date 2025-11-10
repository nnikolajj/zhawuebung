package uebung09;

import lombok.Getter;

@Getter
public class QuizItem {


    private String question;
    private String answer;

    public QuizItem(String question, String answer) {
        this.question = question;
        this.answer = answer;

    }

    public boolean checkAnswer(String answer) {
        return answer.equals(this.answer);
    }
}
