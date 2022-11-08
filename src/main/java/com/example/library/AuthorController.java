package com.example.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @QueryMapping
    public Iterable<Author> authors() {
        return authorRepository.findAllByOrderByIdAsc();
    }

    @QueryMapping
    public Optional<Author> authorById(@Argument Long id){
        return authorRepository.findById(id);
    }
}
