package fr.myhome.server.service.implementation;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import fr.myhome.server.dto.CollectionDTOBuilder;
import fr.myhome.server.generated.model.CollectionDTO;
import fr.myhome.server.generated.model.CollectionParameter;
import fr.myhome.server.model.Collection;
import fr.myhome.server.repository.CollectionPermissionRepository;
import fr.myhome.server.repository.CollectionRepository;
import fr.myhome.server.repository.UserRepository;
import fr.myhome.server.service.CollectionService;

@Service
@Transactional
public class CollectionServiceImpl implements CollectionService {

    private static final CollectionDTOBuilder COLLECTION_DTO_BUILDER = new CollectionDTOBuilder();

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final CollectionRepository collectionRepository;
    private final CollectionPermissionRepository collectionPermissionRepository;

    @Autowired
    public CollectionServiceImpl(final AuthenticationManager authenticationManager,
        final UserRepository userRepository,
        final CollectionRepository collectionRepository,
        final CollectionPermissionRepository collectionPermissionRepository){
            this.authenticationManager = authenticationManager;
            this.userRepository = userRepository;
            this.collectionRepository = collectionRepository;
            this.collectionPermissionRepository = collectionPermissionRepository;
        }

    @Override
    public CollectionDTO createCollection(final CollectionParameter collectionParameter) {
        Collection collection = new C
        return null;
    }
    
}
