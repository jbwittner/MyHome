package fr.myhome.server.dto;

import fr.myhome.server.generated.model.CollectionPermissionDTO;
import fr.myhome.server.generated.model.CollectionPermissionEnum;
import fr.myhome.server.generated.model.UserDTO;
import fr.myhome.server.model.CollectionPermission;

public class CollectionPermissionDTOBuilder extends Transformer<CollectionPermission, CollectionPermissionDTO> {

    private static final UserDTOBuilder USER_DTO_BUILDER = new UserDTOBuilder();

    @Override
    public CollectionPermissionDTO transform(final CollectionPermission input) {
        final CollectionPermissionDTO collectionPermissionDTO = new CollectionPermissionDTO();
        final UserDTO userDTO = USER_DTO_BUILDER.transform(input.getUser());
        collectionPermissionDTO.setUserDTO(userDTO);
        final CollectionPermissionEnum collectionPermissionEnum = CollectionPermissionEnum.fromValue(input.getPermission().toString());
        collectionPermissionDTO.setPermission(collectionPermissionEnum);
        return collectionPermissionDTO;
    }
    
}
