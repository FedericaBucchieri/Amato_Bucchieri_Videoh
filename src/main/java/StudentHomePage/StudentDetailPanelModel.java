package StudentHomePage;

import entities.Question;

import java.util.ArrayList;
import java.util.List;

public class StudentDetailPanelModel {
    private String username;
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

    public void addQuestionToList(Question question){
        questionList.add(question);
    }
}
