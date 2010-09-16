package net.coolcoders.showcase.service;

import net.coolcoders.showcase.model.Gender;
import net.coolcoders.showcase.model.Message;
import net.coolcoders.showcase.model.User;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: andreas
 * Date: 16.09.2010
 * Time: 17:03:49
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class DbInitBean {

    @Inject
    private UserServiceBean userServiceBean;

    public void initDb() {
        System.out.println("Start init DB");
        List<User> users = new ArrayList<User>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            User u1 = new User("Andreas Baumgartner", "bamboit", "bam-1543", "andreas@bambo.it", Gender.MALE, sdf.parse("15.09.1979"));
            users.add(u1);
            User u2 = new User("Peter Schneider-Manzell", "petersm", "bam-1543", "peter@schneider-manzell.de", Gender.MALE, sdf.parse("15.09.1979"));
            users.add(u2);

            for (User user : users) {
                for (int i = 0; i < 5; i++) {
                    Message message = new Message(user, new Date(), user.getEmail() + " " + i);
                    user.getMessages().add(message);
                }
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        userServiceBean.saveAll(users);
    }

}
