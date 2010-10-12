package net.coolcoders.showcase.dao;

import net.coolcoders.showcase.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: andreas
 * Date: 29.09.2010
 * Time: 21:54:01
 * To change this template use File | Settings | File Templates.
 */
public class GenericDaoTest extends AbstractDaoTest {

    @Test
    public void testListAll() {
        List<User> list = genericDao.listAll(User.class);
        Assert.assertEquals(4, list.size());
    }


}
