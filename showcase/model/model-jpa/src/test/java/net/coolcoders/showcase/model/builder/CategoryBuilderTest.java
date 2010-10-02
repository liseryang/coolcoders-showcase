package net.coolcoders.showcase.model.builder;

import net.coolcoders.showcase.model.Category;
import net.coolcoders.showcase.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Peter Schneider-Manzell
 */
public class CategoryBuilderTest {

    @Test
    public void testEmptyBuild() {
        CategoryBuilder categoryBuilderInstance = new CategoryBuilder();
        Category categoryInstance = categoryBuilderInstance.build();
        Assert.assertNotNull(categoryInstance);
        Assert.assertNull(categoryInstance.getName());
        Assert.assertNotNull(categoryInstance.getUsers());
        Assert.assertEquals(0,categoryInstance.getUsers().size());
    }

    @Test
    public void testBuild() {
        CategoryBuilder categoryBuilderInstance = new CategoryBuilder();
        Set<User> users = new HashSet<User>();
        users.add(new User());
        String name = "Name";
        categoryBuilderInstance.withName(name).withUsers(users);
        Category categoryInstance = categoryBuilderInstance.build();
        Assert.assertNotNull(categoryInstance);
        Assert.assertEquals(name,categoryInstance.getName());
        Assert.assertEquals(users,categoryInstance.getUsers());
    }
}
