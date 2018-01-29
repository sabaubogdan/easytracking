package xyz.vegaone.easytracking.project;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.vegaone.easytracking.dto.Project;
import xyz.vegaone.easytracking.dto.User;
import xyz.vegaone.easytracking.service.ProjectService;
import xyz.vegaone.easytracking.service.UserService;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProjectServiceTest {

    private static final String USER_NAME = "TestName";
    private static final String USER_EMAIL = "user@email.com";

    public static final String PROJECT_DESCRIPTION = "Project test";
    public static final String PROJECT_NAME = "Project name";
    public static final String PROJECT_NEW_NAME = "Project new name";

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Test
    public void createProjectTest() {
        //given

        //when
        Project project = buildAndSaveProject();
        project.setUserList(Arrays.asList(buildAndSaveUser()));

        Project savedProject = projectService.createProject(project);

        //then
        Assert.assertNotNull("There should have been one project saved in the database", savedProject);
        Assert.assertEquals("The project name should have matched", PROJECT_NAME, savedProject.getName());
        Assert.assertEquals("The project description should have matched", PROJECT_DESCRIPTION, savedProject.getDescription());

    }

    @Test
    public void getProjectTest() {
        //given
        Project savedProject = projectService.createProject(buildAndSaveProject());

        //when
        Project findProject = projectService.getProject(savedProject.getId());

        //then
        Assert.assertNotNull("There should have been one project in the database", findProject);
        Assert.assertEquals("The project name should have matched", PROJECT_NAME, findProject.getName());
        Assert.assertEquals("The project description should have matched", PROJECT_DESCRIPTION, findProject.getDescription());

    }

    @Test
    public void deleteProjetTest() {
        //given
        Project newProject = buildAndSaveProject();
        newProject.setName("deleteProjectTest");
        Project savedProject = projectService.createProject(newProject);

        //when
        projectService.deleteProject(savedProject.getId());

        //then
        Assert.assertNull("Project should have been deleted from db", projectService.getProject(savedProject.getId()));

    }

    @Test
    public void updateProjectServiceTest() {
        //given
        Project newProject = buildAndSaveProject();
        newProject.setName("updateProjectTest");
        Project savedProject = projectService.createProject(newProject);

        //when
        savedProject.setName(PROJECT_NEW_NAME);
        Project updatedProject = projectService.updateProject(savedProject);

        //then
        Assert.assertNotNull("There should have been one task in the database", updatedProject);
        Assert.assertEquals("The task title should have matched", PROJECT_NEW_NAME, updatedProject.getName());
    }

    @Test
    public void getAllProjectsTest() {
        //given
        Project savedProjectOne = projectService.createProject(buildAndSaveProject());
        Project savedProjectTwo = projectService.createProject(buildAndSaveProject());

        //when
        List<Project> findResult = projectService.getAllProjects();

        //then
        Assert.assertEquals("There should have been two projects in the database", 2, findResult.size());

    }

    private Project buildAndSaveProject() {

        Project project = new Project();
        project.setDescription(PROJECT_DESCRIPTION);
        project.setName(PROJECT_NAME);

        return project;
    }

    private User buildAndSaveUser() {
        User user = new User();

        user.setName(USER_NAME);
        user.setEmail(USER_EMAIL);

        return userService.createUser(user);
    }

}
