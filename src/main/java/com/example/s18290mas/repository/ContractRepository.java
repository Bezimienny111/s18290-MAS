package com.example.s18290mas.repository;

import com.example.s18290mas.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

}
