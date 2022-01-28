package fr.myhome.server.dto;

import fr.myhome.server.generated.model.UserDTO;
import fr.myhome.server.generated.model.UserRoleEnum;
import fr.myhome.server.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTOBuilder extends Transformer<User, UserDTO> {

    @Override
    public UserDTO transform(final User input){
        final UserDTO userDTO = new UserDTO();
        
        userDTO.setUserId(input.getId());
        userDTO.setUsername(input.getUsername());
        userDTO.setEmail(input.getEmail());
        userDTO.setFirstName(input.getFirstName());
        userDTO.setLastName(input.getLastName());

        final List<UserRoleEnum> rolesEnumList = new ArrayList<>();

        input.getRoles().forEach((role) -> {
            final UserRoleEnum rolesEnum = UserRoleEnum.fromValue(role.toString());
            rolesEnumList.add(rolesEnum);
        });

        userDTO.setRoles(rolesEnumList);
        return userDTO;
    }

}
