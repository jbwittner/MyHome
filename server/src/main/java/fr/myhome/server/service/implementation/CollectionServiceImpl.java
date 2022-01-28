package fr.myhome.server.service.implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.myhome.server.dto.CollectionDTOBuilder;
import fr.myhome.server.dto.CollectionSumarryDTOBuilder;
import fr.myhome.server.exception.CollectionNotExistException;
import fr.myhome.server.exception.UserNotExistException;
import fr.myhome.server.generated.model.CollectionDTO;
import fr.myhome.server.generated.model.CollectionParameter;
import fr.myhome.server.generated.model.CollectionSumarryDTO;
import fr.myhome.server.model.Collection;
import fr.myhome.server.model.CollectionPermission;
import fr.myhome.server.model.User;
import fr.myhome.server.model.enumerate.CollectionPermissionEnum;
import fr.myhome.server.repository.CollectionPermissionRepository;
import fr.myhome.server.repository.CollectionRepository;
import fr.myhome.server.repository.UserRepository;
import fr.myhome.server.service.CollectionService;
import fr.myhome.server.tools.AuthenticationFacade;

@Service
@Transactional
public class CollectionServiceImpl implements CollectionService {

    private static final CollectionDTOBuilder COLLECTION_DTO_BUILDER = new CollectionDTOBuilder();
    private static final CollectionSumarryDTOBuilder COLLECTION_SUMARRY_DTO_BUILDER = new CollectionSumarryDTOBuilder();

    private final AuthenticationFacade authenticationFacade;
    private final UserRepository userRepository;
    private final CollectionRepository collectionRepository;
    private final CollectionPermissionRepository collectionPermissionRepository;

    @Autowired
    public CollectionServiceImpl(final AuthenticationFacade authenticationFacade,
        final UserRepository userRepository,
        final CollectionRepository collectionRepository,
        final CollectionPermissionRepository collectionPermissionRepository){
            this.authenticationFacade = authenticationFacade;
            this.userRepository = userRepository;
            this.collectionRepository = collectionRepository;
            this.collectionPermissionRepository = collectionPermissionRepository;
        }

    @Override
    public CollectionDTO createCollection(final CollectionParameter collectionParameter) {

        final Collection collection = new Collection(collectionParameter.getCollectionName());
        this.collectionRepository.save(collection);

        final User currentUser = this.authenticationFacade.getCurrentUser();

        final CollectionPermission collectionPermission = this.collectionPermissionRepository.save(new CollectionPermission(currentUser, CollectionPermissionEnum.ADMIN, collection));
        collection.getPermissions().add(collectionPermission);

        if(collectionParameter.getPermissions() != null){
            collectionParameter.getPermissions().forEach(permissionParameter -> {
                final String userName = permissionParameter.getUserName();
                final User user = this.userRepository.findByUsername(userName).orElseThrow(() -> new UserNotExistException(userName));
                final CollectionPermissionEnum userCollectionPermissionEnum = CollectionPermissionEnum.fromValue(permissionParameter.getPermission().toString());
                CollectionPermission userCollectionPermission = new CollectionPermission(user, userCollectionPermissionEnum, collection);
                userCollectionPermission = this.collectionPermissionRepository.save(userCollectionPermission);
                collection.getPermissions().add(userCollectionPermission);
            });
        }

        this.collectionRepository.save(collection);

        System.out.println(collection);
        
        return COLLECTION_DTO_BUILDER.transform(collection);
    }

    public List<CollectionSumarryDTO> getCollections() {
        final User user = this.authenticationFacade.getCurrentUser();
        final List<CollectionPermission> collectionPermissions = user.getCollectionPermissions();
        return COLLECTION_SUMARRY_DTO_BUILDER.transformAll(collectionPermissions);
    }

    @Override
    public CollectionDTO getCollection(Integer collectionId) {
        final Collection collection = this.collectionRepository.findById(collectionId).orElseThrow(() -> new CollectionNotExistException(collectionId));
        final User user = this.authenticationFacade.getCurrentUser();
        collection.checkCanRead(user);
        return COLLECTION_DTO_BUILDER.transform(collection);
    }
    
}
