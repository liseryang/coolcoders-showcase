package net.coolcoders.showcase.service;

import net.coolcoders.showcase.dao.generic.GenericDao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Created by IntelliJ IDEA.
 * User: andreas
 * Date: 20.09.2010
 * Time: 11:29:24
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class GenericService extends GenericDao {

}
