
Feature: Borrowing books from the library
  As a library member
  I want to borrow books from the library
  So that I can read them at home

  Scenario: Borrowing a book that is available
    Given I am a library member
    And there is a book with the title "The Great Gatsby", writen by 'F. Scott Fitzgerald' in the library
    And the book "The Great Gatsby" is available for borrowing
    When I borrow the book with the title "The Great Gatsby"
    Then the book "The Great Gatsby" should be checked out to me
    And the book "The Great Gatsby" should no longer be available in the library

  Scenario: Borrowing a book that is not available
    Given I am a library member
    And there is a book with the title "The Catcher in the Rye", writen by 'J. D. Salinger' in the library
    And the book "The Catcher in the Rye" is already checked out to another member
    When I try to borrow the book with the title "The Catcher in the Rye"
    Then I should see a message saying "The book is not available for borrowing"

  Scenario: Returning a book that was checked out
    Given I am a library member
    And I have borrowed the book "Pride and Prejudice", writen by 'Jane Austen'
    When I return the book with the title 'Pride and Prejudice'
    Then the book "Pride and Prejudice" should be returned to the library
    And the book "Pride and Prejudice" should be available for borrowing
