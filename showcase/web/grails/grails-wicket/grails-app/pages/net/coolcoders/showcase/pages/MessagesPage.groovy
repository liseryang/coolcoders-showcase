package net.coolcoders.showcase.pages

import net.coolcoders.showcase.Message
import net.coolcoders.showcase.ShowcaseSession
import net.coolcoders.showcase.panel.SingleMessagePanel
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.RequiredTextField
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.markup.html.list.ListView
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class MessagesPage extends BasePage {

  @SpringBean(name = "messageService")
  def transient messageService
  private final String userId
  private String newMessage
  private List<Message> messages;

  public MessagesPage(String userId) {
    this.userId = userId
    messages = messageService.findAllMessagesOfFollowing(userId, 0, 10)
    addTheForm();
    addTheList();
  }

  public String getNewMessage() {
    return newMessage
  }

  public void setNewMessage(String newMessage) {
    this.newMessage = newMessage
  }

  private void addTheForm() {
    PropertyModel<String> formModel = new PropertyModel<String>(this, "newMessage")
    Form<String> form = new Form<String>("createMessageForm", formModel) {
      protected void onSubmit() {
        println "onSubmit ${getNewMessage()}"
      }
    }
    form.add(new RequiredTextField<String>("newMessage", formModel))
    add(form)
  }

  private void addTheList(String userId) {

    ListView<Message> messagesList = new ListView<Message>("messageList", messages) {
      protected void populateItem(ListItem<Message> item) {
        Message theMessage = item.getModelObject()
        item.add(new SingleMessagePanel("singleMessage", theMessage));
      }
    }
    add(messagesList);
  }
}
