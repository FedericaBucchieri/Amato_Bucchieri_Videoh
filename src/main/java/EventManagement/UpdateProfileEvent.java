package EventManagement;

import entities.Professor;

public class UpdateProfileEvent implements Event {
    private Professor professor;

    public UpdateProfileEvent(Professor professor) {
        this.professor = professor;
    }

    public Professor getProfessor() {
        return professor;
    }
}
