package com.srimanth.phonebook.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ENTRY")
public class Entry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ENTRY_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PHONE_NUMBER", unique = true, nullable = false)
    private Long phoneNumber;

    @JoinColumn(name = "PHONE_BOOK_ID", nullable = false)
    @ManyToOne(optional = false)
    private PhoneBook phoneBook;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

    public PhoneBook getPhoneBook() {
        return phoneBook;
    }

    public void setPhoneBook(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;
        Entry entry = (Entry) o;
        return Objects.equals(getId(), entry.getId()) &&
                Objects.equals(getName(), entry.getName()) &&
                Objects.equals(getPhoneNumber(), entry.getPhoneNumber()) &&
                Objects.equals(getPhoneBook(), entry.getPhoneBook());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getPhoneNumber(), getPhoneBook());
    }

}
