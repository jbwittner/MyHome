package fr.myhome.server.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Table;

import fr.myhome.server.model.enumerate.Role;
import fr.myhome.server.model.mother.MotherPersistent;

import javax.persistence.JoinColumn;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "USERS")
@Data
public class User extends MotherPersistent {

    @Column(name = "USER_NAME", nullable = false, unique = true)
    private String userName;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "USER_ROLES", joinColumns = @JoinColumn(name = "USER_ID"))
    @Column(name = "ROLES")
    private List<Role> roles;

    @Override
    public String toString() {
        return "User [id=" + this.id + ", userName=" + userName + "]";
    }
    
}
