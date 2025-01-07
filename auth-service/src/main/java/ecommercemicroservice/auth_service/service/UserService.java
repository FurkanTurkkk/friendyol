package ecommercemicroservice.auth_service.service;

import ecommercemicroservice.auth_service.converter.DtoConverter;
import ecommercemicroservice.auth_service.exception.UserNotFoundException;
import ecommercemicroservice.auth_service.model.User;
import ecommercemicroservice.auth_service.repository.UserRepository;
import ecommercemicroservice.auth_service.request.RequestForCreateUser;
import org.example.UserDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final DtoConverter converter;

    public UserService(UserRepository userRepository, DtoConverter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }

    public UserDto createUser(RequestForCreateUser request) {
        User user=saveUser(new User(request.getEmail(),request.getFirstname(),request.getLastname(),request.getPassword()));
        return converter.convert(user);
    }

    public UserDto findUserByEmail(String email) {
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new UserNotFoundException("User not found by email : "+email));
        return converter.convert(user);
    }

    private User saveUser(User user){
        Optional<User> registeredUser=userRepository.findByEmail(user.getEmail());
        if (registeredUser.isPresent()){
            throw new IllegalArgumentException("User already exist");
        }
        return userRepository.save(user);
    }


}
