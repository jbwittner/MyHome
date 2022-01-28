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
import fr.myhome.server.model.mother.MotherCollectionElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "COLLECTION_ELEMENT_PERMISSIONS")
@Data
@NoArgsConstructor
public class CollectionPermission extends MotherCollectionElement {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    @NotNull
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "PERMISSION", nullable = false)
    @NotNull
    private CollectionPermissionEnum permission;

    public CollectionPermission(final User user, final CollectionPermissionEnum permission, final Collection collection){
        this.collection = collection;
        this.user = user;
        this.permission = permission;
    }
    
}
