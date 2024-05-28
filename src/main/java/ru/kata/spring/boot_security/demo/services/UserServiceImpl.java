package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(int id) {
        return userRepository.findUserByIdWithRoles(id);
    }

    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAllUsersWithRoles();
    }

    @Transactional
    public void updateUserById(int id, User updateUser) {
        User existingUser = userRepository.findUserByIdWithRoles(id);
        updateUser.setId(id);
        updateUser.setRoles(updateUser.getRoles());

        if (!passwordEncoder.matches(updateUser.getPassword(), existingUser.getPassword())) {
            updateUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        } else {
            updateUser.setPassword(existingUser.getPassword());
        }

        userRepository.save(updateUser);
    }

    @Transactional
    public void deleteUserById(int id) {
        userRepository.deleteUserById(id);
    }
}