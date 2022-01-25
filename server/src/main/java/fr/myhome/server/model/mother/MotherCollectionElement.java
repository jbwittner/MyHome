package fr.myhome.server.model.mother;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import fr.myhome.server.model.Collection;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public class MotherCollectionElement extends MotherPersistent {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COLLECTION_ID")
    public Collection collection;
    
}
