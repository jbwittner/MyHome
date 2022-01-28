package fr.myhome.server.dto;

import fr.myhome.server.generated.model.CollectionPermissionEnum;
import fr.myhome.server.generated.model.CollectionSumarryDTO;
import fr.myhome.server.model.CollectionPermission;

public class CollectionSumarryDTOBuilder extends Transformer<CollectionPermission, CollectionSumarryDTO> {

    @Override
    CollectionSumarryDTO transform(CollectionPermission input) {
        final CollectionSumarryDTO collectionSumarryDTO = new CollectionSumarryDTO();
        collectionSumarryDTO.setCollectionId(input.getId());
        collectionSumarryDTO.setCollectionName(input.getCollection().getCollectionName());
        final CollectionPermissionEnum collectionPermissionEnum = CollectionPermissionEnum.fromValue(input.getPermission().toString());
        collectionSumarryDTO.setPermission(collectionPermissionEnum);
        return collectionSumarryDTO;
    }
    
}
