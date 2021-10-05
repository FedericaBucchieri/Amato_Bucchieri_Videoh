package sceneManager;

import EventManagement.*;
import EventManagement.Event;
import GeneralLogin.GeneralLoginScene;
import StudentHomePage.StudentHomePageScene;
import StudentInsertCode.InsertCodeScene;
import StudentLogin.StudentLoginScene;
import entities.Professor;
import ProfessorHomePage.ProfessorHomePage;
import ProfessorLoginScene.ProfessorLoginScene;
import entities.Video;

import javax.swing.*;
import java.awt.*;
import java.io.File;


public class SceneManager extends JFrame implements Listener {
    private Scene currentScene;
    private CardLayout cardLayout;
    private Container container;

    public SceneManager() {
        super("VIDEOH");
        setPreferredSize(new Dimension(Utils.JFRAME_WIDTH, Utils.JFRAME_HEIGHT));
        setLocation(300,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardLayout = new CardLayout();
        container = getContentPane();
        container.setLayout(cardLayout);

        initializeFirstScene();

        pack();
    }

    private void initializeFirstScene(){
         goToGeneralLoginPage();

         /*
        ProfessorService service = new ProfessorService();
        Professor professor = null;
        try {
            professor = service.checkCredentials("prof1", "pass1");
        } catch (CredentialsException | UserNotRegisteredException e) {
            e.printStackTrace();
        }

        goToProfessorHomePage(professor);

          */



//        ProfessorLoginScene professorLoginScene = new ProfessorLoginScene(this);
//        professorLoginScene.setVisible(true);
//        currentScene = professorLoginScene;
//        add(professorLoginScene);

        /*
        GeneralLoginScene generalLoginScene = new GeneralLoginScene(this);
        currentScene = generalLoginScene;
        cardLayout.addLayoutComponent(generalLoginScene.getMainPanel(), "1");
        add(generalLoginScene.getMainPanel());

         */


//        StudentLoginScene studentLoginScene = new StudentLoginScene(this);
//        studentLoginScene.setVisible(true);
//        currentScene = studentLoginScene;
//        add(studentLoginScene);




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
        else if (event.getClass().equals(LogoutEvent.class)){
            goToGeneralLoginPage();
        }
    }

    private void goToVideoStudentPage(Video video, String username) {
        StudentHomePageScene studentHomePageScene = new StudentHomePageScene(this, video, username);
        currentScene = studentHomePageScene;
        container.add(studentHomePageScene.getMainPanel());
        cardLayout.next(container);
    }

    private void goToGeneralLoginPage() {
        GeneralLoginScene generalLoginScene = new GeneralLoginScene(this);
        currentScene = generalLoginScene;

        container.add(generalLoginScene.getMainPanel());
        cardLayout.next(container);
    }

    private void goToStudLoginPage() {
        StudentLoginScene studentLoginScene = new StudentLoginScene(this);
        currentScene = studentLoginScene;

        container.add(studentLoginScene.getMainPanel());
        cardLayout.next(container);
    }

    private void goToProfLoginPage() {
        ProfessorLoginScene professorLoginScene = new ProfessorLoginScene(this);
        currentScene = professorLoginScene;

        container.add(professorLoginScene.getMainPanel());
        cardLayout.next(container);
    }

    private void goToProfessorHomePage(Professor professor){
        ProfessorHomePage professorHomePage = new ProfessorHomePage(this, professor);
        currentScene = professorHomePage;

        container.add(professorHomePage.getMainPanel());
        cardLayout.next(container);
    }

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
    }

    private void goToInsertCodePage(String username) {
        InsertCodeScene studentLoginScene = new InsertCodeScene(this, username);
        currentScene = studentLoginScene;

        container.add(studentLoginScene.getMainPanel());
        cardLayout.next(container);
    }

}
