package net.coolcoders.showcase.model.builder;

import net.coolcoders.showcase.model.Category;
import net.coolcoders.showcase.model.Gender;
import net.coolcoders.showcase.model.Message;
import net.coolcoders.showcase.model.User;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: andreas
 * Date: 27.09.2010
 * Time: 14:08:18
 * To change this template use File | Settings | File Templates.
 */
public class UserBuilder {
    private Map<String, Object> values = new HashMap<String, Object>();

    public UserBuilder withUsername(String username) {
        values.put("username", username);
        return this;
    }

    public UserBuilder withFullname(String fullname) {
        values.put("fullname", fullname);
        return this;
    }

    public UserBuilder withPassword(String password) {
        values.put("password", password);
        return this;
    }

    public UserBuilder withConfirmPassword(String confirmPassword) {
        values.put("confirmPassword", confirmPassword);
        return this;
    }

    public UserBuilder withEmail(String email) {
        values.put("email", email);
        return this;
    }

    public UserBuilder withGender(Gender gender) {
        values.put("gender", gender);
        return this;
    }

    public UserBuilder withBirthday(Date birthday) {
        values.put("birthday", birthday);
        return this;
    }

    public UserBuilder withMessages(Set<Message> messages) {
        values.put("messages", messages);
        return this;
    }

    public UserBuilder withCategories(Set<Category> categories) {
        values.put("categories", categories);
        return this;
    }

    public UserBuilder withFollowing(Set<User> following) {
        values.put("following", following);
        return this;
    }

    public UserBuilder withFollowedBy(Set<User> followedBy) {
        values.put("followedBy", followedBy);
        return this;
    }

    public User build() {
        User user = new User();
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            try {
                Field field = user.getClass().getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(user, entry.getValue());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return user;
    }
}
