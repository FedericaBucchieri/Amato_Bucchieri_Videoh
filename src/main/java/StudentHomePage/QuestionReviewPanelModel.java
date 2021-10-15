package StudentHomePage;

import entities.Question;
import services.QuestionService;

import java.util.List;

public class QuestionReviewPanelModel {
    // The list of question to be reviewed
    private List<Question> questionList;

    public QuestionReviewPanelModel(List<Question> questionList) {
        this.questionList = questionList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    /**
     * This method removes a question from the questionList list
     * @param toBeDeleted the question to be deleted from the list
     */
    public void removeQuestionFormList(Question toBeDeleted){
        System.out.println(questionList.size());
        questionList.removeIf(question -> question.getId() == toBeDeleted.getId());
        System.out.println(questionList.size());
    }

    /**
     * This method updates the question in the database replacing the text body
     * @param question The question instance to update
     * @param newText the new text body of the question
     */
    public void updateQuestion(Question question, String newText){
        QuestionService service = new QuestionService();
        service.updateQuestionBody(question, newText);
    }

    /**
     * This method deletes a question from the database
     * @param question the question instance to be deleted
     */
    public void deleteQuestion(Question question){
        QuestionService service = new QuestionService();
        service.deleteQuestion(question.getId());
    }
}
