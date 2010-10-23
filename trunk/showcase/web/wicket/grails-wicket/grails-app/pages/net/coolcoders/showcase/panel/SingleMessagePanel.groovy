package net.coolcoders.showcase.panel

import net.coolcoders.showcase.Message
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.panel.Panel

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class SingleMessagePanel extends Panel {

  public SingleMessagePanel(String id, Message message) {
    super(id)
    add(new Label("messageDate", message.created.toString()))
    add(new Label("messageUser", message.creator.fullname))
    add(new Label("messageContent", message.content))
  }
}
