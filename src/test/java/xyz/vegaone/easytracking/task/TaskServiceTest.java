package xyz.vegaone.easytracking.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.vegaone.easytracking.dto.Task;
import xyz.vegaone.easytracking.dto.UserStory;
import xyz.vegaone.easytracking.service.TaskService;
import xyz.vegaone.easytracking.service.UserStoryService;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskServiceTest {
    private static final String DESCRIPTION = "Task description";
    private static final String OWNER = "Task owner";
    private static final Integer PRIORITY = 1;
    private static final String STATUS = "Task status";
    private static final String TITLE = "Task title";

    public static final String USERSTORY_TITLE = "Test title";
    public static final String USERSTORY_DESCRIPTION = "Test description";
    public static final String USERSTORY_OWNER = "Test OWNER";
    public static final Integer USERSTORY_ESTIMATION = 123456;
    public static final Integer USERSTORY_PRIORITY = 1;

    public static final Long PROJECT_ID = 1L;

    public static final String NEWUSERSTORY_TITLE = "New user story";

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserStoryService userStoryService;

    @Test
    public void createTaskTest() {
        //given
        UserStory userStory = new UserStory();
        userStory.setTitle(USERSTORY_TITLE);
        userStory.setDescription(USERSTORY_DESCRIPTION);
        userStory.setOwner(USERSTORY_OWNER);
        userStory.setEstimation(USERSTORY_ESTIMATION);
        userStory.setPriority(USERSTORY_PRIORITY);
        userStory.setProjectId(PROJECT_ID);
        userStory.setStatus(STATUS);

        UserStory savedUserStory = userStoryService.createUserStory(userStory);

        Task task = new Task();
        task.setUserStoryId(savedUserStory.getId());
        task.setDescription(DESCRIPTION);
        task.setOwner(OWNER);
        task.setPriority(PRIORITY);
        task.setStatus(STATUS);
        task.setTitle(TITLE);
        //when
        Task savedTask = taskService.createTask(task);

        //then
        Assert.assertNotNull("There should have been onr task saved in the database", savedTask);
        Assert.assertEquals("User story id should match the user story task id", savedUserStory.getId(), savedTask.getUserStoryId());
        Assert.assertEquals("The userStory name should have matched", TITLE, savedTask.getTitle());
        Assert.assertEquals("The userStory description should have matched", DESCRIPTION, savedTask.getDescription());
        Assert.assertEquals("The userStory owner should have matched", OWNER, savedTask.getOwner());
        Assert.assertEquals("The userStory estimation should have matched", STATUS, savedTask.getStatus());
        Assert.assertEquals("The userStory priority should have matched", PRIORITY, savedTask.getPriority());
    }

    @Test
    public void getTaskTest() {
        //given
        UserStory userStory = new UserStory();
        userStory.setTitle(USERSTORY_TITLE);
        userStory.setDescription(USERSTORY_DESCRIPTION);
        userStory.setOwner(USERSTORY_OWNER);
        userStory.setEstimation(USERSTORY_ESTIMATION);
        userStory.setPriority(USERSTORY_PRIORITY);
        userStory.setProjectId(PROJECT_ID);
        userStory.setStatus(STATUS);

        UserStory savedUserStory = userStoryService.createUserStory(userStory);

        Task task = new Task();
        task.setUserStoryId(savedUserStory.getId());
        task.setDescription(DESCRIPTION);
        task.setOwner(OWNER);
        task.setPriority(PRIORITY);
        task.setStatus(STATUS);
        task.setTitle(TITLE);
        Task savedTask = taskService.createTask(task);

        //when
        Task findTask = taskService.getTask(savedTask.getId());

        //then
        Assert.assertNotNull("There should have been one task in the database", findTask);
        Assert.assertEquals("The task title should have matched", TITLE, findTask.getTitle());
        Assert.assertEquals("The task description should have matched", DESCRIPTION, findTask.getDescription());
        Assert.assertEquals("The task owner should have matched", OWNER, findTask.getOwner());
        Assert.assertEquals("The task estimation should have matched", STATUS, findTask.getStatus());
        Assert.assertEquals("The task priority should have matched", PRIORITY, findTask.getPriority());

    }

    @Test
    public void deleteTaskTest() {
        //given
        UserStory userStory = new UserStory();
        userStory.setTitle(USERSTORY_TITLE);
        userStory.setDescription(USERSTORY_DESCRIPTION);
        userStory.setOwner(USERSTORY_OWNER);
        userStory.setEstimation(USERSTORY_ESTIMATION);
        userStory.setPriority(USERSTORY_PRIORITY);
        userStory.setProjectId(PROJECT_ID);
        userStory.setStatus(STATUS);

        UserStory savedUserStory = userStoryService.createUserStory(userStory);

        Task task = new Task();
        task.setUserStoryId(savedUserStory.getId());
        task.setDescription(DESCRIPTION);
        task.setOwner(OWNER);
        task.setPriority(PRIORITY);
        task.setStatus(STATUS);
        task.setTitle(TITLE);

        Task savedTask = taskService.createTask(task);

        //when
        taskService.deleteTask(savedTask.getId());

        //then
        Assert.assertNull("Task should have been deleted from db", taskService.getTask(savedTask.getId()));
    }

    @Test
    public void updateTaskTest() {
        //given
        UserStory userStory = new UserStory();
        userStory.setTitle(USERSTORY_TITLE);
        userStory.setDescription(USERSTORY_DESCRIPTION);
        userStory.setOwner(USERSTORY_OWNER);
        userStory.setEstimation(USERSTORY_ESTIMATION);
        userStory.setPriority(USERSTORY_PRIORITY);
        userStory.setProjectId(PROJECT_ID);
        userStory.setStatus(STATUS);

        UserStory savedUserStory = userStoryService.createUserStory(userStory);

        Task task = new Task();
        task.setUserStoryId(savedUserStory.getId());
        task.setDescription(DESCRIPTION);
        task.setOwner(OWNER);
        task.setPriority(PRIORITY);
        task.setStatus(STATUS);
        task.setTitle(TITLE);

        Task savedTask = taskService.createTask(task);
        //when
        savedTask.setTitle(NEWUSERSTORY_TITLE);
        Task updatedTask = taskService.updateTask(savedTask);

        //then
        Assert.assertNotNull("There should have been one task in the database", updatedTask);
        Assert.assertEquals("The task title should have matched", NEWUSERSTORY_TITLE, updatedTask.getTitle());

    }
    @Test
    public void findAllByUserStoryIdTest() {
        //given
        UserStory userStory = new UserStory();
        userStory.setTitle(USERSTORY_TITLE);
        userStory.setDescription(USERSTORY_DESCRIPTION);
        userStory.setOwner(USERSTORY_OWNER);
        userStory.setEstimation(USERSTORY_ESTIMATION);
        userStory.setPriority(USERSTORY_PRIORITY);
        userStory.setProjectId(PROJECT_ID);
        userStory.setStatus(STATUS);

        UserStory savedUserStory = userStoryService.createUserStory(userStory);

        Task task = new Task();
        task.setUserStoryId(savedUserStory.getId());
        task.setDescription(DESCRIPTION);
        task.setOwner(OWNER);
        task.setPriority(PRIORITY);
        task.setStatus(STATUS);
        task.setTitle(TITLE);
        Task savedTask = taskService.createTask(task);

        Task task2 = new Task();
        task2.setUserStoryId(savedUserStory.getId());
        task2.setDescription(DESCRIPTION);
        task2.setOwner(OWNER);
        task2.setPriority(PRIORITY);
        task2.setStatus(STATUS);
        task2.setTitle(TITLE);
        Task savedTask2 = taskService.createTask(task);

        //when
        List<Task> findTask = taskService.findAllByUserStoryId(savedUserStory.getId());

        //then
        Assert.assertNotNull("There should have been one task in the database", findTask);
        Assert.assertEquals("There should have been two tasks associated with the user story Id", 2, findTask.size());
        Assert.assertEquals("The task description should have matched", savedUserStory.getId(), findTask.get(0).getUserStoryId());
        Assert.assertEquals("The task description should have matched", savedUserStory.getId(), findTask.get(1).getUserStoryId());

    }



}
