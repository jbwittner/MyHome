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
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import fr.myhome.server.exception.TokenMatchException;
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
    @NotNull
    private String username;

    @Column(name = "FIRST_NAME", nullable = false)
    @NotNull
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    @NotNull
    private String lastName;

    @Column(name = "EMAIL", nullable = false, unique = true)
    @NotNull
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    @NotNull
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "USER_ROLES", joinColumns = @JoinColumn(name = "USER_ID"))
    @Column(name = "ROLES")
    private List<Role> roles;

    @Column(name = "IS_LOCKED", nullable = false)
    @NotNull
    private Boolean isLocked = false;

    @Column(name = "IS_ENABLED", nullable = false)
    @NotNull
    private Boolean isEnabled = true;

    @Column(name = "REMEMBER_ME_TOKEN", nullable = true, unique = true, length = 1024)
    private String rememberMeToken;

    public User(final String email, final String firstName, final String lastName, final String userName, final String password){
        this.email = email;
        this.firstName = StringUtils.capitalize(firstName.toLowerCase());
        this.lastName = lastName.toUpperCase();
        this.username = userName.toLowerCase();
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + this.id + ", userName=" + username + "]";
    }

    public void isRememberMeTokenMath(final String rememberMeToken){
        boolean value = false;

        if(rememberMeToken != null && this.rememberMeToken != null){
            if(rememberMeToken.equals(this.rememberMeToken)){
                value = true;
            }
        }

        if(!value){
            throw new TokenMatchException();
        }
    }
    
}
