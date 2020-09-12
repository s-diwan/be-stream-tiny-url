package com.tinyurl.tinyserver.model;

import javax.persistence.*;

@Entity
@Table(name = "group_admin")
public class GroupAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO ,generator="group_admin_seq")
    private int id;
    private String userName;
    private int userId;
    @Column(name = "group_id")
    private int groupId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
