package net.coolcoders.showcase.model;

import net.coolcoders.showcase.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

/**
 * Peter Schneider-Manzell
 */

public class UserTest extends AbstractBeanValidationTest<User> {

    protected Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Assert.assertNotNull("No validator created!", validator);
    }

    @Test
    public void testCreateConstructor() {
        String fullname = "Fullname";
        String username = "Username";
        String password = "password";
        String email = "test@test.de";
        Gender gender = Gender.MALE;
        Date birthday = new Date();
        User userInstance = new User(fullname,username,password,email,gender,birthday);
        Assert.assertEquals(fullname,userInstance.getFullname());
        Assert.assertEquals(username,userInstance.getUsername());
        Assert.assertEquals(password,userInstance.getPassword());
        Assert.assertEquals(email,userInstance.getEmail());
        Assert.assertEquals(gender,userInstance.getGender());
        Assert.assertEquals(birthday,userInstance.getBirthday());
    }

    @Test
    public void testSettersAndGetters() {
       String fullname = "Fullname";
        String username = "Username";
        String password = "password";
        String email = "test@test.de";
        Gender gender = Gender.MALE;
        Date birthday = new Date();

        User userInstance = new User();
        userInstance.setFullname(fullname);
        userInstance.setUsername(username);
        userInstance.setPassword(password);
        userInstance.setEmail(email);
        userInstance.setGender(gender);
        userInstance.setBirthday(birthday);
        
        Assert.assertEquals(fullname,userInstance.getFullname());
        Assert.assertEquals(username,userInstance.getUsername());
        Assert.assertEquals(password,userInstance.getPassword());
        Assert.assertEquals(email,userInstance.getEmail());
        Assert.assertEquals(gender,userInstance.getGender());
        Assert.assertEquals(birthday,userInstance.getBirthday());
    }

    @Test
    public void testEmailEmailValidation() {
        testValidEmail("test@test.de");

        testInvalidEmail("test");
        testInvalidEmail("test@test@test");
    }

    @Test
    public void testEmailNotNullValidation() {
        User user = new User();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        testInvalidAttribute("email", user.getEmail(), "{net.coolcoders.showcase.User.email.blank}", violations);
    }

    @Test
    public void testUsernameNotNullValidation() {
        User user = new User();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        testInvalidAttribute("username", user.getUsername(), "{net.coolcoders.showcase.User.username.blank}", violations);
    }

    @Test
    public void testPasswordNotNullValidation() {
        User user = new User();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        testInvalidAttribute("password", user.getUsername(), "{net.coolcoders.showcase.User.password.blank}", violations);
    }

    private void testInvalidEmail(String email) {
        User user = new User();
        user.setEmail(email);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        testInvalidAttribute("email", email, "{net.coolcoders.showcase.AppUser.email.email.invalid}", violations);
    }

    private void testValidEmail(String email) {
        User user = new User();
        user.setEmail(email);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        testValidAttribute("email", violations);
    }
}
