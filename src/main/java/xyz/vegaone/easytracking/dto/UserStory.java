package xyz.vegaone.easytracking.dto;

import java.util.List;

public class UserStory {

    private Long id;

    private String title;

    private String description;

    private String owner;

    private Integer priority;

    private Integer estimation;

    private String status;

    private Long projectId;

    private List<Task> taskList;

    private List<Bug> bugList;

    private Long sprintId;

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

    public Integer getEstimation() {
        return estimation;
    }

    public void setEstimation(Integer estimation) {
        this.estimation = estimation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Bug> getBugList() {
        return bugList;
    }

    public void setBugList(List<Bug> bugList) {
        this.bugList = bugList;
    }

    public Long getSprintId() {
        return sprintId;
    }

    public void setSprintId(Long sprintId) {
        this.sprintId = sprintId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserStory{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", owner='" + owner + '\'' +
                ", priority=" + priority +
                ", estimation=" + estimation +
                ", status='" + status + '\'' +
                ", projectId=" + projectId +
                ", taskList=" + taskList +
                ", bugList=" + bugList +
                ", sprintId=" + sprintId +
                ", user=" + user +
                '}';
    }
}
