package com.annie.bookclub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.annie.bookclub.models.Book;
import com.annie.bookclub.models.User;
import com.annie.bookclub.services.BookService;
import com.annie.bookclub.services.UserService;

@Controller
public class BookController {
	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;
	
	
	//-----------Display All Books------------------------------------
	@GetMapping("/books")
	public String allBooks(HttpSession s, Model m) {
		// retrieve the user from session
		Long userId = (Long) s.getAttribute("user_id");
		// check if userId is null (Route guard)
		if(userId == null) {
			return "redirect:/";		
		} else {
			// go to the DB to retrieve the user using the id from session
			User thisUser = userService.findOne(userId);
			List<Book> allBooks = bookService.allBooks();
			m.addAttribute("thisUser", thisUser);
			m.addAttribute("allBooks", allBooks);		
	
			
			return "books.jsp";
		}
		
	}
	
		
	//-----Action: Borrow Book_Update the book with a borrower(user)-------------------
		@PostMapping("/borrow/{id}")
		public String borrowBook(@PathVariable("id") Long bookId, HttpSession session ){
			
			
			// find the book
			Book thisBooktoBorrow = bookService.findBook(bookId);
			//find the user from session
			Long userId = (Long) session.getAttribute("user_id");
			User thisUser = userService.findOne(userId);
			
			thisBooktoBorrow.setBorrower(thisUser);
			bookService.updateBook(thisBooktoBorrow);
		
			
			return "redirect:/books";
		}
		
	
	// -----Action: Return Book _ remove borrower from the book------------
		@PostMapping("/return/{id}")
		public String returnBook(@PathVariable("id") Long bookId, HttpSession session){
			// find the book
			Book thisBooktoReturn = bookService.findBook(bookId);
			// find the user from session
			Long userId = (Long) session.getAttribute("user_id");
			User thisUser = userService.findOne(userId);
			
			thisBooktoReturn.setBorrower(null);
			bookService.updateBook(thisBooktoReturn);
			
			return "redirect:/books";
		}
	
		
		
		
		
	//--------Display:  Form to add new book--------------------------------
	@GetMapping("/books/new")
	public String newBook(Model model, HttpSession session) {
		
		// Route Guard - check if user is in session
		Long userId= (Long) session.getAttribute("user_id");
		if(userId == null) {
			return "redirect:/";
		} else {
			//pass in the logged in user
			User thisLoggedInUser = userService.findOne(userId);			
			model.addAttribute("thisLoggedInUser", thisLoggedInUser);
			model.addAttribute("book", new Book());
			
			
			return "newBook.jsp";
		}
		
		
		
	}	
	
	//-------Action: Create New Book--------------------------------------
	@PostMapping("/books/new")
	public String createBook(@Valid @ModelAttribute("book") Book book,
							BindingResult result, Model model, HttpSession session) {
		// grab the user again to send over again if there are errors
		Long userId=(Long) session.getAttribute("user_id");
		if(userId == null) {
			return "redirect:/";
		}else {
			User thisLoggedInUser = userService.findOne(userId);
			model.addAttribute("thisLoggedInUser", thisLoggedInUser);
			if(result.hasErrors()) {
				
				return "newBook.jsp";
			} else {
				// put loggedInUser into the newBook
				book.setUser(thisLoggedInUser);
				bookService.createBook(book);
				return "redirect:/books";
			}
		}
		
	}
	
	//-------Display: Form to edit a book --------------------------------
	@RequestMapping("/books/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		Book book = bookService.findBook(id);
		model.addAttribute("book", book);
		return "editBook.jsp";
	}
	//-------Action: Update the book -------------------------------------
	@PutMapping("/books/{id}")
	public String update(@Valid @ModelAttribute("book") Book book,
						BindingResult result) {
		if(result.hasErrors()) {
			return "editBook.jsp";
		}else {
			Book b = bookService.findBook(book.getId());
			book.setUser(b.getUser());
			// save borrower id in the book.
			book.setBorrower(b.getBorrower());
			bookService.updateBook(book);
			return "redirect:/books";
		}
	}
	
	
	
	
	//------Display: Show One Book detail----------------------------------
	@GetMapping("/books/{id}")
	public String showOneBook(@PathVariable("id")Long id, Model model, HttpSession session) {
		// grab the user again to send over again if there are errors
		Long userId=(Long) session.getAttribute("user_id");
		if(userId == null) {
			return "redirect:/";		
		} else {
			//pass in the logged in user
			User thisLoggedInUser = userService.findOne(userId);			
			model.addAttribute("thisLoggedInUser", thisLoggedInUser);
			Book book = bookService.findBook(id);
			model.addAttribute("book", book);
			return "oneBook.jsp";
		}
		
	}
	
	
	//-----Action: Delete --------------------------------------------------
	@DeleteMapping("/books/delete/{id}")
	public String deleteBook(@PathVariable("id")Long id) {
		bookService.deleteBook(id);
		return "redirect:/books";
	}
	
	
}
