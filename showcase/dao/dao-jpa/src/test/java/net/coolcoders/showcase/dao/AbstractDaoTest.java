package net.coolcoders.showcase.dao;

import net.coolcoders.showcase.dao.generic.GenericDao;
import net.coolcoders.showcase.dao.init.DbInitBean;
import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Field;

/**
 * Created by IntelliJ IDEA.
 * User: andreas
 * Date: 02.10.2010
 * Time: 17:34:54
 * To change this template use File | Settings | File Templates.
 */
public class AbstractDaoTest {

    protected EntityManager em;
    protected GenericDao genericDao;

    protected boolean rollback = true;

    @Before
    public void before() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShowcasePU");
        em = emf.createEntityManager();
        genericDao = inject(new GenericDao(), "em", em);
        try {
            DbInitBean initBean = new DbInitBean();
            Field daoField = initBean.getClass().getDeclaredField("genericDao");
            daoField.setAccessible(true);
            daoField.set(initBean, genericDao);

            beginTx();
            initBean.initDb();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    protected <T> T inject(T object, String fieldName, Object value) {
        Class clazz = object.getClass();
        Field field = null;
        while(field == null && !clazz.equals(Object.class)) {
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        try {
            field.setAccessible(true);
            field.set(object, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return object;
    }

    @After
    public void after() {
        if(rollback) {
            rollbackTx();
        } else {
            commitTx();
        }
    }

    private void commitTx() {
        em.getTransaction().commit();
    }

    private void beginTx() {
        em.getTransaction().begin();
    }

    private void rollbackTx() {
        em.getTransaction().rollback();
    }

}
