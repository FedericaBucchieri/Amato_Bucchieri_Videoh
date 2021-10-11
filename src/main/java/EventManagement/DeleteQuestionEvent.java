package EventManagement;

import entities.Question;

public class DeleteQuestionEvent implements Event{
    private Question question;

    public DeleteQuestionEvent(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }
}
