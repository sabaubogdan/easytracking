package xyz.vegaone.easytracking.project;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.vegaone.easytracking.dto.Project;
import xyz.vegaone.easytracking.dto.UserStory;
import xyz.vegaone.easytracking.service.ProjectService;
import xyz.vegaone.easytracking.service.UserStoryService;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProjectServiceTest {

    public static final String USERSTORY_TITLE = "Test title";
    public static final String USERSTORY_DESCRIPTION = "Test description";
    public static final String USERSTORY_OWNER = "Test OWNER";
    public static final Integer USERSTORY_ESTIMATION = 123456;
    public static final Integer USERSTORY_PRIORITY = 1;
    public static final Long USERSTORY_PROJECTID=5L;

    public static final String PROJECT_DESCRIPTION="Project test";
    public static final String PROJECT_NAME="Project name";

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserStoryService userStoryService;

    @Test
    public void createProjectTest(){
        //given
//        UserStory userStory = new UserStory();
//        userStory.setTitle(USERSTORY_TITLE);
//        userStory.setDescription(USERSTORY_DESCRIPTION);
//        userStory.setOwner(USERSTORY_OWNER);
//        userStory.setEstimation(USERSTORY_ESTIMATION);
//        userStory.setPriority(USERSTORY_PRIORITY);
//        userStory.setProjectId(USERSTORY_PROJECTID);
//
//        UserStory savedUserStory = userStoryService.createUserStory(userStory);

        Project project = new Project();
        project.setDescription(PROJECT_DESCRIPTION);
        project.setName(PROJECT_NAME);
 //       project.setUserStories(savedUserStory.getProjectId());

        //when

        Project savedProject = projectService.createProject(project);

        //then
        Assert.assertNotNull("There should have been one project saved in the database", savedProject);
        Assert.assertEquals("The project name should have matched", PROJECT_NAME, savedProject.getName());
        Assert.assertEquals("The project description should have matched", PROJECT_DESCRIPTION, savedProject.getDescription());

    }

}
