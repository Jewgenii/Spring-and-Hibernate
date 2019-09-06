package com.example.demo.db;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TestRepositery extends JpaRepository<TestT,Long>{

    @Modifying
    @Query(value = "SELECT ", nativeQuery = true)
    void getSome();

    TestT getByTestField(String testF);
}
