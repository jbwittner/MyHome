package fr.myhome.server.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import fr.myhome.server.generated.api.CollectionApi;
import fr.myhome.server.generated.model.CollectionDTO;
import fr.myhome.server.generated.model.CollectionParameter;
import fr.myhome.server.service.CollectionService;

@RestController
public class CollectionController extends BaseRestController implements CollectionApi {

    private CollectionService collectionService;

    @Autowired
    public CollectionController(final CollectionService collectionService){
        super();
        this.collectionService = collectionService;
    }

    @Override
    public ResponseEntity<CollectionDTO> createCollection(@Valid CollectionParameter collectionParameter) {
        CollectionDTO collectionDTO = this.collectionService.createCollection(collectionParameter);
        return new ResponseEntity<>(collectionDTO, HttpStatus.OK);
    }
    
}
