/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author andreas
 */
@Entity
@Table(name="USER_TABLE")
public class User extends AbstractBaseEntity {

    @NotNull
    @Size(max=32)
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    @Column(nullable = true)
    private String fullname;

    @NotNull
    @Pattern(regexp="^.*(?=.{6,})(?=.*\\d)(?=.*[a-zA-Z]).*$")
    @Column(nullable = false)
    private String password;

    private transient String confirmPassword;

    @NotNull
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    private Gender gender = Gender.MALE;
    
    @Temporal(value = javax.persistence.TemporalType.DATE)
    private Date birthday;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Message> messages = new HashSet<Message>();

    @ManyToMany
    private Set<Category> categories = new HashSet<Category>();

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

    @Transient
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
