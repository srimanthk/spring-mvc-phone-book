package com.srimanth.phonebook.controller;

import com.srimanth.phonebook.model.Entry;
import com.srimanth.phonebook.model.PhoneBook;
import com.srimanth.phonebook.repository.EntryRepository;
import com.srimanth.phonebook.repository.PhoneBookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    private final String TARGET = "home";
    final String RETURN_KEY = "phoneBook";

    // Create an instance of what you are going to test.
    // When using the @InjectMocks annotation, you must create the instance in
    // the constructor or in the field declaration.
    @InjectMocks
    private HomeController controllerUT = new HomeController();

    // The @Mock annotation creates the mock instance of the class and
    // automatically injects into the object annotated with @InjectMocks (if
    // possible).
    @Mock
    private PhoneBookRepository phoneBookRepository;
    // This @Mock annotation simply creates a mock instance. There is nowhere to
    // inject it. Depending on the particular circumstance, it may be better or
    // clearer to instantiate the mock explicitly in the test itself, but we're
    // doing it here for illustration. Also, I don't know what your real class
    // is like, but it may be more appropriate to just instantiate a real one
    // than a mock one.
    @Mock
    private ModelMap model;
    // Same as above
    @Mock
    private EntryRepository entryRepository;

    @Before
    public void setUp() {
        // We want to make sure that when we call getFileData(), it returns
        // something non-null, so we return the mock of fileData.

        List<PhoneBook> allPhones = new ArrayList<>();
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.setId(1);
        Entry entry = new Entry();
        entry.setName("Home");
        entry.setPhoneBook(phoneBook);
        entry.setPhoneNumber(Long.valueOf("123456789"));
        entry.setId(1);

        phoneBook.setName("Test");
        phoneBook.getEntries().add(entry);

        allPhones.add(phoneBook);

        when(phoneBookRepository.findAll()).thenReturn(allPhones);
    }

    @Test
    public void phoneBookHome() throws Exception {
        ModelAndView modelAndView = controllerUT.phoneBookHome();
        assertEquals(TARGET,modelAndView.getViewName());
        assertTrue(modelAndView.getModel().containsKey(RETURN_KEY));
    }

    @Test
    public void searchContact() throws Exception {
        ModelAndView phoneBookHome = controllerUT.phoneBookHome();
        ModelAndView modelAndView = controllerUT.searchContact((PhoneBook) phoneBookHome.getModel().get(RETURN_KEY));
        assertEquals(TARGET,modelAndView.getViewName());
        assertTrue(modelAndView.getModel().containsKey("entries"));
    }

    @Test
    public void addContact() {
        ModelAndView addContactModel = controllerUT.addContact();
        assertEquals("input", addContactModel.getViewName());
        assertTrue(addContactModel.getModel().containsKey("phoneBook"));
    }

    @Test
    public void saveContact() throws Exception {
        ModelAndView phoneBookHome = controllerUT.phoneBookHome();
        ModelAndView addContactModel = controllerUT.saveContact((PhoneBook) phoneBookHome.getModel().get(RETURN_KEY));
        assertEquals(TARGET, addContactModel.getViewName());
        assertTrue(addContactModel.getModel().containsKey("phoneBook"));
    }

}