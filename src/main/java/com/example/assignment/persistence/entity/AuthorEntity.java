package com.example.assignment.persistence.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "author")
@Data
public class AuthorEntity extends BaseEntity {

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "deleted")
    private Boolean deleted;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<BookEntity> books;

    public AuthorEntity(String authorName, String email, String mobileNumber, Boolean deleted) {
        this.authorName = authorName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.deleted = deleted;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public AuthorEntity() {
    }
}
