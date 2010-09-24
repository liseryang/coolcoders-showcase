/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coolcoders.showcase.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 *
 * @author andreas
 */
@Entity
public class Message extends AbstractBaseEntity {

    @NotNull
    @Size(max=140)
    private String content;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created = new Date();

    @ManyToOne
    private User author;

    public Message() {
    }

    public Message(User author, Date created, String content) {
        this.author = author;
        this.created = created;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
