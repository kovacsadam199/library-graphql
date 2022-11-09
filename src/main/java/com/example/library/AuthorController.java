package com.example.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class AuthorController {

    @Autowired
    private  AuthorRepository authorRepository;

    @Autowired
    private  BookRepository bookRepository;


    @QueryMapping
    public Iterable<Author> authors() {
        return authorRepository.findAllByOrderByIdAsc();
    }

    @QueryMapping
    public Optional<Author> authorById(@Argument Long id){
        return authorRepository.findById(id);
    }

    @MutationMapping
    Book addBook(@Argument BookInput book){
        Author author = authorRepository.findById(book.authorId).orElseThrow(() -> new IllegalArgumentException("author not found"));
        Book b = new Book(book.title, author);
        return bookRepository.save(b);
    }


    record BookInput(String title, Long authorId){}
}
