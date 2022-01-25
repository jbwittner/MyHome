package fr.myhome.server.dto;

import java.util.List;

import fr.myhome.server.generated.model.CollectionDTO;
import fr.myhome.server.generated.model.CollectionPermissionDTO;
import fr.myhome.server.model.Collection;

public class CollectionDTOBuilder extends Transformer<Collection, CollectionDTO> {

    private static final CollectionPermissionDTOBuilder COLLECTION_PERMISSION_DTO_BUILDER = new CollectionPermissionDTOBuilder();

    @Override
    CollectionDTO transform(final Collection input) {
        CollectionDTO collectionDTO = new CollectionDTO();
        collectionDTO.setCollectionName(input.getCollectionName());
        List<CollectionPermissionDTO> permissionDTOs = COLLECTION_PERMISSION_DTO_BUILDER.transformAll(input.getPermissions());
        collectionDTO.setPermissions(permissionDTOs);
        return collectionDTO;
    }
    
}
