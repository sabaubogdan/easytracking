package xyz.vegaone.easytracking.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Bug {

    private Long id;

    private String title;

    private String description;

    private String owner;

    private Integer priority;

    private String status;

    private UserStory userStory;
}
