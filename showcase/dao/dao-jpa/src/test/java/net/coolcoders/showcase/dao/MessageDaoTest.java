package net.coolcoders.showcase.dao;

import junit.framework.Assert;
import net.coolcoders.showcase.dao.generic.QueryParameter;
import net.coolcoders.showcase.model.Message;
import net.coolcoders.showcase.model.User;
import net.coolcoders.showcase.model.User_;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MessageDaoTest extends AbstractDaoTest {

    private MessageDao messageDao;

    @Before
    public void before() {
        super.before();
        messageDao = inject(new MessageDao(), "em", em);
    }

    @Test
    public void testListForUserFromTo() {
        User andreas = genericDao.find(User.class, QueryParameter.with(User_.username, "anerlich"));
        List<Message> messages = messageDao.list(andreas.getId(), 0, 5);
        Assert.assertEquals(5, messages.size());
        Assert.assertTrue(messages.get(0).getCreated().getTime() > messages.get(1).getCreated().getTime());
    }


}
