package com.example.assignment.persistence.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorRequestDTO {

    @JsonProperty("author_name")
    private String authorName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("mobile")
    private String mobile;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public AuthorRequestDTO(String authorName, String email, String mobile) {
        this.authorName = authorName;
        this.email = email;
        this.mobile = mobile;
    }
}
