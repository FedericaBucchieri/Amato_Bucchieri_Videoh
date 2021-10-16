package ProfessorHomePage;

import EventManagement.*;
import entities.Professor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UpdateProfile extends JComponent {
    //The model of the UpdateProfile component
    private UpdateProfileModel model;
    //The view of the UpdateProfile component
    private UpdateProfileView view;
    //The list of all the listeners
    private List<Listener> listeners = new ArrayList<>();

    /**
     * Creates a new UpdateProfile component. It is the page that is shown when the professor wants to update its username and it password
     * @param professorHomePage the home page that has created the UpdateProfile page; it is added to the listeners
     * @param professor the entity of the logged professor
     */
    public UpdateProfile(ProfessorHomePage professorHomePage, Professor professor) {
        this.listeners.add(professorHomePage);
        this.model = new UpdateProfileModel(this, professor);
        this.view = new UpdateProfileView(this);
        this.view.installUI();
    }

    /**
     * Handle the request of update by updating the model and dispatching a an UpdateProfileEvent to the above listeners
     * @param username the updated username
     * @param password the updated password
     */
    public void handleUpdateProfileRequest(String username, String password){
        Professor professor = model.updateProfile(username, password);
        dispatchUpdateProfileEvent(professor);
    }

    /**
     * Dispatch the UpdateProfileEvent to the above listener
     * @param professor the entity of the logged professor to pass to the above listeners via the event
     */
    private void dispatchUpdateProfileEvent(Professor professor){
        for (Listener listener : listeners)
            listener.listen(new UpdateProfileEvent(professor));
    }

    public String getUsername(){
        return model.getUsername();
    }

    public String getPassword(){
        return model.getPassword();
    }

    /**
     * This method dispatch a new CancelEvent to the above listeners. The professor home page will handle it to dismiss the UpdateProfile
     * page and to go back to the videoList
     */
    public void goBackToVideoList(){
        for (Listener listener : listeners)
            listener.listen(new CancelEvent());
    }

    public JPanel getMainPanel(){
        return view.getMainPanel();
    }

    @Override
    protected void paintComponent(Graphics g) {
        add(this.view.getMainPanel());
    }


}
