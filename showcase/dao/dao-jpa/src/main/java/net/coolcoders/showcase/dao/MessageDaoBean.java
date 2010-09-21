/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.dao;

import net.coolcoders.showcase.dao.generic.AbstractGenericDao;
import net.coolcoders.showcase.model.Message;

import javax.ejb.Stateless;

/**
 *
 * @author andreas
 */
@Stateless
public class MessageDaoBean extends AbstractGenericDao<Message, Long> {

    public MessageDaoBean() {
        super(Message.class);
    }

}
