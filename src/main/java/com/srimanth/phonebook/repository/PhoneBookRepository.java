package com.srimanth.phonebook.repository;

import com.srimanth.phonebook.model.PhoneBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneBookRepository extends JpaRepository<PhoneBook, Integer> {

    @Query(nativeQuery = true, value = "select pb.* from phone_book pb where pb.name like %?1%")
    List<PhoneBook> findByName(String name);

}

