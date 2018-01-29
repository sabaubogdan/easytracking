package xyz.vegaone.easytracking.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.vegaone.easytracking.dto.Bug;
import xyz.vegaone.easytracking.dto.Task;
import xyz.vegaone.easytracking.dto.User;
import xyz.vegaone.easytracking.dto.UserStory;
import xyz.vegaone.easytracking.service.BugService;
import xyz.vegaone.easytracking.service.ProjectService;
import xyz.vegaone.easytracking.service.TaskService;
import xyz.vegaone.easytracking.service.UserService;
import xyz.vegaone.easytracking.service.UserStoryService;

import java.util.Arrays;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    private static final String USER_NAME = "TestName";
    private static final String USER_EMAIL = "user@email.com";
    private static final String NEW_USER_EMAIL = "newuser@email.com";

    private static final String PROJECT_DESCRIPTION = "Project test";
    private static final String PROJECT_NAME = "Project name";

    private static final String USERSTORY_TITLE = "Test title";
    private static final String USERSTORY_DESCRIPTION = "Test description";
    private static final String USERSTORY_OWNER = "Test OWNER";
    private static final Integer USERSTORY_ESTIMATION = 123456;
    private static final Integer USERSTORY_PRIORITY = 1;
    private static final Long USERSTORY_PROJECT_ID = 1L;
    private static final String USERSTORY_STATUS = "Temporary status";

    private static final String BUG_DESCRIPTION = "Bug description";
    private static final String BUG_OWNER = "Bug owner";
    private static final Integer BUG_PRIORITY = 1;
    private static final String BUG_STATUS = "Bug status";
    private static final String BUG_TITLE = "Bug title";

    private static final String TASK_DESCRIPTION = "Task description";
    private static final String TASK_OWNER = "Task owner";
    private static final Integer TASK_PRIORITY = 1;
    private static final String TASK_STATUS = "Task status";
    private static final String TASK_TITLE = "Task title";

    private static final String NEWBUG_TITLE = "New bug title";

    @Autowired
    private UserStoryService userStoryService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private BugService bugService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Test
    public void createUserTest() {
        // when
        User savedUser = buildAndSaveUser();

        //then
        User findResult = userService.getUser(savedUser.getId());
        Assert.assertNotNull("There should have been one user saved in the database", savedUser);
        Assert.assertEquals("The user name should have matched", USER_NAME, findResult.getName());
        Assert.assertEquals("The user email should have matched", USER_EMAIL, findResult.getEmail());
    }

    @Test
    public void getUserTest() {
        //given
        User savedUser = buildAndSaveUser();

        //when
        User findResult = userService.getUser(savedUser.getId());

        //then
        Assert.assertNotNull("There should have been one user saved in the database", savedUser);
        Assert.assertEquals("The user name should have matched", savedUser.getName(), findResult.getName());
        Assert.assertEquals("The user email should have matched", savedUser.getEmail(), findResult.getEmail());
    }

    @Test
    public void deleteUserTest() {
        //given
        User savedUser = buildAndSaveUser();
        UserStory userStory = buildAndSaveUserStory(savedUser);
        Bug bug = buildAndSaveBug(userStory.getId(), savedUser);
        Task task = buildAndSaveTask(userStory.getId(), savedUser);

        //when
        userService.deleteUserById(savedUser.getId());

        //then
        Assert.assertNull("User should have been deleted from db", userService.getUser(savedUser.getId()));
        Assert.assertNotNull("UserStory should not have been deleted from db", userStoryService.getUserStory(userStory.getId()));
        Assert.assertNotNull("Task should not have been deleted from db", taskService.getTask(task.getId()));
        Assert.assertNotNull("Bug should not have been deleted from db", bugService.getBug(bug.getId()));

    }

    @Test
    public void updateUserService() {
        //given
        User savedUser = buildAndSaveUser();

        //when
        savedUser.setEmail(NEW_USER_EMAIL);
        User updatedUser = userService.updateUser(savedUser);
        User findResult = userService.getUser(savedUser.getId());

        //then
        Assert.assertNotNull("There should have been one user in the database", findResult);
        Assert.assertEquals("The email should have matched", NEW_USER_EMAIL, findResult.getEmail());

    }

    private UserStory buildAndSaveUserStory(User user) {
        UserStory userStory = new UserStory();
        userStory.setTitle(USERSTORY_TITLE);
        userStory.setDescription(USERSTORY_DESCRIPTION);
        userStory.setOwner(USERSTORY_OWNER);
        userStory.setEstimation(USERSTORY_ESTIMATION);
        userStory.setPriority(USERSTORY_PRIORITY);
        userStory.setStatus(USERSTORY_STATUS);
        userStory.setProjectId(USERSTORY_PROJECT_ID);
        userStory.setUserList(Arrays.asList(user));

        return userStoryService.createUserStory(userStory);
    }

    private Bug buildAndSaveBug(Long userStoryId, User user) {

        Bug bug = new Bug();
        bug.setUserStoryId(userStoryId);
        bug.setDescription(BUG_DESCRIPTION);
        bug.setOwner(BUG_OWNER);
        bug.setPriority(BUG_PRIORITY);
        bug.setStatus(BUG_STATUS);
        bug.setTitle(BUG_TITLE);
        bug.setUserList(Arrays.asList(user));

        return bugService.createBug(bug);

    }

    private User buildAndSaveUser() {
        User user = new User();
        user.setName(USER_NAME);
        user.setEmail(USER_EMAIL);

        return userService.createUser(user);
    }

    private Task buildAndSaveTask(Long userStoryId, User user) {

        Task task = new Task();
        task.setUserStoryId(userStoryId);
        task.setDescription(TASK_DESCRIPTION);
        task.setOwner(TASK_OWNER);
        task.setPriority(TASK_PRIORITY);
        task.setStatus(TASK_STATUS);
        task.setTitle(TASK_TITLE);
        task.setUserList(Arrays.asList(user));

        return taskService.createTask(task);

    }
}
