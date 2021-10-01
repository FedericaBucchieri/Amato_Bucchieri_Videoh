package entities;

import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date timestamp;
    private int type;

    public Interaction() {
    }

    public Interaction(int type, Video video) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
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
