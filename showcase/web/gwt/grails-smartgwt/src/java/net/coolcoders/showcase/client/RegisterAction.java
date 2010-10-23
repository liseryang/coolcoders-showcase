package net.coolcoders.showcase.client;

import grails.plugins.gwt.shared.Action;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class RegisterAction implements Action<RegisterResponse> {
    private String username;
    private String password;
    private String passwordRepetition;
    private String fullname;
    private String email;
    private String gender;
    private Date birthday;
    private Set<String> categoryIds = new HashSet<String>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepetition() {
        return passwordRepetition;
    }

    public void setPasswordRepetition(String passwordRepetition) {
        this.passwordRepetition = passwordRepetition;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Set<String> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Set<String> categoryIds) {
        this.categoryIds = categoryIds;
    }

    @Override
    public String toString() {
        return "RegisterAction{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordRepetition='" + passwordRepetition + '\'' +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", categoryIds=" + categoryIds +
                '}';
    }
}
