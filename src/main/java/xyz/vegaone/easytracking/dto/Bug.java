package xyz.vegaone.easytracking.dto;

import java.util.List;

public class Bug {

    private Long id;

    private String title;

    private String description;

    private String owner;

    private Integer priority;

    private String status;

    private Long userStoryId;

    private Long estimation;

    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserStoryId() {
        return userStoryId;
    }

    public void setUserStoryId(Long userStoryId) {
        this.userStoryId = userStoryId;
    }

    public Long getEstimation() {
        return estimation;
    }

    public void setEstimation(Long estimation) {
        this.estimation = estimation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
