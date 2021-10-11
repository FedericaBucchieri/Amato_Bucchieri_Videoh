package StudentHomePage;

import EventManagement.BackEvent;
import EventManagement.DeleteQuestionEvent;
import EventManagement.EndReviewEvent;
import EventManagement.Listener;
import entities.Question;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionReviewPanel {
    private QuestionReviewPanelModel model;
    private QuestionReviewPanelUI ui;
    private List<Listener> listeners = new ArrayList<>();

    public QuestionReviewPanel(StudentHomePageScene studentHomePageScene, List<Question> questionList) {
        this.listeners.add(studentHomePageScene);

        this.model = new QuestionReviewPanelModel(questionList);
        this.ui = new QuestionReviewPanelUI(this);
        this.ui.installUI();
    }

    public QuestionReviewPanelModel getModel() {
        return model;
    }

    public List<Question> getQuestionList(){
        return model.getQuestionList();
    }

    public void backToVideo(){
        for (Listener listener : listeners)
            listener.listen(new BackEvent());
    }

    public void endReview(){
        for (Listener listener : listeners)
            listener.listen(new EndReviewEvent());
    }

    public JPanel getMainPanel(){
        return ui.getMainPanel();
    }

    public void handleModifyRequest(Question question, String newText){
        model.updateQuestion(question, newText);
    }

    public void handleDeleteRequest(Question question){
        model.deleteQuestion(question);
        model.removeQuestionFormList(question);
        ui.repaintQuestionList();
        dispatchDeleteQuestionEvent(question);
    }

    private void dispatchDeleteQuestionEvent(Question question){
        for (Listener listener : listeners)
            listener.listen(new DeleteQuestionEvent(question));
    }
}
