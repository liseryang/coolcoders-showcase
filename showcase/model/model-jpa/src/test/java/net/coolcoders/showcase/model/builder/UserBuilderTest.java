package net.coolcoders.showcase.model.builder;

import net.coolcoders.showcase.model.Gender;
import net.coolcoders.showcase.model.Message;
import net.coolcoders.showcase.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Peter Schneider-Manzell
 */
public class UserBuilderTest {

    @Test
    public void testEmptyBuild() {
        UserBuilder builder = new UserBuilder();
        User userInstance = builder.build();
        Assert.assertNotNull(userInstance);
        Assert.assertNull(userInstance.getFullname());
        Assert.assertNull(userInstance.getUsername());
        Assert.assertNull(userInstance.getEmail());
        Assert.assertNull(userInstance.getBirthday());
        Assert.assertNotNull(userInstance.getGender());
        Assert.assertNotNull(userInstance.getMessages());
        Assert.assertEquals(0,userInstance.getMessages().size());
        Assert.assertNotNull(userInstance.getFollowing());
        Assert.assertEquals(0,userInstance.getFollowing().size());
    }

    @Test
    public void testBuild() {
        UserBuilder builder = new UserBuilder();
        String username = "Username";
        String fullname = "Fullname";
        String email = "test@test.de";
        String password = "password";
        Gender gender = Gender.FEMALE;
        Date birthday = new Date();

        User userInstance = builder
                .withUsername(username)
                .withFullname(fullname)
                .withEmail(email)
                .withPassword(password)
                .withBirthday(birthday)
                .withGender(gender)
                .build();
        
        Assert.assertNotNull(userInstance);
        Assert.assertEquals(username,userInstance.getUsername());
        Assert.assertEquals(fullname,userInstance.getFullname());
        Assert.assertEquals(email,userInstance.getEmail());
        Assert.assertEquals(password,userInstance.getPassword());
        Assert.assertEquals(birthday,userInstance.getBirthday());
        Assert.assertEquals(gender,userInstance.getGender());
    }
}
