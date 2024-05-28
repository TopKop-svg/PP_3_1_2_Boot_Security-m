package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    @Query("SELECT DISTINCT  u FROM User u JOIN FETCH u.roles WHERE u.id = :id")
    @Transactional
    User findUserByIdWithRoles(@Param("id") int id);
    @Query("SELECT DISTINCT  u FROM User u JOIN FETCH u.roles")
    @Transactional
    List<User> findAllUsersWithRoles();
    void deleteUserById(int id);
}
