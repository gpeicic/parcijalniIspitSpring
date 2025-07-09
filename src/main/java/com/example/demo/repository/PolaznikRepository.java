package com.example.demo.repository;

import com.example.demo.model.Polaznik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolaznikRepository extends JpaRepository<Polaznik,Long> {
}
