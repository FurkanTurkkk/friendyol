package ecommercemicroservice.auth_service.converter;

import ecommercemicroservice.auth_service.model.User;
import org.example.UserDto;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {

    public UserDto convert(User user){
        return new UserDto(
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
    }
}
