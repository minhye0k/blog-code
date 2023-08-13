package com.example.replication.repository;

import com.example.replication.entity.ReplTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplTestRepository extends JpaRepository<ReplTest, Long> {
}
