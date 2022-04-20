package com.annie.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.annie.bookclub.models.Book;
import com.annie.bookclub.models.User;
import com.annie.bookclub.repositories.BookRepository;
import com.annie.bookclub.repositories.UserRepository;

@Service
public class BookService {
	//----------Dependency Injection to talk to BookRepository------------------
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserRepository userRepository;
	
	//--------READ ALL --------------------------------------
	public List<Book> allBooks(){
		return bookRepository.findAll();
	}
	
	//-------CREATE ----------------------------------------
	public Book createBook(Book book) {
		return bookRepository.save(book);
	}
	
	//----------READ ONE-----------------------------------
	public Book findBook(Long id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
			if(optionalBook.isPresent()) {
				return optionalBook.get();
			} else {
				return null;
			}
	}
	
	//-----------UPDATE -------------------------------------
	
	public Book updateBook(Book book) {
		return bookRepository.save(book);
	}
	
	//----------------UPDATE book with user------------------------
//	public Book updateBookWithUser(Book book, User user) {
//		book.setUser(user);
//		return bookRepository.save(book);
//	}
	
	//----------DELETE ----------------------------------
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}
	
	
	
	
}
