package net.coolcoders.showcase.panel

import org.apache.wicket.markup.html.panel.FeedbackPanel

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class ShowcaseFeedbackPanel extends FeedbackPanel {
  public ShowcaseFeedbackPanel(String id) {
    super(id)
  }

  def boolean isVisible() {
    return super.anyMessage();
  }
}
