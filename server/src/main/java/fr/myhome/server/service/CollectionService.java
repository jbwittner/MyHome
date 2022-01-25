package fr.myhome.server.service;

import fr.myhome.server.generated.model.CollectionDTO;
import fr.myhome.server.generated.model.CollectionParameter;

public interface CollectionService {

    CollectionDTO createCollection(final CollectionParameter collectionParameter);
    
}
