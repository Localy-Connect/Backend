package ch.gibb.localy.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import java.util.Set;

@Entity
@Table(name = "town")
public class Town {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false,unique=true)
    private String name;

    @OneToMany(mappedBy="town")
    private Set<UserInfo> userInfos;

    @OneToMany(mappedBy="town")
    private Set<Message> messages;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<UserInfo> getUserInfos() {
        return userInfos;
    }
    public void setUserInfos(Set<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }
    public Set<Message> getMessages() {
        return messages;
    }
    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
