package entities;

import jakarta.persistence.*;

@Entity
public class Question implements GenericInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String text;
    private String student;
    private int timestamp;

    @ManyToOne
    @JoinColumn(name = "video")
    private Video video;

    public Question() {
    }

    public Question(String text, String student, Video video, int timestamp) {
        this.text = text;
        this.student = student;
        this.video = video;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    @Override
    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
