package com.example.demo.db;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository  extends JpaRepository<Person,Long> {
    @Query(value = "SELECT public.get_all_tables_count()", nativeQuery = true)
    int getalltablescount();
    @Query(value="Select public.get_all_tables()", nativeQuery = true)
    List<String> getalltables();
}
