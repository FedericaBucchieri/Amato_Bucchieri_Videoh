package EventManagement;

import entities.Professor;

public class ProfessorLoginEvent implements Event{
    private Professor professor;

    public ProfessorLoginEvent(Professor professor) {
        this.professor = professor;
    }

    public Professor getProfessor() {
        return professor;
    }
}
