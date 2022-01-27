package fr.myhome.server.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.myhome.server.exception.ElementCollectionNoReadPermission;
import fr.myhome.server.exception.ElementCollectrionNoAdminPermission;
import fr.myhome.server.exception.ElementCollectrionNoWritePermission;
import fr.myhome.server.model.enumerate.CollectionPermissionEnum;
import fr.myhome.server.model.mother.MotherPersistent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "COLLECTION_ELEMENTS")
@Data
public class Collection extends MotherPersistent {

    @Column(name = "COLLECTION_NAME", nullable = false)
    @NotNull
    private String collectionName;

    @OneToMany(fetch = FetchType.EAGER ,mappedBy = "collection")
    private List<CollectionPermission> permissions = new ArrayList<>();

    public Collection(final String collectionName, final List<CollectionPermission> permissions){
        this.collectionName = collectionName;
        this.permissions = permissions;
    }

    private CollectionPermission getPermission(final User user) {
        final CollectionPermission collectionPermission = this.permissions.stream()
            .filter(permission -> user.getId().equals(permission.getUser().getId()))
            .findAny()
            .orElseThrow(() -> new ElementCollectionNoReadPermission());

        return collectionPermission;
    }

    public void checkIsAdmin(final User user) {
        final CollectionPermissionEnum permission = this.getPermission(user).getPermission();
        if(!permission.equals(CollectionPermissionEnum.ADMIN)){
            throw new ElementCollectrionNoAdminPermission();
        }
    }

    public void checkCanWrite(final User user) {
        final CollectionPermissionEnum permission = this.getPermission(user).getPermission();
        if(!(permission.equals(CollectionPermissionEnum.READ_WRITE) || permission.equals(CollectionPermissionEnum.ADMIN))){
            throw new ElementCollectrionNoWritePermission();
        }
    }

    public void checkCanRead(final User user){
        this.getPermission(user);
    }
    
}
