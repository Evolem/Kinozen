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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserDto findByLogin(String login) {
        return UserMapper.INSTANCE.toDto(userRepository.findOneByLogin(login));
    }

    public User getAnonymousUser() {
        return userRepository.findOneByLogin("anonymous");
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByLogin(username);
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


    public User saveNewUser(UserDto userPojo){
        if(userPojo.getRoles() == null || userPojo.getRoles().isEmpty()){
            userPojo.addRole(roleRepository.getByRole("ROLE_USER"));
        }
        if (userPojo.getNewPassword1() != null) {
            userPojo.setPassword(new BCryptPasswordEncoder().encode(userPojo.getNewPassword1()));
        }
        return save(userPojo);

    }

    @Transactional
    public User save(UserDto userPojo){
        return userRepository.save(UserMapper.INSTANCE.toEntity(userPojo));
    }

    public User updateFieldsAndSave(String login, UserDto sourceOfChanges) {
        UserDto userPojo = findByLogin(login);
        if (sourceOfChanges.getEmail() != null) {
            userPojo.setEmail(sourceOfChanges.getEmail());
        }
        if (sourceOfChanges.getName() != null) {
            userPojo.setName(sourceOfChanges.getName());
        }
        if (sourceOfChanges.getNewPassword1() != null) {
            userPojo.setPassword(new BCryptPasswordEncoder().encode(sourceOfChanges.getNewPassword1()));
        }
        return save(userPojo);
    }
}

