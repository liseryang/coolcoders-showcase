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
import java.util.UUID;

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
            User bambo = new User("Andreas Baumgartner", "abaumgartner", "test123", "andreas@bambo.it", Gender.MALE, sdf.parse("15.09.1979"));
            users.add(bambo);
            User peter = new User("Peter Schneider-Manzell", "pschneider-manzell", "test123", "peter@schneider-manzell.de", Gender.MALE, null);
            users.add(peter);
            User andreas = new User("Andreas Nerlich", "anerlich", "test123", "andreas.nerlich@gmail.com", Gender.MALE, null);
            users.add(andreas);
            User josip = new User("Josip Mihelko", "jmihelko", "test123", "josip.mihelko@googlemail.com", Gender.MALE, null);
            users.add(josip);

            bambo.getFollowing().add(peter);
            josip.getFollowing().add(andreas);
            andreas.getFollowing().add(bambo);

            createDummyMessages(bambo, 100);
            createDummyMessages(josip, 50);
            createDummyMessages(andreas, 10);

            userServiceBean.saveAll(users);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        userServiceBean.saveAll(users);
    }

    private void createDummyMessages(User u, int count) {
        for( int i = 0; i < count; i++) {
            Message m = new Message(u, new Date(), UUID.randomUUID().toString());
            u.getMessages().add(m);
        }
    }


}
