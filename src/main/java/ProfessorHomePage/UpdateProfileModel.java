package ProfessorHomePage;

import entities.Professor;
import services.ProfessorService;

public class UpdateProfileModel {
    private UpdateProfile controller;
    private Professor professor;

    public UpdateProfileModel(UpdateProfile controller, Professor professor) {
        this.controller = controller;
        this.professor = professor;
    }

    public Professor updateProfile(String username, String  password){
        ProfessorService service = new ProfessorService();
        service.updateProfessor(professor, username, password);
        return professor;
    }

    public String getUsername(){
        return professor.getUsername();
    }

    public String getPassword(){
        return professor.getPassword();
    }

}
