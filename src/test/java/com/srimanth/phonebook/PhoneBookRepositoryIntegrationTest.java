package com.srimanth.phonebook;

import com.srimanth.phonebook.model.Entry;
import com.srimanth.phonebook.model.PhoneBook;
import com.srimanth.phonebook.repository.EntryRepository;
import com.srimanth.phonebook.repository.PhoneBookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PhoneBookRepositoryIntegrationTest {

    private static final String WORK = "work";
    private static final String FOO = "foo";

    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private PhoneBookRepository phoneBookRepository;

    @Autowired
    private EntryRepository entryRepository;

    @Test
    public void whenFindByName_thenReturnEntry() {

        // given
        whenFindByName_thenReturnPhoneBook();
        List<PhoneBook> phoneBookByName = phoneBookRepository.findByName(FOO);

        Entry fooEntry = new Entry();
        fooEntry.setPhoneNumber(27813173711L);
        fooEntry.setName(WORK);
        fooEntry.setPhoneBook(phoneBookByName.get(0));

        entityManager.persist(fooEntry);
        entityManager.flush();

        // when
        List<Entry> entry = entryRepository.findByName(FOO);

        // then
        assertEquals(entry.get(0).getName(),fooEntry.getName());
    }

    @Test
    public void whenFindByName_thenReturnPhoneBook() {
        // given
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.setName(FOO);
        entityManager.persist(phoneBook);
        entityManager.flush();

        // when
        List<PhoneBook> foundList = phoneBookRepository.findByName(FOO);

        PhoneBook found = foundList.get(0);

        PhoneBook phoneBookReplicate = new PhoneBook();
        phoneBookReplicate.setId(found.getId());
        phoneBookReplicate.setName(found.getName());
        phoneBookReplicate.setEntries(found.getEntries());

        // then
        assertEquals(found.getName(),phoneBook.getName());
        assertTrue(phoneBookReplicate.equals(found));
    }
 
}