package xyz.vegaone.easytracking.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "bug")
public class BugEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "owner")
    private String owner;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "status")
    private String status;

    @Column(name = "user_story_id")
    private Long userStoryId;
}
