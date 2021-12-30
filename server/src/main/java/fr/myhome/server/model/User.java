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
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

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

    @Column(name = "REMEMBER_ME", nullable = false)
    @NotNull
    private Boolean rememberMe = false;

    @Column(name = "REFRESH_TOKEN", nullable = true, unique = true, length = 1024)
    private String refreshToken;

    @Override
    public String toString() {
        return "User [id=" + this.id + ", userName=" + username + "]";
    }

    public void isRefreshTokenMath(final String refreshToken){
        boolean value = false;

        if(refreshToken != null && this.refreshToken != null){
            if(refreshToken.equals(this.refreshToken)){
                value = true;
            }
        }

        if(!value){
            throw new TokenMatchException();
        }
    }
    
}
