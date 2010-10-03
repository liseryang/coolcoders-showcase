package net.coolcoders.showcase.web.primefaces;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * User: <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 * Date: 03.10.2010
 * Time: 17:23:01
 */
@Named
@SessionScoped
public class TweetsSessionBean implements Serializable{

    private int firstPage = 0;

    private int stepSize = 5;

    private Long messageCount;

    public Long getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Long messageCount) {
        this.messageCount = messageCount;
    }

    public int getStepSize() {
        return stepSize;
    }

    public void setStepSize(int stepSize) {
        this.stepSize = stepSize;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void nextPage() {
        firstPage += stepSize;
    }

    public void prevPage() {
        if(firstPage - stepSize <= 0) {
            firstPage = 0;
        } else {
            firstPage -= stepSize;
        }
    }

    public boolean getRenderNext() {
        return messageCount > firstPage + stepSize;
    }

    public boolean getRenderPrev() {
        return firstPage > 0;
    }
}
