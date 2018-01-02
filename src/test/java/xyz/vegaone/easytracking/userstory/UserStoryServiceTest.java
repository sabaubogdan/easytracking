package xyz.vegaone.easytracking.userstory;


import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.vegaone.easytracking.dto.Task;
import xyz.vegaone.easytracking.dto.UserStory;
import xyz.vegaone.easytracking.service.TaskService;
import xyz.vegaone.easytracking.service.UserStoryService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserStoryServiceTest {

    public static final String TITLE = "Test title";
    public static final String DESCRIPTION = "Test description";
    public static final String OWNER = "Test OWNER";
    public static final Integer ESTIMATION = 123456;
    public static final Integer PRIORITY = 1;
    public static final Long PROJECT_ID = 1L;
    public static final String STATUS = "Temporary status";

    private static final String TASK_DESCRIPTION = "Task description";
    private static final String TASK_OWNER = "Task owner";
    private static final Integer TASK_PRIORITY = 1;
    private static final String TASK_STATUS = "Task status";
    private static final String TASK_TITLE = "Task title";

    public static final String NEW_TITLE = "New test title";

    @Autowired
    private UserStoryService userStoryService;

    @Autowired
    private TaskService taskService;

    @Test
    public void createUserStoryTest(){
        //given
        UserStory userStory = new UserStory();
        userStory.setTitle(TITLE);
        userStory.setDescription(DESCRIPTION);
        userStory.setOwner(OWNER);
        userStory.setEstimation(ESTIMATION);
        userStory.setPriority(PRIORITY);
        userStory.setStatus(STATUS);
        userStory.setProjectId(PROJECT_ID);

        //when
        UserStory savedUserStory = userStoryService.createUserStory(userStory);

        //then
        Assert.assertNotNull("There should have been one userStory saved in the database.", savedUserStory);
        Assert.assertEquals("The userStory name should have matched", TITLE, savedUserStory.getTitle());
        Assert.assertEquals("The userStory description should have matched", DESCRIPTION, savedUserStory.getDescription());
        Assert.assertEquals("The userStory owner should have matched", OWNER, savedUserStory.getOwner());
        Assert.assertEquals("The userStory estimation should have matched", ESTIMATION, savedUserStory.getEstimation());
        Assert.assertEquals("The userStory priority should have matched", PRIORITY, savedUserStory.getPriority());
        Assert.assertEquals("The userStory priority should have matched", STATUS, savedUserStory.getStatus());
        Assert.assertEquals("The userStory priority should have matched", PROJECT_ID, savedUserStory.getProjectId());
    }

    @Test
    public void getUserStoryTest(){
        //given
        UserStory userStory = new UserStory();
        userStory.setTitle(TITLE);
        userStory.setDescription(DESCRIPTION);
        userStory.setOwner(OWNER);
        userStory.setEstimation(ESTIMATION);
        userStory.setPriority(PRIORITY);
        userStory.setStatus(STATUS);
        userStory.setProjectId(PROJECT_ID);

        UserStory savedUserStory = userStoryService.createUserStory(userStory);

        Task task = new Task();
        task.setUserStoryId(savedUserStory.getId());
        task.setDescription(TASK_DESCRIPTION);
        task.setOwner(TASK_OWNER);
        task.setPriority(TASK_PRIORITY);
        task.setStatus(TASK_STATUS);
        task.setTitle(TASK_TITLE);

        Task savedTask = taskService.createTask(task);

        //when
        UserStory findResult = userStoryService.getUserStory(savedUserStory.getId());

        //then
        Assert.assertNotNull("There should have been one userStory in the database.", findResult);
        Assert.assertEquals("The userStory name should have matched", TITLE, findResult.getTitle());
        Assert.assertEquals("There should have been a task associated with the user story", 1, findResult.getTaskList().size());
        Assert.assertEquals("The userStory description should have matched", DESCRIPTION, findResult.getDescription());
        Assert.assertEquals("The userStory owner should have matched", OWNER, findResult.getOwner());
        Assert.assertEquals("The userStory estimation should have matched", ESTIMATION, findResult.getEstimation());
        Assert.assertEquals("The userStory priority should have matched", PRIORITY, findResult.getPriority());
        Assert.assertEquals("The userStory priority should have matched", STATUS, savedUserStory.getStatus());
        Assert.assertEquals("The userStory priority should have matched", PROJECT_ID, savedUserStory.getProjectId());

    }

    @Test
    @Ignore
    public void deleteUserStoryTest(){
        //given
        UserStory userStory = new UserStory();
        userStory.setTitle(TITLE);
        userStory.setDescription(DESCRIPTION);
        userStory.setOwner(OWNER);
        userStory.setEstimation(ESTIMATION);
        userStory.setPriority(PRIORITY);
        userStory.setStatus(STATUS);
        userStory.setProjectId(PROJECT_ID);

        UserStory savedUserStory = userStoryService.createUserStory(userStory);

        Task task = new Task();
        task.setUserStoryId(savedUserStory.getId());
        task.setDescription(TASK_DESCRIPTION);
        task.setOwner(TASK_OWNER);
        task.setPriority(TASK_PRIORITY);
        task.setStatus(TASK_STATUS);
        task.setTitle(TASK_TITLE);

        Task savedTask = taskService.createTask(task);

        //when
        userStoryService.deleteUserStory(savedUserStory.getId());

        //then
        Assert.assertNull("The user story should have been deleted from the db", userStoryService.getUserStory(savedUserStory.getId()));
    }

    @Test
    public void updateUserStoryTest(){
        //given
        UserStory userStory = new UserStory();
        userStory.setTitle(TITLE);
        userStory.setDescription(DESCRIPTION);
        userStory.setOwner(OWNER);
        userStory.setEstimation(ESTIMATION);
        userStory.setPriority(PRIORITY);
        userStory.setStatus(STATUS);
        userStory.setProjectId(PROJECT_ID);

        UserStory savedUserStory = userStoryService.createUserStory(userStory);

        //when
        savedUserStory.setTitle(NEW_TITLE);
        UserStory updatedUserStory = userStoryService.updateUserStory(savedUserStory);

        //then
        Assert.assertNotNull("There should have been one userStory updated in the database.", updatedUserStory);
        Assert.assertEquals("The userStory title should have matched", NEW_TITLE, updatedUserStory.getTitle());

    }
}
