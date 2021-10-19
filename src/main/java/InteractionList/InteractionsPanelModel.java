package InteractionList;

import VideoPlayer.VideoBox;
import entities.GenericInteraction;
import entities.Interaction;
import entities.Question;
import Utils.Utils;
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

    /**
     * This method insert a positive interaction in the given timestamp into the database
     * @param timestamp the timestamp to insert the interaction too
     * @return the new interaction created
     */
    public Interaction insertPositiveInteraction(int timestamp){
        InteractionService service = new InteractionService();
        Interaction interaction = service.createInteraction(Utils.POSITIVE_INTERACTION, videoBox.getVideoId(), timestamp);
        interactionList.add(interaction);

        return interaction;
    }

    /**
     * This method insert a negative interaction in the given timestamp into the database
     * @param timestamp the timestamp to insert the interaction too
     * @return the new interaction created
     */
    public Interaction insertNegativeInteraction(int timestamp){
        InteractionService service = new InteractionService();
        Interaction interaction = service.createInteraction(Utils.NEGATIVE_INTERACTION, videoBox.getVideoId(), timestamp);
        interactionList.add(interaction);

        return interaction;
    }

    public List<GenericInteraction> getInteractionList() {
        return interactionList;
    }

    /**
     * This method sets the current question timestamp to the given integer
     * @param timestamp the timestamp to set
     */
    public void setLastQuestionTimestamp(int timestamp){
        currentQuestionTimestamp = timestamp;
    }

    /**
     * This method insert new question in the database, with the given text as body
     * @param text the text of the question
     * @return the new question created
     */
    public Question insertNewQuestion(String text){
        QuestionService service = new QuestionService();
        Question question = service.createQuestion(text, username, videoBox.getVideoId(), currentQuestionTimestamp);
        interactionList.add(question);

        return question;
    }


    /**
     *  this method populates the interaction panel taking all the interaction for the video with the given id
     * @param videoId the id of the video to populate the interaction panel for
     */
    public void populateInteractionListPerVideo(int videoId) {
        InteractionService service = new InteractionService();
        List<Interaction> allInteractionPerVideoToAdd = service.findInteractionsByVideo(videoId);
        updateInteractionList(allInteractionPerVideoToAdd);

        QuestionService questService = new QuestionService();
        List<Question> allQuestionPerVideoToAdd = questService.findQuestionsByVideo(videoId);
        updateInteractionList_Question(allQuestionPerVideoToAdd);



    }

    /**
     * This method updates the interactionList attribute adding interactions to the list if not presents
     * @param allInteractionPerVideoToAdd the interactions to be added to the list
     */
    private void updateInteractionList(List<Interaction> allInteractionPerVideoToAdd) {
        for (Interaction interaction: allInteractionPerVideoToAdd){
            if (!checkPresence(interaction.getId()))   {
                  interactionList.add(interaction);
            }
        }
    }

    /**
     * This method updates the interactionList attribute adding questions to the list if not presents
     * @param allInteractionPerVideoToAdd the questions to be added to the list
     */
   private void updateInteractionList_Question(List<Question> allInteractionPerVideoToAdd) {
       for (Question question: allInteractionPerVideoToAdd){
           if (!checkPresence(question.getId())) {
               interactionList.add(question);
           }
       }
   }

    /**
     * This method checks if a given general interaction id is already present in the interactionList
     * @param id the id to check the general interaction for
     * @return true if there is an interaction with the given id, false otherwise.
     */
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
