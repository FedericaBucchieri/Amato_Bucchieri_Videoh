package ProfessorLoginScene;

import EventManagement.ErrorEvent;
import EventManagement.Listener;
import EventManagement.ProfessorLoginEvent;
import entities.Professor;
import exceptions.CredentialsException;
import exceptions.UserNotRegisteredException;
import services.ProfessorService;

import java.util.ArrayList;
import java.util.List;

public class ProfessorLoginFormModel {
    private Professor professor;
    private List<Listener> listeners = new ArrayList<>();


    public ProfessorLoginFormModel() {
    }

    public void addlisteners(Listener listener){
        listeners.add(listener);
    }

    public void checkCredential(String usr, String pwd){
        ProfessorService service = new ProfessorService();

        try {
            Professor professor = service.checkCredentials(usr, pwd);
            if(professor != null) {
                this.professor = professor;
                dispatchLoginEvent(professor);
            }
        } catch (CredentialsException e) {
            dispatchErrorEvent(e.getMessage());
        } catch (UserNotRegisteredException e){
            dispatchErrorEvent(e.getMessage());
        }
    }


    private void dispatchErrorEvent(String error){
        for (Listener listener : listeners)
            listener.listen(new ErrorEvent(error));
    }

    private void dispatchLoginEvent(Professor professor){
        for (Listener listener : listeners)
            listener.listen(new ProfessorLoginEvent(professor));
    }

}
