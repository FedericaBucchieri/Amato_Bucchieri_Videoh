package EventManagement;

public class StudentLoginEvent implements Event{
    private String username;

    public StudentLoginEvent(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
