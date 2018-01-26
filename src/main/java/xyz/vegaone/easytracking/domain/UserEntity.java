package xyz.vegaone.easytracking.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_project",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<ProjectEntity> projectList;

    @OneToMany(mappedBy = "user")
    private List<BugEntity> bugList;

    @OneToMany(mappedBy = "user")
    private List<TaskEntity> taskList;

    @OneToMany(mappedBy = "user")
    private List<UserStoryEntity> userStoryList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ProjectEntity> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectEntity> projectList) {
        this.projectList = projectList;
    }

    public List<BugEntity> getBugList() {
        return bugList;
    }

    public void setBugList(List<BugEntity> bugList) {
        this.bugList = bugList;
    }

    public List<TaskEntity> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskEntity> taskList) {
        this.taskList = taskList;
    }

    public List<UserStoryEntity> getUserStoryList() {
        return userStoryList;
    }

    public void setUserStoryList(List<UserStoryEntity> userStoryList) {
        this.userStoryList = userStoryList;
    }
}
