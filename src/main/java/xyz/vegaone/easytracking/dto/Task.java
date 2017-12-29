package xyz.vegaone.easytracking.dto;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
public class Task {

    private Long id;

    private String title;

    private String description;

    private String owner;

    private Integer priority;

    private String status;

    private UserStory userStory;
}
