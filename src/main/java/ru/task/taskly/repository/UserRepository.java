package ru.task.taskly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.task.taskly.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM taskly_user WHERE user_login = :login", nativeQuery = true)
    Optional<User> findByLogin(@Param("login") String login);
}
