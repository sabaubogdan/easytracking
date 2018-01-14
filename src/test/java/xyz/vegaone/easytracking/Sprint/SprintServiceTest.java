package xyz.vegaone.easytracking.Sprint;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.vegaone.easytracking.dto.Sprint;
import xyz.vegaone.easytracking.dto.UserStory;
import xyz.vegaone.easytracking.service.SprintService;
import xyz.vegaone.easytracking.service.UserStoryService;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SprintServiceTest {

    private static final Date SPRINT_START_DATE = new Date(2017, 1, 10);
    private static final Date SPRINT_END_DATE = new Date(2017, 1, 20);
    private static final Integer SPRINT_NUMBER = 1;
    private static final Integer NEW_SPRINT_NUMBER = 9;


    public static final String TITLE = "Test title";
    public static final String DESCRIPTION = "Test description";
    public static final String OWNER = "Test OWNER";
    public static final Integer ESTIMATION = 123456;
    public static final Integer PRIORITY = 1;
    public static final Long PROJECT_ID = 1L;
    public static final String STATUS = "Temporary status";
    public static final Long SPRINT_ID = 1L;

    @Autowired
    private SprintService sprintService;

    @Autowired
    private UserStoryService userStoryService;

    @Test
    public void createSprintTest() {
        //given

        //when
        Sprint savedSprint = buildAndSaveSprint();

        //then
        Assert.assertNotNull("There should have been one Sprint saved in the database.", savedSprint);
        Assert.assertEquals("The sprint end date should have matched", SPRINT_END_DATE, savedSprint.getEndDate());
        Assert.assertEquals("The sprint end date should have matched", SPRINT_START_DATE, savedSprint.getStartDate());
        Assert.assertEquals("The sprint end date should have matched", SPRINT_NUMBER, savedSprint.getSprintNumber());
    }

    @Test
    public void getSprintTest() {
        //given
        Sprint savedSprint = sprintService.createSprint(buildAndSaveSprint());

        UserStory userStory = new UserStory();
        userStory.setTitle(TITLE);
        userStory.setDescription(DESCRIPTION);
        userStory.setOwner(OWNER);
        userStory.setEstimation(ESTIMATION);
        userStory.setPriority(PRIORITY);
        userStory.setStatus(STATUS);
        userStory.setProjectId(PROJECT_ID);
        userStory.setSprintId(SPRINT_ID);

        UserStory savedUserStory = userStoryService.createUserStory(userStory);

        //when
        Sprint findSprint = sprintService.getSprint(savedSprint.getId());

        //then
        Assert.assertNotNull("There should have been one Sprint saved in the database.", findSprint);
        Assert.assertEquals("The sprint end date should have matched", SPRINT_END_DATE, findSprint.getEndDate());
        Assert.assertEquals("The sprint end date should have matched", SPRINT_START_DATE, findSprint.getStartDate());
        Assert.assertEquals("The sprint end date should have matched", SPRINT_NUMBER, findSprint.getSprintNumber());
        Assert.assertEquals("There should have been a userstory associated with the sprint", 1, findSprint.getUserStoryList().size());

    }

    @Test
    public void deleteSpring() {
        //given
        Sprint savedSprint = buildAndSaveSprint();

        //when
        sprintService.deleteSprint(savedSprint.getId());

        //then
        Assert.assertNull("The sprint should have been deleted from the db", sprintService.getSprint(savedSprint.getId()));
    }

    @Test
    public void updateSprintTest() {
        //given
        Sprint savedSprint = buildAndSaveSprint();

        //when
        savedSprint.setSprintNumber(NEW_SPRINT_NUMBER);
        Sprint updatedSprint = sprintService.updateSprint(savedSprint);

        //then
        Assert.assertNotNull("There should have been one sprint updated in the database.", updatedSprint);
        Assert.assertEquals("The userStory title should have matched", NEW_SPRINT_NUMBER, updatedSprint.getSprintNumber());
    }

    private Sprint buildAndSaveSprint() {
        Sprint sprint = new Sprint();
        sprint.setEndDate(SPRINT_END_DATE);
        sprint.setStartDate(SPRINT_START_DATE);
        sprint.setSprintNumber(SPRINT_NUMBER);

        Sprint savedSprint = sprintService.createSprint(sprint);

        return savedSprint;
    }
}
