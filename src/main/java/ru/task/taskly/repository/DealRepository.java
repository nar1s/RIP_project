package ru.task.taskly.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.task.taskly.model.Deal;

@Repository
public interface DealRepository extends JpaRepository<Deal, Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM taskly_deal WHERE deal_id = :id", nativeQuery = true)
    void deleteById(@Param("id") Integer id);
}
