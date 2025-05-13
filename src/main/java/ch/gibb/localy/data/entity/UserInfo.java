package ch.gibb.localy.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import java.util.Set;

@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false,unique=true)
    private String username;

    @ManyToOne
    @JoinColumn(name="town_id")
    private Town town;

    @OneToMany(mappedBy="sender")
    private Set<Message> sentMessages;

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
    public Town getTown() {
        return town;
    }
    public void setTown(Town town) {
        this.town = town;
    }
    public Set<Message> getSentMessages() {
        return sentMessages;
    }
    public void setSentMessages(Set<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }
}
