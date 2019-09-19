package com.example.demo.repositories;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository  extends JpaRepository<Person,Long> {
    @Query(value = "SELECT public.get_all_tables_count()", nativeQuery = true)
    int getalltablescount();
    @Query(value="Select public.get_all_tables()", nativeQuery = true)
    List<String> getalltables();
    @Query(value = "select * from information_schema.tables t where t.table_name like %?1",nativeQuery = true)
    List<String> getalltableinfoscheme(String tname);
    @Modifying
    @Query(value="insert into person(first_name,second_name,email,age) values (:first_name,:second_name,:email,:age)",
        nativeQuery = true)
    void insertPerson(@Param("first_name") String first_name,@Param("second_name") String second_name,
                      @Param("email") String email,@Param("age") Long age);
    @Query(value="select * from person where id = (select max(id) from person)",nativeQuery = true)
    Person getLastInsertedPerson();
}
