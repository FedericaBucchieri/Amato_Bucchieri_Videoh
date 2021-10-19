package sceneManager;

import EventManagement.*;
import EventManagement.Event;
import GeneralLogin.GeneralLoginScene;
import StudentEndVisionScene.StudentEndVisionScene;
import StudentHomePage.StudentHomePageScene;
import StudentInsertCode.InsertCodeScene;
import StudentLogin.StudentLoginScene;
import Utils.Utils;
import entities.Professor;
import ProfessorHomePage.ProfessorHomePageScene;
import ProfessorLoginScene.ProfessorLoginScene;
import entities.Video;

import javax.swing.*;
import java.awt.*;


public class SceneManager extends JFrame implements Listener {
    // The current Scene instance displayed in the application
    private Scene currentScene;
    // The content Pane of the main Jframe of the applicaiton
    private Container container;
    // the layout of the container
    private CardLayout cardLayout;

    public SceneManager() {
        super("VIDEOH");
        setupFrame();
        setupGeneralLayout();
        goToGeneralLoginPage();

        pack();
    }

    /**
     * This method sets up the preferences for appearance and dimensions of the frame
     */
    private void setupFrame(){
        setPreferredSize(new Dimension(Utils.JFRAME_WIDTH, Utils.JFRAME_HEIGHT));
        setLocation(Utils.JFRAME_LOCATION_X,Utils.JFRAME_LOCATION_Y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method sets up the cardLayout as basic layout of the frame to enable scenes change
     */
    private void setupGeneralLayout(){
        cardLayout = new CardLayout();
        container = getContentPane();
        container.setLayout(cardLayout);
    }


    @Override
    public void listen(Event event) {
        if(event.getClass().equals(ProfessorLoginEvent.class)) {
            goToProfessorHomePage(((ProfessorLoginEvent) event).getProfessor());
        }
        else if(event.getClass().equals(LoginStudEvent.class)){
            goToStudLoginPage();
        }
        else if (event.getClass().equals(LoginProfEvent.class)){
            goToProfLoginPage();
        }
        else if (event.getClass().equals(StudentLoginEvent.class)){
            goToInsertCodePage(((StudentLoginEvent) event).getUsername());
        }
        else if (event.getClass().equals(BackEvent.class)){
            handleBackEvent();
        }
        else if (event.getClass().equals(GoToVideoEvent.class)){
            GoToVideoEvent e = ((GoToVideoEvent) event);
            goToVideoStudentPage(e.getVideo(), e.getUsername());
        }
        else if (event.getClass().equals(EndReviewEvent.class)){
            goToStudentEndVisionScene();
        }
        else if (event.getClass().equals(LogoutEvent.class)){
            goToGeneralLoginPage();
        }

    }

    /**
     * This method allows going to the Scene "GeneralLoginScene"
     */
    private void goToGeneralLoginPage() {
        GeneralLoginScene generalLoginScene = new GeneralLoginScene(this);
        currentScene = generalLoginScene;

        container.add(generalLoginScene.getMainPanel());
        cardLayout.next(container);
    }

    /**
     * This method allows going to the Scene "StudentHomePage"
     * @param video the video to be displayed in the scene
     * @param username the username of the student that want to go to that scene
     */
    private void goToVideoStudentPage(Video video, String username) {
        StudentHomePageScene studentHomePageScene = new StudentHomePageScene(this, video, username);
        currentScene = studentHomePageScene;
        container.add(studentHomePageScene.getMainPanel());
        cardLayout.next(container);
    }

    /**
     * This method allows going to the Scene "StudentLoginScene"
     */
    private void goToStudLoginPage() {
        StudentLoginScene studentLoginScene = new StudentLoginScene(this);
        currentScene = studentLoginScene;

        container.add(studentLoginScene.getMainPanel());
        cardLayout.next(container);
    }

    /**
     * This method allows going to the Scene "ProfessorLoginScene"
     */
    private void goToProfLoginPage() {
        ProfessorLoginScene professorLoginScene = new ProfessorLoginScene(this);
        currentScene = professorLoginScene;

        container.add(professorLoginScene.getMainPanel());
        cardLayout.next(container);
    }

    /**
     * This method allows going to the Scene "ProfessorHomePage"
     * @param professor the professor entity that is logged in the application
     */
    private void goToProfessorHomePage(Professor professor){
        ProfessorHomePageScene professorHomePageScene = new ProfessorHomePageScene(this, professor);
        currentScene = professorHomePageScene;

        container.add(professorHomePageScene.getMainPanel());
        cardLayout.next(container);
    }

    /**
     * This method allows going to the Scene "StudentEndVisionScene"
     */
    private void goToStudentEndVisionScene(){
        StudentEndVisionScene studentEndVisionScene = new StudentEndVisionScene(this);
        studentEndVisionScene.installUI();
        currentScene = studentEndVisionScene;

        container.add(studentEndVisionScene.getMainPanel());
        cardLayout.next(container);
    }

    /**
     * This method helps to handle BackEvents, directing the scene to the correct one accordingly to the currentScene displayed in the application
     */
    private void handleBackEvent(){
        if(currentScene instanceof ProfessorLoginScene)
            goToGeneralLoginPage();
        else if(currentScene instanceof StudentLoginScene)
            goToGeneralLoginPage();
        else if(currentScene instanceof InsertCodeScene)
            goToStudLoginPage();
        else if (currentScene instanceof StudentHomePageScene){
            StudentHomePageScene instanceOfCurrentScene = (StudentHomePageScene) currentScene;
            goToInsertCodePage(instanceOfCurrentScene.getUsername());
        }
        else if (currentScene instanceof StudentEndVisionScene){
            goToGeneralLoginPage();
        }
    }

    /**
     * This method allows going to the Scene "InsertCodeScene"
     * @param username the username of the student that is logged in the application
     */
    private void goToInsertCodePage(String username) {
        InsertCodeScene insertCodeScene = new InsertCodeScene(this, username);
        currentScene = insertCodeScene;

        container.add(insertCodeScene.getMainPanel());
        cardLayout.next(container);
    }



}
