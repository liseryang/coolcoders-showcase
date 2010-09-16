/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.web;

import net.coolcoders.showcase.service.MessageServiceBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author andreas
 */
@Named
@RequestScoped
public class TweetsBean {

    @Inject
    private MessageServiceBean messageServiceBean;

    @Inject
    private SessionBean sessionBean;

    

}
