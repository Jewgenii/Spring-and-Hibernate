package com.example.demo.repositories;

import com.example.demo.model.cities_streets.CitiesToStreets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesToStreetsRepository  extends JpaRepository<CitiesToStreets,Long> {
}
