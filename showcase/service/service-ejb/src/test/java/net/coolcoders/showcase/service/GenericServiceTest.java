package net.coolcoders.showcase.service;

import net.coolcoders.showcase.dao.generic.QueryFetch;
import net.coolcoders.showcase.dao.generic.QueryParameter;
import net.coolcoders.showcase.model.Message;
import net.coolcoders.showcase.model.User;
import net.coolcoders.showcase.model.User_;
import net.coolcoders.showcase.model.builder.MessageBuilder;
import net.coolcoders.showcase.model.builder.UserBuilder;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: andreas
 * Date: 29.09.2010
 * Time: 21:54:01
 * To change this template use File | Settings | File Templates.
 */
public class GenericServiceTest {

    @Test
    @Ignore("dosen't work yet")
    public void bla() throws NamingException {
        EJBContainer ejbC = EJBContainer.createEJBContainer();
        Context ctx = ejbC.getContext();
        GenericService service = (GenericService) ctx.lookup("java:global/classes/GenericService");
        Assert.assertNotNull(service);

        User user = new UserBuilder().withUsername("username").withPassword("password").withEmail("bla@bla.com").build();
        Message message = new MessageBuilder().withAuthor(user).withCreated(new Date()).withContent("content").build();
        user.getMessages().add(message);
        service.persist(user);

        service.flushAndClear();

        List<User> list = service.get(User.class, QueryParameter.with(User_.username, "username"), null, QueryFetch.withInnerJoin(User_.messages));
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(1, list.get(0).getMessages().size());
    }
}
