package com.example.spring_webflux.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees") // Collection name in MongoDB
@Getter
@Setter
@AllArgsConstructor
public class Employee {

    @Id
    private String id; // MongoDB uses String _id by default

    private String name;
    private String email;
    private String role;

}
