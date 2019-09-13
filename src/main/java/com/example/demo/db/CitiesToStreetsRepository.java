package com.example.demo.db;

import com.example.demo.model.CitiesToStreets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesToStreetsRepository  extends JpaRepository<CitiesToStreets,Long> {
}
