package ProfessorHomePage;

import entities.Professor;

public class DetailPanelModel {
    private Professor professor;

    public DetailPanelModel(Professor professor) {
        this.professor = professor;
    }

    public Professor getProfessor() {
        return professor;
    }
}
