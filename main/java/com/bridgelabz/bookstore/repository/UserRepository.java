package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <UserData, Integer>{

    @Query(value = "SELECT * from usersDetails u where u.email_id = :bemail_id",nativeQuery = true)
    List<UserData> findByEmail(@Param("bemail_id") String bemail_id);

}
