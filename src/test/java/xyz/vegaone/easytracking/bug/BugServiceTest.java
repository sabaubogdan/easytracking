package xyz.vegaone.easytracking.bug;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.vegaone.easytracking.dto.Bug;
import xyz.vegaone.easytracking.dto.UserStory;
import xyz.vegaone.easytracking.service.BugService;
import xyz.vegaone.easytracking.service.UserStoryService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BugServiceTest {
    public static final String USERSTORY_TITLE = "Test title";
    public static final String USERSTORY_DESCRIPTION = "Test description";
    public static final String USERSTORY_OWNER = "Test OWNER";
    public static final Integer USERSTORY_ESTIMATION = 123456;
    public static final Integer USERSTORY_PRIORITY = 1;
    public static final Long USERSTORY_PROJECT_ID = 1L;
    public static final String USERSTORY_STATUS = "Temporary status";
    private static final String USER_STORY_STATUS = "User story status";

    public static final String BUG_DESCRIPTION = "Bug description";
    public static final String BUG_OWNER = "Bug owner";
    public static final Integer BUG_PRIORITY = 1;
    public static final String BUG_STATUS = "Bug status";
    public static final String BUG_TITLE = "Bug title";

    public static final String NEWBUG_TITLE="New bug title";

    @Autowired
    private UserStoryService userStoryService;

    @Autowired
    private BugService bugService;

    private UserStory buildAndSaveUserStory(){
        UserStory userStory = new UserStory();
        userStory.setTitle(USERSTORY_TITLE);
        userStory.setDescription(USERSTORY_DESCRIPTION);
        userStory.setOwner(USERSTORY_OWNER);
        userStory.setEstimation(USERSTORY_ESTIMATION);
        userStory.setPriority(USERSTORY_PRIORITY);
        userStory.setStatus(USERSTORY_STATUS);
        userStory.setProjectId(USERSTORY_PROJECT_ID);

        return userStory;
    }

    @Test
    public void createBugTest(){
        //given

        UserStory savedUserStory = userStoryService.createUserStory(buildAndSaveUserStory());

        Bug bug = new Bug();
        bug.setUserStoryId(savedUserStory.getId());
        bug.setDescription(BUG_DESCRIPTION);
        bug.setOwner(BUG_OWNER);
        bug.setPriority(BUG_PRIORITY);
        bug.setStatus(BUG_STATUS);
        bug.setTitle(BUG_TITLE);

        //when
        Bug savedBug = bugService.createBug(bug);

        //then
        Assert.assertNotNull("There should have been one bug saved in the database", savedBug);
        Assert.assertEquals("User story id should match the user story id from the bug", savedUserStory.getId(), savedBug.getUserStoryId());
        Assert.assertEquals("The bug name should have matched", BUG_TITLE, savedBug.getTitle());
        Assert.assertEquals("The bug description should have matched", BUG_DESCRIPTION, savedBug.getDescription());
        Assert.assertEquals("The bug owner should have matched", BUG_OWNER, savedBug.getOwner());
        Assert.assertEquals("The bug status should have matched", BUG_STATUS, savedBug.getStatus());
        Assert.assertEquals("The bug priority should have matched", BUG_PRIORITY, savedBug.getPriority());

    }

    @Test
    public void getBugTest(){
        //given

        UserStory savedUserStory = userStoryService.createUserStory(buildAndSaveUserStory());

        Bug bug = new Bug();
        bug.setUserStoryId(savedUserStory.getId());
        bug.setDescription(BUG_DESCRIPTION);
        bug.setOwner(BUG_OWNER);
        bug.setPriority(BUG_PRIORITY);
        bug.setStatus(BUG_STATUS);
        bug.setTitle(BUG_TITLE);

        Bug savedBug = bugService.createBug(bug);

        //when

        Bug findBug = bugService.getBug(savedBug.getId());

        //then
        Assert.assertNotNull("There should have been one bug saved in the database", findBug);
        Assert.assertEquals("The bug name should have matched", BUG_TITLE, savedBug.getTitle());
        Assert.assertEquals("The bug description should have matched", BUG_DESCRIPTION, savedBug.getDescription());
        Assert.assertEquals("The bug owner should have matched", BUG_OWNER, savedBug.getOwner());
        Assert.assertEquals("The bug status should have matched", BUG_STATUS, savedBug.getStatus());
        Assert.assertEquals("The bug priority should have matched", BUG_PRIORITY, savedBug.getPriority());

    }

    @Test
    public void deleteBugTest(){
        //given

        UserStory savedUserStory = userStoryService.createUserStory(buildAndSaveUserStory());

        Bug bug = new Bug();
        bug.setUserStoryId(savedUserStory.getId());
        bug.setDescription(BUG_DESCRIPTION);
        bug.setOwner(BUG_OWNER);
        bug.setPriority(BUG_PRIORITY);
        bug.setStatus(BUG_STATUS);
        bug.setTitle(BUG_TITLE);

        Bug savedBug = bugService.createBug(bug);

        //when
        bugService.deleteBug(savedBug.getId());

        //then
        Assert.assertNull("Bug should have been deleted from db", bugService.getBug(savedBug.getId()));

    }

    @Test
    public void updateBugService(){
        //given

        UserStory savedUserStory = userStoryService.createUserStory(buildAndSaveUserStory());

        Bug bug = new Bug();
        bug.setUserStoryId(savedUserStory.getId());
        bug.setDescription(BUG_DESCRIPTION);
        bug.setOwner(BUG_OWNER);
        bug.setPriority(BUG_PRIORITY);
        bug.setStatus(BUG_STATUS);
        bug.setTitle(BUG_TITLE);

        Bug savedBug = bugService.createBug(bug);

        //when
        savedBug.setTitle(NEWBUG_TITLE);
        Bug updatedBug = bugService.updateBug(savedBug);

        //then
        Assert.assertNotNull("There should have been one task in the database", updatedBug);
        Assert.assertEquals("The task title should have matched", NEWBUG_TITLE, updatedBug.getTitle());

    }
}
