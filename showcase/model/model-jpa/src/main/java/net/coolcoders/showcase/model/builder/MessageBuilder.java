package net.coolcoders.showcase.model.builder;

import net.coolcoders.showcase.model.Message;
import net.coolcoders.showcase.model.User;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: andreas
 * Date: 27.09.2010
 * Time: 13:55:45
 * To change this template use File | Settings | File Templates.
 */
public class MessageBuilder {
    private Map<String, Object> values = new HashMap<String, Object>();

    public MessageBuilder withContent(String content) {
        values.put("content", content);
        return this;
    }

    public MessageBuilder withCreated(Date created) {
        values.put("created", created);
        return this;
    }

    public MessageBuilder withAuthor(User author) {
        values.put("author", author);
        return this;
    }

    public Message build() {
        Message message = new Message();
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            try {
                Field field = message.getClass().getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(message, entry.getValue());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return message;
    }
}
