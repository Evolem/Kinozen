package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gbjava.kinozen.dto.UserDto;
import ru.gbjava.kinozen.dto.mappers.UserMapper;
import ru.gbjava.kinozen.persistence.entities.Role;
import ru.gbjava.kinozen.persistence.entities.User;
import ru.gbjava.kinozen.persistence.repositories.RoleRepository;
import ru.gbjava.kinozen.persistence.repositories.UserRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService, CrudService<User, UUID> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;



    public User findByLogin(String login) {
        return userRepository.findOneByLogin(login).orElseThrow(()-> new RuntimeException("Login not found! " + login));
    }

    public User getAnonymousUser() {
        return userRepository.findOneByLogin("anonymous").orElseThrow(()-> new RuntimeException("Login not found! anon"));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByLogin(username).orElseThrow(()-> new RuntimeException("Username not found! " + username));
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        if(roles == null)
            return new ArrayList<>();

        List<GrantedAuthority> grandAuthority = new ArrayList<>();

        for (Role role : roles) {
            grandAuthority.add((GrantedAuthority)role::getRole);
        }
        return grandAuthority;
    }

    @Transactional
    public void delete(User user){
        userRepository.delete(user);
    }

    public boolean isUserExist(String login) {
        return userRepository.existsByLogin(login);
    }

    public boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }


    public User saveNewUser(UserDto userDto){
        if(userDto.getRoles() == null || userDto.getRoles().isEmpty()){
            userDto.addRole(roleRepository.getByRole("ROLE_USER").orElseThrow(()-> new RuntimeException("Role not found!")));
        }
        if (userDto.getNewPassword1() != null) {
            userDto.setPassword(new BCryptPasswordEncoder().encode(userDto.getNewPassword1()));
        }
        return save(userDto);

    }

    @Transactional
    public User save(UserDto userDto){
        return userRepository.save(UserMapper.INSTANCE.toEntity(userDto));
    }

    public User updateFieldsAndSave(String login, UserDto sourceOfChanges) {
        User user = findByLogin(login);
        UserDto userDto = UserMapper.INSTANCE.toDto(user);
        if (sourceOfChanges.getEmail() != null) {
            userDto.setEmail(sourceOfChanges.getEmail());
        }
        if (sourceOfChanges.getName() != null) {
            userDto.setName(sourceOfChanges.getName());
        }
        if (sourceOfChanges.getNewPassword1() != null) {
            userDto.setPassword(new BCryptPasswordEncoder().encode(sourceOfChanges.getNewPassword1()));
        }
        return save(userDto);
    }

    @Override
    public List<User> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public User findById(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(() -> new RuntimeException("User not found! " + uuid));
    }

    @Override
    public User save(User user) {
       return userRepository.save(user);
    }

    @Override
    public void deleteById(UUID uuid) {
        userRepository.deleteById(uuid);
    }
}

