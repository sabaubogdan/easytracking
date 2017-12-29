package xyz.vegaone.easytracking.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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

}
