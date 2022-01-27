package fr.myhome.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.myhome.server.model.enumerate.CollectionPermissionEnum;
import fr.myhome.server.model.mother.MotherPersistent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "COLLECTION_ELEMENT_PERMISSIONS")
@Data
public class CollectionPermission extends MotherPersistent {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COLLECTION_ID", nullable = false)
    protected Collection collection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    @NotNull
    protected User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "PERMISSION", nullable = false)
    @NotNull
    private CollectionPermissionEnum permission;

    public CollectionPermission(final User user, final CollectionPermissionEnum permission){
        this.user = user;
        this.permission = permission;
    }
    
}
