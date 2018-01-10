package xyz.vegaone.easytracking.tag;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.vegaone.easytracking.dto.Bug;
import xyz.vegaone.easytracking.dto.Tag;
import xyz.vegaone.easytracking.dto.Task;
import xyz.vegaone.easytracking.dto.UserStory;
import xyz.vegaone.easytracking.service.BugService;
import xyz.vegaone.easytracking.service.TagService;
import xyz.vegaone.easytracking.service.TaskService;
import xyz.vegaone.easytracking.service.UserStoryService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TagServiceTest {

    public static final String TAG_NAME = "Tag name";
    public static final String TAG_NEWNAME = "Tag name";

    public static final String BUG_DESCRIPTION = "Bug description";
    public static final String BUG_OWNER = "Bug owner";
    public static final Integer BUG_PRIORITY = 1;
    public static final String BUG_STATUS = "Bug status";
    public static final String BUG_TITLE = "Bug title";

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

    @Autowired
    private BugService bugService;

    @Autowired
    private UserStoryService userStoryService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TagService tagService;

    @Test
    public void createTagTest() {
        //given

        //when
        Tag savedTag = tagService.createTag(buildAndSaveTag());

        //then

        Assert.assertNotNull("There should have been one tag saved in the database", savedTag);
        Assert.assertEquals("The tag name name should have matched", TAG_NAME, savedTag.getName());
    }

    @Test
    public void getTagTest() {
        //given
        Tag savedTag = tagService.createTag(buildAndSaveTag());

        //when
        Tag findTag = tagService.getTag(savedTag.getId());

        //then
        Assert.assertNotNull("There should have been one tag in the database", findTag);
    }

    @Test
    public void deleteTag() {
        //given
        Tag savedTag = tagService.createTag(buildAndSaveTag());

        //when
        tagService.deleteTag(savedTag.getId());

        //then
        Assert.assertNull("Task should have been deleted from db", tagService.getTag(savedTag.getId()));
    }

    @Test
    public void updateTagTest() {
        //given
        Tag savedTag = tagService.createTag(buildAndSaveTag());

        //when
        savedTag.setName(TAG_NEWNAME);

        //then
        Assert.assertNotNull("There should have been one tag in the database", savedTag);
        Assert.assertEquals("The task title should have matched", TAG_NEWNAME, savedTag.getName());
    }

    private UserStory buildAndSaveUserStory() {
        UserStory userStory = new UserStory();
        userStory.setTitle(TITLE);
        userStory.setDescription(DESCRIPTION);
        userStory.setOwner(OWNER);
        userStory.setEstimation(ESTIMATION);
        userStory.setPriority(PRIORITY);
        userStory.setStatus(STATUS);
        userStory.setProjectId(PROJECT_ID);

        UserStory savedUserStory = userStoryService.createUserStory(userStory);

        return savedUserStory;
    }

    private Bug buildAndSaveBug() {
        Bug bug = new Bug();
        bug.setUserStoryId(buildAndSaveUserStory().getId());
        bug.setDescription(BUG_DESCRIPTION);
        bug.setOwner(BUG_OWNER);
        bug.setPriority(BUG_PRIORITY);
        bug.setStatus(BUG_STATUS);
        bug.setTitle(BUG_TITLE);


        //when
        Bug savedBug = bugService.createBug(bug);

        return savedBug;
    }

    private Task buildAndSaveTask() {
        Task task = new Task();
        task.setUserStoryId(buildAndSaveUserStory().getId());
        task.setDescription(TASK_DESCRIPTION);
        task.setOwner(TASK_OWNER);
        task.setPriority(TASK_PRIORITY);
        task.setStatus(TASK_STATUS);
        task.setTitle(TASK_TITLE);

        Task savedTask = taskService.createTask(task);

        return savedTask;
    }

    private Tag buildAndSaveTag() {
        Tag tag = new Tag();
        tag.setBugId(buildAndSaveBug().getId());
        tag.setTaskId(buildAndSaveTask().getId());
        tag.setUserStoryId(buildAndSaveUserStory().getId());
        tag.setName(TAG_NAME);

        //when
        Tag savedTag = tagService.createTag(tag);

        return savedTag;
    }

}
