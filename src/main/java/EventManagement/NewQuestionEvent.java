package EventManagement;

import entities.Question;

public class NewQuestionEvent  implements Event{
    private Question question;

    public NewQuestionEvent(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }
}
