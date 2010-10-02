package net.coolcoders.showcase.model.builder;

import net.coolcoders.showcase.model.Category;
import net.coolcoders.showcase.model.Message;
import net.coolcoders.showcase.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Peter Schneider-Manzell
 */
public class MessageBuilderTest {

    @Test
    public void testEmptyBuild() {
        MessageBuilder messageBuilderInstance = new MessageBuilder();
        Message messageInstance = messageBuilderInstance.build();
        Assert.assertNotNull(messageInstance);
        Assert.assertNull(messageInstance.getContent());
        Assert.assertNotNull(messageInstance.getCreated());
        Assert.assertNull(messageInstance.getAuthor());
    }

    @Test
    public void testBuild() {
        MessageBuilder messageBuilderInstance = new MessageBuilder();
        User author = new User();
        String content = "Content";
        Date created = new Date();
        Message messageInstance = messageBuilderInstance.withAuthor(author).withContent(content).withCreated(created).build();
        Assert.assertNotNull(messageInstance);
        Assert.assertEquals(author,messageInstance.getAuthor());
        Assert.assertEquals(content,messageInstance.getContent());
        Assert.assertEquals(created,messageInstance.getCreated());
    }
}
