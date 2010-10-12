package net.coolcoders.showcase.service;

import net.coolcoders.showcase.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *         Date: 13.10.2010
 *         Time: 00:19:22
 */
@TransactionConfiguration
@ContextConfiguration({"classpath:application-context.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class GenericServiceTest {

    @Resource
    GenericService genericService;

    @Test
    public void bla() {
        genericService.listAll(User.class);
    }

}
