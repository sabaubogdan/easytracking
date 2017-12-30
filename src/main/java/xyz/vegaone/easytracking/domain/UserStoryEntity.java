package xyz.vegaone.easytracking.domain;

import javax.persistence.*;

@Entity
@Table(name = "user_story")
public class UserStoryEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "owner")
    private String owner;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "estimation")
    private Integer estimation;

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
}
