/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package fr.myhome.server.generated.api;

import fr.myhome.server.generated.model.LoginParameter;
import fr.myhome.server.generated.model.UserRegistrationParameter;
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
@Api(value = "authentication", description = "the authentication API")
public interface AuthenticationApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /authentication/connectionTest : Check if the user are connected
     *
     * @return successful operation (status code 200)
     */
    @ApiOperation(value = "Check if the user are connected", nickname = "connectionTest", notes = "", tags={ "security", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation") })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/authentication/connectionTest"
    )
    default ResponseEntity<Void> connectionTest() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /authentication/login : Login
     *
     * @param loginParameter Object that need to be authenticated (optional)
     * @return successful operation (status code 200)
     */
    @ApiOperation(value = "Login", nickname = "login", notes = "", tags={ "security", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation") })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/authentication/login",
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> login(@ApiParam(value = "Object that need to be authenticated") @Valid @RequestBody(required = false) LoginParameter loginParameter) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /authentication/registration : Register a new user account
     *
     * @param userRegistrationParameter Object that needs to register a new user (optional)
     * @return successful operation (status code 200)
     */
    @ApiOperation(value = "Register a new user account", nickname = "registration", notes = "", tags={ "security", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation") })
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/authentication/registration",
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> registration(@ApiParam(value = "Object that needs to register a new user") @Valid @RequestBody(required = false) UserRegistrationParameter userRegistrationParameter) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
