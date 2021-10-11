package StudentHomePage;

import entities.Question;
import services.QuestionService;

import java.util.List;

public class QuestionReviewPanelModel {
    private List<Question> questionList;

    public QuestionReviewPanelModel(List<Question> questionList) {
        this.questionList = questionList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void removeQuestionFormList(Question toBeDeleted){
        System.out.println(questionList.size());
        questionList.removeIf(question -> question.getId() == toBeDeleted.getId());
        System.out.println(questionList.size());
    }

    public void updateQuestion(Question question, String newText){
        QuestionService service = new QuestionService();
        service.updateQuestionBody(question, newText);
    }

    public void deleteQuestion(Question question){
        QuestionService service = new QuestionService();
        service.deleteQuestion(question.getId());
    }
}
