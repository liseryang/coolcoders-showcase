package net.coolcoders.showcase.model;

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
public class MessageTest extends AbstractBeanValidationTest<Message> {

    protected Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Assert.assertNotNull("No validator created!", validator);
    }

    @Test
    public void testConstructor() {
        User author = new User();
        author.setUsername("username");
        Date created = new Date();
        String content = "Content";

        Message messageInstance = new Message(author,created,content);
        Assert.assertEquals(author,messageInstance.getAuthor());
        Assert.assertEquals(created,messageInstance.getCreated());
        Assert.assertEquals(content,messageInstance.getContent());
    }

    @Test
    public void testSetterAndGetter() {
        User author = new User();
        author.setUsername("username");
        Date created = new Date();
        String content = "Content";

        Message messageInstance = new Message();
        messageInstance.setAuthor(author);
        messageInstance.setCreated(created);
        messageInstance.setContent(content);

        Assert.assertEquals(author,messageInstance.getAuthor());
        Assert.assertEquals(created,messageInstance.getCreated());
        Assert.assertEquals(content,messageInstance.getContent());
    }

    @Test
    public void testContentNotNullConstraint(){
        Message messageInstance = new Message();
        Set<ConstraintViolation<Message>> violations = validator.validate(messageInstance);
        testInvalidAttribute("content", messageInstance.getContent(),"{net.coolcoders.showcase.Message.content.blank}", violations);
        messageInstance.setContent("Content");
        violations = validator.validate(messageInstance);
        testValidAttribute("content",violations);
    }

    @Test
    public void testContentMaxSizeConstraint(){
        Message messageInstance = new Message();
        StringBuffer contentBuilder = new StringBuffer();
        for(int i = 1; i<=140;i++){
          contentBuilder.append("A");
        }
        messageInstance.setContent(contentBuilder.toString());
        Set<ConstraintViolation<Message>> violations = validator.validate(messageInstance);
        testValidAttribute("content", violations);

        contentBuilder.append("A");
        messageInstance.setContent(contentBuilder.toString());
        violations = validator.validate(messageInstance);
        testInvalidAttribute("content", messageInstance.getContent(),"{net.coolcoders.showcase.Message.content.maxSize.exceeded}", violations);
    }


    @Test
    public void testCreatorNotNullConstraint(){
        Message messageInstance = new Message();
        Set<ConstraintViolation<Message>> violations = validator.validate(messageInstance);
        testInvalidAttribute("author", messageInstance.getContent(),"{net.coolcoders.showcase.Message.author.blank}", violations);
        messageInstance.setAuthor(new User());
        violations = validator.validate(messageInstance);
        testValidAttribute("author",violations);
    }

    @Test
    public void testCreatedNotNullConstraint(){
        Message messageInstance = new Message();
        messageInstance.setCreated(null);
        Set<ConstraintViolation<Message>> violations = validator.validate(messageInstance);
        testInvalidAttribute("created", messageInstance.getContent(),"{net.coolcoders.showcase.Message.created.blank}", violations);
        messageInstance.setCreated(new Date());
        violations = validator.validate(messageInstance);
        testValidAttribute("created",violations);
    }

}
