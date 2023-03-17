Feature: Library returns book
  As a librarian
  I want to return a book to the library
  So that I can keep track of inventory

  Scenario: Successful return
    Given a book is checked out with ID "1234"
    When I return the book
    Then the book with ID "1234" should be marked as returned

  Scenario: Invalid book ID
    Given an invalid book ID "5678"
    When I attempt to return the book
    Then I should see an error message "Invalid book ID"
