package ProfessorLoginScene;

import EventManagement.Listener;
import entities.Professor;
import exceptions.CredentialsException;
import exceptions.UserNotRegisteredException;
import services.ProfessorService;

import java.util.ArrayList;
import java.util.List;

public class ProfessorLoginFormModel {
    // The controller of the component
    private ProfessorLoginForm controller;
    // The Professor instance to be retrieved from the DataBase
    private Professor professor;
    // The list of listeners for event handling
    private List<Listener> listeners = new ArrayList<>();

    public ProfessorLoginFormModel(ProfessorLoginForm controller) {
        this.controller = controller;
    }

    public void addListeners(Listener listener){
        listeners.add(listener);
    }

    /**
     * This method checks the user credential to validate a professor login
     * @param usr The string containing the username of the professor
     * @param pwd The string containing the password of the professor
     */
    public void checkCredential(String usr, String pwd){
        ProfessorService service = new ProfessorService();

        try {
            Professor professor = service.checkCredentials(usr, pwd);
            if(professor != null) {
                this.professor = professor;
                controller.dispatchLoginEvent(professor);
            }
        } catch (CredentialsException e) {
            controller.displayErrorMessage(e.getMessage());
        } catch (UserNotRegisteredException e){
            controller.displayErrorMessage(e.getMessage());
        }
    }


}
