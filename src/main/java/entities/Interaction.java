package entities;

import jakarta.persistence.*;

@Entity
public class Interaction implements GenericInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int timestamp;
    private int type;

    public Interaction() {
    }

    public Interaction(int type, Video video, int timestamp) {
        this.timestamp = timestamp;
        this.type = type;
        this.video = video;
    }

    @ManyToOne
    @JoinColumn(name = "video")
    private Video video;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

}
