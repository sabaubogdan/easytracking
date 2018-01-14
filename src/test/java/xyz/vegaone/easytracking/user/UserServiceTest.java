package xyz.vegaone.easytracking.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.vegaone.easytracking.dto.User;
import xyz.vegaone.easytracking.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    public static final String USER_NAME = "easytracking";
    public static final String USER_EMAIL = "JohnnyBravo";
    public static final Long PROJECT_ID = 13L;
    public static final String USER_NEW_NAME = "Scrib";

    @Autowired
    private UserService userService;

    @Test
    public void createUserTest() {
        //given

        //when
        User savedUser = userService.createUser(createNewUser());

        //then
        Assert.assertNotNull("There should have been one user saved in the database", savedUser);
        Assert.assertEquals("The user name should have matched", USER_NAME, savedUser.getName());
        Assert.assertEquals("The user email should have matched", USER_EMAIL, savedUser.getEmail());
    }

    @Test
    public void getUserTest() {
        //given
        User savedUser = userService.createUser(createNewUser());

        //when
        User findUser = userService.getUser(savedUser.getId());

        //then
        Assert.assertNotNull("There should have been one user saved in the database", findUser);
        Assert.assertEquals("The user name should have matched", USER_NAME, findUser.getName());
        Assert.assertEquals("The user email should have matched", USER_EMAIL, findUser.getEmail());
    }

    @Test
    public void updateUserTest() {
        //given
        User savedUser = userService.createUser(createNewUser());

        //when
        savedUser.setName(USER_NEW_NAME);
        User updatedUser = userService.updateUser(savedUser);

        //then
        Assert.assertNotNull("There should have been one user in the database", updatedUser);
        Assert.assertEquals("The user name should have matched", USER_NEW_NAME, updatedUser.getName());
    }

    @Test
    public void deleteUserTest() {
        //given
        User savedUser = userService.createUser(createNewUser());

        //when
        userService.deleteUser(savedUser.getId());

        //then
        Assert.assertNull("User should have been deleted from database", userService.getUser(savedUser.getId()));
    }

    private User createNewUser() {

        User user = new User();
        user.setName(USER_NAME);
        user.setEmail(USER_EMAIL);
        user.setProjectId(PROJECT_ID);

        return user;
    }
}
