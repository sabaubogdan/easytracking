package xyz.vegaone.easytracking.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
public class UserStory {

    private Long id;

    private String title;

    private String description;

    private String owner;

    private Integer priority;

    private Integer estimation;

}
