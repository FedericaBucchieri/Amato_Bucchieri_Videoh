package EventManagement;

import entities.Question;


public class NewQuestionEvent  implements Event{
    private Question question;

    /**
     * NewQuestionEvent is an Event implementation class. An instance of this class will be dispatched when a new question has been posted by the student
     * @param question: the question that has been posted by the student.
     */
    public NewQuestionEvent(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }
}
