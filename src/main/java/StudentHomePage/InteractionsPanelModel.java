package StudentHomePage;

import entities.GenericInteraction;
import entities.Interaction;
import entities.Question;
import sceneManager.Utils;
import services.InteractionService;
import services.QuestionService;

import java.util.ArrayList;
import java.util.List;

public class InteractionsPanelModel {
    private VideoBox videoBox;
    private List<GenericInteraction> interactionList;
    private int currentQuestionTimestamp;
    private String username;
    private boolean deleteMode;

    public InteractionsPanelModel(VideoBox videoBox) {
        this.videoBox = videoBox;
        this.username = videoBox.getModel().getUsername();
        interactionList = new ArrayList<>();
    }

    public boolean isDeleteMode() {
        return deleteMode;
    }

    public void setDeleteMode(boolean deleteMode) {
        this.deleteMode = deleteMode;
    }

    public int getSliderWidth(){
        return videoBox.getSlider().getWidth();
    }

    public int getSliderMaximum(){
        return videoBox.getSlider().getMaximum();
    }

    public int getSliderValue(){
        return videoBox.getSlider().getValue();
    }

    public Interaction insertPositiveInteraction(int timestamp){
        InteractionService service = new InteractionService();
        Interaction interaction = service.createInteraction(Utils.POSITIVE_INTERACTION, videoBox.getVideoId(), timestamp);
        interactionList.add(interaction);

        return interaction;
    }

    public Interaction insertNegativeInteraction(int timestamp){
        InteractionService service = new InteractionService();
        Interaction interaction = service.createInteraction(Utils.NEGATIVE_INTERACTION, videoBox.getVideoId(), timestamp);
        interactionList.add(interaction);

        return interaction;
    }

    public List<GenericInteraction> getInteractionList() {
        return interactionList;
    }

    public void setLastQuestionTimestamp(int timestamp){
        currentQuestionTimestamp = timestamp;
    }

    public Question insertNewQuestion(String text){
        QuestionService service = new QuestionService();
        Question question = service.createQuestion(text, username, videoBox.getVideoId(), currentQuestionTimestamp);
        interactionList.add(question);

        return question;
    }


    public void populateInteractionListPerVideo(int videoId) {
        InteractionService service = new InteractionService();
        List<Interaction> allInteractionPerVideoToAdd = service.findInteractionsByVideo(videoId);
        System.out.println("****[populateInteractionListPerVideo] all interaction per video are: "+allInteractionPerVideoToAdd.size());
        updateInteractionList(allInteractionPerVideoToAdd);


        List<Question> allQuestionPerVideoToAdd = service.findQuestionByVideo(videoId);
        updateInteractionList_Question(allQuestionPerVideoToAdd);



    }

    private void updateInteractionList(List<Interaction> allInteractionPerVideoToAdd) {
        for (Interaction interaction: allInteractionPerVideoToAdd){
            if (!checkPresence(interaction.getId()))   {
                System.out.println("intetraction not already present. adedd");
                  interactionList.add(interaction);
            }
            else
                System.out.println("intetraction  already present. NOT adedd");
        }


    }

   private void updateInteractionList_Question(List<Question> allInteractionPerVideoToAdd) {
       for (Question question: allInteractionPerVideoToAdd){
           if (!checkPresence(question.getId()))   {
               System.out.println("question not already present. adedd");
               interactionList.add(question);
           }
           else
               System.out.println("question already present. NOT adedd");
       }


   }

    private boolean checkPresence(int id){
        if (interactionList.isEmpty())
            return false;
        for (GenericInteraction interaction :
                this.interactionList) {
            if (interaction.getId() == id)
                return true;
        }
        return false;
    }
}
