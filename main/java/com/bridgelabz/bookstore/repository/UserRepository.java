package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UserData, Integer>{
    @Query(value="select * from book_store_project  where email =:mail",nativeQuery = true)
    Optional<UserData> findByMail(String mail);

    @Query(value = "SELECT * FROM address_book_data e WHERE e.email = :bemail", nativeQuery = true)
    List<UserData> findByEmailId(@Param("bemail") String bemail);
}
