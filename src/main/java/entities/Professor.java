package entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQuery(name = "Professor.checkCredentials", query = "SELECT p FROM Professor p  WHERE p.username = ?1 and p.password = ?2")
@NamedQuery(name = "Professor.findProfessorByUserame", query = "SELECT p FROM Professor p WHERE p.username = :username")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "professor", cascade = CascadeType.ALL)
    private List<Video> videoList;

    public Professor() {
    }

    public Professor(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    public void removeVideo(Video toErase){
        videoList.removeIf(video -> video.getId() == toErase.getId());
    }


}
