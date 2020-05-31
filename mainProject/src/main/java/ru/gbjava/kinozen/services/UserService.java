package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gbjava.kinozen.persistence.entities.Role;
import ru.gbjava.kinozen.persistence.entities.User;
import ru.gbjava.kinozen.persistence.repositories.UserRepository;
import ru.gbjava.kinozen.services.pojo.UserPojo;

import java.util.*;

/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserPojo findByLogin(String login) {
        return new UserPojo(userRepository.findOneByLogin(login));
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



    public void delete(User user){
        userRepository.delete(user);
    }

    public boolean isUserExist(String login) {
        return userRepository.existsByLogin(login);
    }

    public User save(UserPojo userPojo){
        User user = User.builder()
                .id(userPojo.getId())
                .login(userPojo.getLogin())
                .email(userPojo.getEmail())
                .name(userPojo.getName())
                .password(userPojo.getPassword())
                .roles(userPojo.getRoles()).build();
        return userRepository.save(user);
    }

    public User updateFieldsAndSave(String login, UserPojo sourceOfChanges) {
        UserPojo userPojo = findByLogin(login);
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

