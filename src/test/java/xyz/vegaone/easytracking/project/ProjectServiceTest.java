package xyz.vegaone.easytracking.project;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.vegaone.easytracking.dto.Project;
import xyz.vegaone.easytracking.service.ProjectService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProjectServiceTest {

    public static final String PROJECT_DESCRIPTION="Project test";
    public static final String PROJECT_NAME="Project name";
    public static final String PROJECT_NEW_NAME="Project new name";

    @Autowired
    private ProjectService projectService;

    @Test
    public void createProjectTest(){
        //given

        Project project = new Project();
        project.setDescription(PROJECT_DESCRIPTION);
        project.setName(PROJECT_NAME);
        //when

        Project savedProject = projectService.createProject(project);

        //then
        Assert.assertNotNull("There should have been one project saved in the database", savedProject);
        Assert.assertEquals("The project name should have matched", PROJECT_NAME, savedProject.getName());
        Assert.assertEquals("The project description should have matched", PROJECT_DESCRIPTION, savedProject.getDescription());

    }

    @Test
    public void getProject(){
        //given

        Project project = new Project();
        project.setDescription(PROJECT_DESCRIPTION);
        project.setName(PROJECT_NAME);

        Project savedProject = projectService.createProject(project);

        //when
        Project findProject = projectService.getProject(savedProject.getId());

        //then
        Assert.assertNotNull("There should have been one project in the database", findProject);
        Assert.assertEquals("The project name should have matched", PROJECT_NAME, findProject.getName());
        Assert.assertEquals("The project description should have matched", PROJECT_DESCRIPTION, findProject.getDescription());

    }

    @Test
    public void deleteTask(){
        //given
        Project project = new Project();
        project.setDescription(PROJECT_DESCRIPTION);
        project.setName(PROJECT_NAME);

        Project savedProject = projectService.createProject(project);

        //when
        projectService.deleteProject(savedProject.getId());
        //then
        Assert.assertNull("Project should have been deleted from db", projectService.getProject(savedProject.getId()));

    }

    @Test
    public void updateProjectServiceTest(){
        //given
        Project project = new Project();
        project.setDescription(PROJECT_DESCRIPTION);
        project.setName(PROJECT_NAME);

        Project savedProject = projectService.createProject(project);
        //when

        savedProject.setName(PROJECT_NEW_NAME);
        Project updatedProject = projectService.updateProject(savedProject);
        //then
        Assert.assertNotNull("There should have been one task in the database", updatedProject);
        Assert.assertEquals("The task title should have matched", PROJECT_NEW_NAME, updatedProject.getName());
    }

}
