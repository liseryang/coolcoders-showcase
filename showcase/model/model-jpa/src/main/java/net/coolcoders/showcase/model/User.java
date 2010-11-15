/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.model;

import net.coolcoders.showcase.model.constraint.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *
 */
@Entity
@Table(name="USER_TABLE")
public class User extends AbstractBaseEntity {

    @NotNull(message = "{net.coolcoders.showcase.User.username.blank}")
    @Size(max=32)
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = true)
    private String fullname;

    @NotNull(message = "{net.coolcoders.showcase.User.password.blank}")
    @Pattern(regexp="^.*(?=.{6,})(?=.*\\d)(?=.*[a-zA-Z]).*$", message="{net.coolcoders.showcase.User.password.matches.invalid}")
    @Column(nullable = false)
    private String password;

    private transient String confirmPassword;

    @NotNull(message = "{net.coolcoders.showcase.User.email.blank}")
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    private Gender gender = Gender.MALE;
    
    @Temporal(value = javax.persistence.TemporalType.DATE)
    private Date birthday;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Message> messages = new HashSet<Message>();

    @ManyToMany()
    private Set<Category> categories = new HashSet<Category>();

    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> following = new HashSet<User>();

    public User() {
    }

    public User(String fullname, String username, String password, String email, Gender gender, Date birthday) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    @Transient
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Transient
    public int getMessageCount() {
        return getMessages().size();
    }

}
