package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }




    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }


    public User showUserById(int id) {
        Optional<User> userById = userRepository.findById(id);
        return userById.orElse(null);
    }


  /*@Transactional
    public void updateUserById(int id, User updateUser) {
        updateUser.setId(id);
        userRepository.save(updateUser);
    }*/

    @Transactional
    public void updateUserById(int id, User updateUser) {
        updateUser.setId(id);
        if (passwordEncoder.encode(updateUser.getPassword()).hashCode() != userRepository.findUserById(id).getPassword().hashCode()) {
            updateUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        } else {
            updateUser.setPassword(userRepository.findUserById(id).getPassword());
        }
        userRepository.save(updateUser);
    }

    @Transactional
    public void deleteUserById(int id) {
        userRepository.deleteUserById(id);
    }

    @Transactional
    public Role getRoleByName(String username){
        return roleRepository.getRoleByName(username);
    }
}