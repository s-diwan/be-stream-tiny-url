package com.tinyurl.tinyserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.*;

import java.util.List;


@Entity
@Table(name="group_table")
public class Group {

    @Id
    private int id;
    private String groupName;
    private String groupType;
    private String groupAdmin;

    @ManyToOne(cascade = {CascadeType.PERSIST , CascadeType.MERGE , CascadeType.DETACH , CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(targetEntity=Card.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id", referencedColumnName="id")
    private List<Card> cards;

    @OneToMany(targetEntity=GroupAdmin.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id", referencedColumnName="id")
    private List<GroupAdmin> GroupAdmins;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<GroupAdmin> getGroupAdmins() {
        return GroupAdmins;
    }

    public void setGroupAdmins(List<GroupAdmin> groupAdmins) {
        GroupAdmins = groupAdmins;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getGroupAdmin() {
        return groupAdmin;
    }

    public void setGroupAdmin(String groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    @Override
    public String  toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", groupType='" + groupType + '\'' +
                ", groupAdmin='" + groupAdmin + '\'' +
                ", user=" + user +
                '}';
    }

}
