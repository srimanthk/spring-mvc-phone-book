package com.srimanth.phonebook.repository;

import com.srimanth.phonebook.model.Entry;
import com.srimanth.phonebook.model.PhoneBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Integer> {

    @Query(nativeQuery = true, value = "select e.* from entry e join phone_book pb on (e.phone_book_id = pb.id) " +
            "where pb.name like %?1%")
    List<Entry> findByName(String name);

}
