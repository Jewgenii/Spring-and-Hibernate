package com.example.demo.repositories;

import com.example.demo.model.cities_streets.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetsRepository  extends JpaRepository<Street,Long> {
}
