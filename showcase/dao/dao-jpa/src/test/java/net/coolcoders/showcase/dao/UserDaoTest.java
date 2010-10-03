package net.coolcoders.showcase.dao;

import junit.framework.Assert;
import net.coolcoders.showcase.dao.generic.QueryParameter;
import net.coolcoders.showcase.model.User;
import net.coolcoders.showcase.model.User_;
import org.junit.Before;
import org.junit.Test;

public class UserDaoTest extends AbstractDaoTest {

    private UserDao userDao;

    @Before
    public void before() {
        super.before();
        userDao = inject(new UserDao(), "em", em);
    }

    @Test
    public void testCountForUser() {
        User andreas = genericDao.find(User.class, QueryParameter.with(User_.username, "anerlich"));
        Long count = userDao.count(andreas.getId());
        Assert.assertEquals(Long.valueOf(1), count);
    }

}
