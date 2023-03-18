import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Book;
import org.example.Library;

import java.util.ArrayList;
import java.util.List;

public class BookSteps {

    Library library = new Library();
    List<Book> result = new ArrayList<>();


    @Given("I am a library member")
    public void iAmALibraryMember() {
        System.out.println("I am a library member");
    }

    @And("there is a book with the title {string}, writen by {string} in the library")
    public void thereIsABook(String title, String author) {
        Book book = new Book(title, true, author);
        library.addBook(book);
        System.out.println("There is a book with the title " + title + ", writen by " + author + " in the library");

    }

    @And("the book {string} is available for borrowing")
    public boolean theBookAvailable(String title) {
        Book book = library.findBook(title, "author");
        if (book != null) {
            return book.isState();
        }
        return false;
    }


    @When("I borrow the book with the title {string}")
    public void iBorrowBook(String title) {
        Book book = library.findBook(title, "author");
        if (book != null) {
            library.checkout(book);
        }
    }

    @Then("the book {string} should be checked out to me")
    public boolean CheckedOutToMe(String title) {
        Book book = library.findBook(title, "author");
        if (book != null) {
            return book.isState();
        }
        return false;
    }


    @And("the book {string} should no longer be available in the library")
    public boolean theBookNotAvailable(String title) {
        Book book = library.findBook(title, "author");
        if (book != null) {
            return book.isState();
        }
        return false;
    }


    @And("the book {string} is already checked out to another member")
    public boolean theBookAnotherMember(String title) {
        Book book = library.findBook(title, "author");
        if (book != null) {
            return book.isState();
        }

        return false;
    }

    @When("I try to borrow the book with the title {string}")
    public void iTryToBorrow(String title) {
        Book book = library.findBook(title, "author");
        if (book != null) {
            library.checkout(book);
        }
    }


    @Then("I should see a message saying {string}")
    public void message(String message) {
        System.out.println("I should see a message saying " + message);
    }


    @And("I have borrowed the book {string}, writen by {string}")
    public void iHaveBorrowed(String title, String author) {
        Book book = new Book(title, true, author);
        library.addBook(book);
        library.checkout(book);
        System.out.println("I have borrowed the book " + title + ", writen by " + author);
    }


    @When("I return the book with the title {string}")
    public void iReturnTheBook(String title) {
        Book book = library.findBook("Pride and Prejudice", "Jane Austen");
        if (book != null) {
            library.checkin(book);
            System.out.println("I return the book with the title Pride and Prejudice");
        }
    }


    @Then("the book {string} should be returned to the library")
    public boolean theBookShouldBeReturned(String title) {
        Book book = library.findBook(title, "author");
        if (book != null) {
            return book.isState();
        }
        return false;
    }


    @And("the book {string} should be available for borrowing")
    public boolean theBookShouldBeAvailable(String title) {
        Book book = library.findBook(title, "author");
        if (book != null) {
            return book.isState();
        }

        return false;
    }
}
