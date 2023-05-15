package com.jan1ooo.workshopmongo.service;

import com.jan1ooo.workshopmongo.domain.User;
import com.jan1ooo.workshopmongo.dto.UserDTO;
import com.jan1ooo.workshopmongo.repository.UserRepository;
import com.jan1ooo.workshopmongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String id){
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User user){
        return userRepository.insert(user);
    }

    public User fromDTO(UserDTO user){
        return new User(user.getId(), user.getName(), user.getEmail());
    }

    public void deleteById(String id){
        findById(id);
        userRepository.deleteById(id);
    }
}
