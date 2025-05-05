package com.erp.school.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book,
                                        @RequestHeader("tenant") String tenant) {
        return ResponseEntity.ok(libraryService.addBook(book));
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(@RequestHeader("tenant") String tenant) {
        return ResponseEntity.ok(libraryService.getAllBooks());
    }

    @PostMapping("/books/{bookId}/issue")
    public ResponseEntity<BookIssue> issueBook(@PathVariable Long bookId, @RequestParam Long studentId,@RequestHeader("tenant") String tenant) {
        return ResponseEntity.ok(libraryService.issueBook(bookId, studentId));
    }

    @PostMapping("/books/return/{issueId}")
    public ResponseEntity<BookIssue> returnBook(@PathVariable Long issueId, @RequestHeader("tenant") String tenant) {
        return ResponseEntity.ok(libraryService.returnBook(issueId));
    }

    @GetMapping("/books/{bookId}/issues")
    public ResponseEntity<List<BookIssue>> getBookIssues(@PathVariable Long bookId, @RequestHeader("tenant") String tenant) {
        return ResponseEntity.ok(libraryService.getBookIssues(bookId));
    }
}