package net.coolcoders.showcase.model.builder;

import net.coolcoders.showcase.model.Category;
import net.coolcoders.showcase.model.User;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: andreas
 * Date: 27.09.2010
 * Time: 14:08:41
 * To change this template use File | Settings | File Templates.
 */
public class CategoryBuilder {
    private Map<String, Object> values = new HashMap<String, Object>();

    public CategoryBuilder withName(String name) {
        values.put("name", name);
        return this;
    }

    public CategoryBuilder withUsers(Set<User> users) {
        values.put("users", users);
        return this;
    }

    public Category build() {
        Category category = new Category();
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            try {
                Field field = category.getClass().getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(category, entry.getValue());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return category;
    }
}
