package StudentHomePage;

import entities.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentDetailPanelModel {
    // the username of the logged student
    private String username;
    // the list of questions made by the student
    private List<Question> questionList = new ArrayList<>();

    public StudentDetailPanelModel(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public void addQuestionToList(Question question){
        questionList.add(question);
    }

    /**
     * This method sorts the questionList according to descent order of the date
     */
    public void sortQuestionList(){
        Collections.sort(questionList, new Comparator<Question>() {
            @Override public int compare(Question q1, Question q2) {
                return q1.getTimestamp() - q2.getTimestamp(); // Ascending
            }

        });
    }

    public void removeQuestion(Question toRemove){
        questionList.removeIf(question -> question.getId() == toRemove.getId() );
    }
}
