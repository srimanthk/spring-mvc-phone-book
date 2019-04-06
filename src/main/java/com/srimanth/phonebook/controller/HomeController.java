package com.srimanth.phonebook.controller;

import com.srimanth.phonebook.model.Entry;
import com.srimanth.phonebook.model.PhoneBook;
import com.srimanth.phonebook.repository.EntryRepository;
import com.srimanth.phonebook.repository.PhoneBookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller

public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private PhoneBookRepository phoneBookRepository;

    @Autowired
    private EntryRepository entryRepository;

    @RequestMapping(value = {"/", "/home", "/resetSearchCriteria"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView phoneBookHome() throws Exception {
        LOGGER.debug("Entered in HomeController.phoneBookHome");
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("phoneBook", new PhoneBook());
        modelAndView.addObject("phoneBooks", phoneBookRepository.findAll());
        LOGGER.debug("Exit from HomeController.phoneBookHome");
        return modelAndView;
    }

    @RequestMapping(value = "/searchContact", method = RequestMethod.POST)
    public ModelAndView searchContact(PhoneBook phoneBook) {
        LOGGER.debug("Entered in HomeController.searchPhoneBook");
        ModelAndView modelAndView = new ModelAndView("home");
        List<Entry> entries = entryRepository.findByName(phoneBook.getName());
        modelAndView.addObject("entries", entries);
        modelAndView.addObject("phoneBooks", phoneBookRepository.findAll());
        LOGGER.debug("Exit from HomeController.searchPhoneBook");
        return modelAndView;
    }

    @RequestMapping(value = "/addContact", method = RequestMethod.GET)
    public ModelAndView addContact() {
        LOGGER.debug("Entered in HomeController.addContact");
        ModelAndView modelAndView = new ModelAndView("input");
        PhoneBook newContact = new PhoneBook();
        Entry home = new Entry();
        home.setName("Home");
        home.setPhoneBook(newContact);
        newContact.getEntries().add(home);
        Entry work = new Entry();
        work.setName("Work");
        work.setPhoneBook(newContact);
        newContact.getEntries().add(work);
        modelAndView.addObject("phoneBook", newContact);
        LOGGER.debug("Exit from HomeController.addContact");
        return modelAndView;
    }

    @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
    public ModelAndView saveContact(PhoneBook phoneBook) {
        LOGGER.debug("Entered in HomeController.addContact");
        phoneBook.getEntries().forEach(entry -> {
            entry.setPhoneBook(phoneBook);
        });
        phoneBookRepository.saveAndFlush(phoneBook);
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("phoneBook", new PhoneBook());
        modelAndView.addObject("phoneBooks", phoneBookRepository.findAll());
        LOGGER.debug("Exit from HomeController.addContact");
        return modelAndView;
    }

    @PostConstruct
    public void init() {
        PhoneBook p1 = new PhoneBook();
        p1.setName("srimanth");

        Entry home = new Entry();
        home.setName("Home");
        home.setPhoneNumber(new Long("27813173711"));
        home.setPhoneBook(p1);

        Entry work = new Entry();
        work.setName("Work");
        work.setPhoneNumber(new Long("27744501576"));
        work.setPhoneBook(p1);

        p1.getEntries().add(home);
        p1.getEntries().add(work);

        phoneBookRepository.save(p1);
    }

}
