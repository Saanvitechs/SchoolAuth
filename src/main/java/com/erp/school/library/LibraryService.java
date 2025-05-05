package com.erp.school.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookIssueRepository bookIssueRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookIssue issueBook(Long bookId, Long studentId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No copies available");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        BookIssue bookIssue = new BookIssue();
        bookIssue.setBookId(bookId);
        bookIssue.setStudentId(studentId);
        bookIssue.setIssueDate(LocalDate.now());
        bookIssue.setDueDate(LocalDate.now().plusDays(15)); // 15 days due period
        return bookIssueRepository.save(bookIssue);
    }

    public BookIssue returnBook(Long issueId) {
        BookIssue bookIssue = bookIssueRepository.findById(issueId).orElseThrow(() -> new RuntimeException("Issue record not found"));
        Book book = bookRepository.findById(bookIssue.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        bookIssue.setReturnDate(LocalDate.now());
        if (bookIssue.getReturnDate().isAfter(bookIssue.getDueDate())) {
            long daysLate = bookIssue.getReturnDate().toEpochDay() - bookIssue.getDueDate().toEpochDay();
            bookIssue.setFine(daysLate * 5); // Fine of 5 units per day
        }
        return bookIssueRepository.save(bookIssue);
    }

    public List<BookIssue> getBookIssues(Long bookId) {
        return bookIssueRepository.findByBookId(bookId);
    }
}