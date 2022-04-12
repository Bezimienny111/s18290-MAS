package com.example.s18290mas.repository;

import com.example.s18290mas.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairRepository extends JpaRepository<Repair,Long> {
}
