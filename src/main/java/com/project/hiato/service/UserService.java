package com.project.hiato.service;

import com.project.hiato.dto.UserDTO;
import com.project.hiato.dto.UserPasswordDTO;
import com.project.hiato.dto.UserResponseDTO;
import com.project.hiato.entity.User;
import com.project.hiato.exception.BusinessRuleException;
import com.project.hiato.exception.ConflictException;
import com.project.hiato.exception.ResourceNotFoundException;
import com.project.hiato.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    public UserService(UserRepository userRepository, PasswordEncoder encoder){
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public UserResponseDTO create(UserDTO data){

        if(userRepository.existsByNickname(data.getNickname())){
            throw new ConflictException("Nickname already exists");
        }

        if(userRepository.existsByEmail(data.getEmail())){
            throw new ConflictException("Email already exists");
        }

        User user = new User();
        user.setName(data.getName());
        user.setEmail(data.getEmail());
        user.setNickname(data.getNickname());
        user.setPassword(encoder.encode(data.getPassword()));

        User saved = userRepository.save(user);

        UserResponseDTO response = new UserResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setEmail(saved.getEmail());
        response.setNickname(saved.getNickname());
        return response;
    }

    public List<UserResponseDTO> findAll(){
        List<User> users = userRepository.findAll();

        List<UserResponseDTO> response = new ArrayList<>();
        for(int i = 0; i < users.size(); i++){
            UserResponseDTO dto = new UserResponseDTO();
            dto.setId(users.get(i).getId());
            dto.setName(users.get(i).getName());
            dto.setNickname(users.get(i).getNickname());
            dto.setEmail(users.get(i).getEmail());
            response.add(dto);
        }

        return response;
    }

    public UserResponseDTO findById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        UserResponseDTO response = new UserResponseDTO();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setNickname(user.getNickname());
        response.setName(user.getName());

        return response;
    }

    public void deleteById(Long id){
        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    public UserResponseDTO update(Long id, UserDTO data){
        User user = userRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("User not found"));

        if(userRepository.existsByNicknameAndIdNot(data.getNickname(), id)){
            throw new ConflictException("Nickname already exists");
        }

        if(userRepository.existsByEmailAndIdNot(data.getEmail(), id)){
            throw new ConflictException("Email already exists");
        }

        user.setName(data.getName());
        user.setNickname(data.getNickname());
        user.setEmail(data.getEmail());

        User updated = userRepository.save(user);

        UserResponseDTO response = new UserResponseDTO();
        response.setId(updated.getId());
        response.setName(updated.getName());
        response.setEmail(updated.getEmail());
        response.setNickname(updated.getNickname());
        return response;
    }

    public UserResponseDTO updatePassword(Long id, UserPasswordDTO passwordDTO){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        boolean matches = encoder.matches(passwordDTO.getPreviousPassword(), user.getPassword());

        if(!matches){
            throw new BusinessRuleException("Current password is incorrect");
        }

        user.setPassword(encoder.encode(passwordDTO.getNewPassword()));
        user = userRepository.save(user);

        UserResponseDTO response = new UserResponseDTO();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setNickname(user.getNickname());
        response.setEmail(user.getEmail());
        return response;
    }

}
