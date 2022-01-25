/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package fr.myhome.server.generated.api;

import fr.myhome.server.generated.model.CollectionDTO;
import fr.myhome.server.generated.model.CollectionParameter;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Validated
@Api(value = "collection", description = "the collection API")
public interface CollectionApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /collection : Create Collection
     *
     * @param collectionParameter Object that need to create a collection (optional)
     * @return successful operation (status code 200)
     */
    @ApiOperation(value = "Create Collection", nickname = "createCollection", notes = "", response = CollectionDTO.class, tags={ "collection", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = CollectionDTO.class) })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/collection",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<CollectionDTO> createCollection(@ApiParam(value = "Object that need to create a collection") @Valid @RequestBody(required = false) CollectionParameter collectionParameter) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"permissions\" : [ { \"userDTO\" : { \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"roles\" : [ null, null ], \"email\" : \"email\", \"username\" : \"username\" } }, { \"userDTO\" : { \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"roles\" : [ null, null ], \"email\" : \"email\", \"username\" : \"username\" } } ], \"collectionName\" : \"collectionName\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
