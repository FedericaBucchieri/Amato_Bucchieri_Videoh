package EventManagement;

import entities.Question;

public class DeleteQuestionEvent implements Event{
    private Question question;

    /**
     *  * DeleteQuestionEvent is an Event implementation class. An instance of this class will be dispatched when a question has been deleted by the student
     * @param question
     */
    public DeleteQuestionEvent(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }
}
