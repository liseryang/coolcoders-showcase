package net.coolcoders.showcase.pages

import net.coolcoders.showcase.Message
import net.coolcoders.showcase.ShowcaseSession
import net.coolcoders.showcase.panel.SingleMessagePanel
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.TextArea
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator
import org.apache.wicket.markup.repeater.Item
import org.apache.wicket.markup.repeater.data.DataView
import org.apache.wicket.model.IModel
import org.apache.wicket.model.Model
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

  public MessagesPage() {
    this.userId = ShowcaseSession.get().getUserId()
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
        addMessage();
      }
    }
    form.add(
            new TextArea<String>("newMessage", formModel).setRequired(true)
    )
    add(form)
  }

  private void addMessage() {
    messageService.addMessage(userId, newMessage);
    newMessage = ""
  }

  private void addTheList(String userId) {
    DataView<Message> messagesList = new DataView<Message>("messageList", new MessagesProvider(), 10) {
      protected void populateItem(Item<Message> item) {
        Message theMessage = item.getModelObject()
        item.add(new SingleMessagePanel("singleMessage", theMessage));
      }
    }
    add(messagesList);
    add(new PagingNavigator("paginator", messagesList))
  }

  class MessagesProvider extends SortableDataProvider<Message> {
    Iterator<? extends Message> iterator(int first, int count) {
      return messageService.findAllMessagesOfFollowing(userId, first, count).iterator();
    }

    int size() {
      return messageService.numberOfAllMessagesOfFollowing(userId);
    }

    IModel<Message> model(Message t) {
      return new Model(Message.get(t.id));
    }

  }
}
