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
    // The model of the component
    private QuestionReviewPanelModel model;
    // The view of the component
    private QuestionReviewPanelView view;
    // The list of listeners for event handling
    private List<Listener> listeners = new ArrayList<>();

    /**
     * This constructor creates an instance of QuestionReviewPanel assigning a StudentHomePageScene to its listeners
     * @param studentHomePageScene the instance to add to the listeners list
     * @param questionList the list of questions to be displayed in the panel
     */
    public QuestionReviewPanel(StudentHomePageScene studentHomePageScene, List<Question> questionList) {
        this.listeners.add(studentHomePageScene);

        this.model = new QuestionReviewPanelModel(questionList);
        this.view = new QuestionReviewPanelView(this);
        this.view.installUI();
    }

    public QuestionReviewPanelModel getModel() {
        return model;
    }

    public List<Question> getQuestionList(){
        return model.getQuestionList();
    }

    /**
     * This method dispatches a BackEvent event
     */
    public void backToVideo(){
        for (Listener listener : listeners)
            listener.listen(new BackEvent());
    }

    /**
     * This method dispatches an EndReviewEvent event
     */
    public void endReview(){
        for (Listener listener : listeners)
            listener.listen(new EndReviewEvent());
    }

    public JPanel getMainPanel(){
        return view.getMainPanel();
    }

    /**
     * This method handles a Modify request from the view
     * @param question the question instance to be modified
     * @param newText the new text body of the question
     */
    public boolean handleModifyRequest(Question question, String newText){
        return model.updateQuestion(question, newText);
    }

    /**
     * This method handles a delete request from the view to delete a question
     * @param question The question to be deleted
     */
    public void handleDeleteRequest(Question question){
        model.deleteQuestion(question);
        model.removeQuestionFormList(question);
        view.repaintQuestionList();
        dispatchDeleteQuestionEvent(question);
    }

    /**
     * This method dispatched a new DeleteQuestionEvent event
     * @param question The question to be passed in the event
     */
    private void dispatchDeleteQuestionEvent(Question question){
        for (Listener listener : listeners)
            listener.listen(new DeleteQuestionEvent(question));
    }
}
