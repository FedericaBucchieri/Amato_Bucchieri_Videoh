package EventManagement;

import entities.Question;

import java.util.List;

public class ReviewRequestEvent implements Event {
    private List<Question> questionList;

    public ReviewRequestEvent(List<Question> questionList) {
        this.questionList = questionList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }
}
