/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *
 */
@Entity
public class Category extends AbstractBaseEntity {

    @NotNull(message="{net.coolcoders.showcase.Category.name.blank}")
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<User> users = new HashSet<User>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
