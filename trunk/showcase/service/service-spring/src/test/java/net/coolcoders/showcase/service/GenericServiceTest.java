package net.coolcoders.showcase.service;

import junit.framework.Assert;
import net.coolcoders.showcase.dao.init.DbInitBean;
import net.coolcoders.showcase.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *         Date: 13.10.2010
 *         Time: 00:19:22
 */
@ContextConfiguration({"classpath:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class GenericServiceTest {

    @Resource
    GenericService genericService;

    @Resource
    DbInitBean dbInitBean;

    @Before
    public void before() {
        dbInitBean.initDb();
    }

    @Test
    public void testListAll() throws ParseException {
        List<User> users = genericService.listAll(User.class);
        Assert.assertEquals(4, users.size());
    }

}
