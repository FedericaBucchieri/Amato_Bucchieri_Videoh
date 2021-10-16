package ProfessorHomePage;

import entities.Professor;
import services.ProfessorService;

public class UpdateProfileModel {
    //The UpdateProfile component
    private UpdateProfile controller;
    //The logged professor
    private Professor professor;

    /**
     * Creates the Model of the UpdateProfile component
     * @param controller the UpdateProfile
     * @param professor the logged professor
     */
    public UpdateProfileModel(UpdateProfile controller, Professor professor) {
        this.controller = controller;
        this.professor = professor;
    }

    /**
     * Update the given parameters into the corresponding tuple in the database
     * @param username the new username
     * @param password the new password
     * @return
     */
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
