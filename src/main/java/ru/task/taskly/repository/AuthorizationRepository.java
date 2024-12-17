package ru.task.taskly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.task.taskly.model.User;

import java.util.Optional;

@Repository
public interface AuthorizationRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM taskly_user WHERE user_login = :login", nativeQuery = true)
    Optional<User> findUserByUsername(@Param("login") String username);

    @Query(value = "SELECT count(*) = 0 FROM taskly_user WHERE user_login = :username", nativeQuery = true)
    Boolean isUsernameUnique(@Param("username") String username);

    @Query(value = "SELECT count(*) = 0 FROM taskly_user WHERE user_email = :email", nativeQuery = true)
    Boolean isEmailUnique(@Param("email") String email);

    @Query(value = "INSERT INTO taskly_user(user_login, user_email, user_password)" +
            "VALUES (:username, :email, :password) RETURNING *", nativeQuery = true)
    User saveUser(@Param("username") String username,
                  @Param("email") String email,
                  @Param("password") String password);
}
