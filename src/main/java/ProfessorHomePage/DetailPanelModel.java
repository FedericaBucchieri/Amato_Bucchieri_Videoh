package ProfessorHomePage;

import entities.Professor;

public class DetailPanelModel {
    //The logged professor
    private Professor professor;

    /**
     * Creates a new model for the DetailPanel component
     * @param professor the logged professor
     */
    public DetailPanelModel(Professor professor) {
        this.professor = professor;
    }

    public Professor getProfessor() {
        return professor;
    }
}
