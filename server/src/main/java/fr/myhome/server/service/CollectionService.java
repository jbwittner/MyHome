package fr.myhome.server.service;

import java.util.List;

import fr.myhome.server.generated.model.CollectionDTO;
import fr.myhome.server.generated.model.CollectionParameter;
import fr.myhome.server.generated.model.CollectionSumarryDTO;

public interface CollectionService {

    CollectionDTO createCollection(final CollectionParameter collectionParameter);
    
    List<CollectionSumarryDTO> getCollections();

    CollectionDTO getCollection(Integer collectionId);
}
