package com.epam.bdd.utaf.web.models;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Email {
    private String recipient;
    private String subject;
    private String bodyMessage;

    public Email(){
        Faker faker = new Faker();
        Random random = new Random();
        this.recipient = "dessie.zhang16@google.com";
        this.subject = "Test email "+random.nextInt(999999);
        this.bodyMessage = faker.name().title();
    }
}
