package bg.tilchev.io;

import bg.tilchev.models.Author;
import bg.tilchev.models.Book;
import bg.tilchev.models.Category;
import bg.tilchev.models.enums.AgeRestriction;
import bg.tilchev.models.enums.EditionType;
import bg.tilchev.services.AuthorService;
import bg.tilchev.services.BookService;
import bg.tilchev.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Todor Ilchev on 2016-11-18.
 */

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private static final Random random = new Random();

    private AuthorService authorService;

    private BookService bookService;

    private CategoryService categoryService;

    @Autowired
    public Terminal(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... strings) throws Exception {
//        this.printBooksAfter2000(); // 1
//        this.printAuthorsWithBooksBefore1990(); // 2
//        this.printAuthorsOrderedByBookCountDesc(); // 3
//        this.printBooksByAuthor(); // 4
        this.pr09();
    }

    private void pr09() {
        List<Book> books = this.bookService.retrieveAll();
        List<Book> threeBooks = books.subList(0, 3);
        threeBooks.get(0).getRelatedBooks().add(threeBooks.get(1));
        threeBooks.get(1).getRelatedBooks().add(threeBooks.get(0));
        threeBooks.get(0).getRelatedBooks().add(threeBooks.get(2));
        threeBooks.get(2).getRelatedBooks().add(threeBooks.get(0));
        for (Book book : threeBooks) {
            this.bookService.persist(book);
        }

        for (Book book : threeBooks) {
            System.out.printf("--%s\n", book.getTitle());
            for (Book relatedBook : book.getRelatedBooks()) {
                System.out.println(relatedBook.getTitle());
            }
        }
    }

    private void printBooksByAuthor() {
        List<Book> books = this.bookService.boosByAuthor("George", "Powell");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void printAuthorsOrderedByBookCountDesc() {
        List<Author> authors = this.authorService.findAllOrderedByBooks();
        for (Author author : authors) {
            System.out.println(author + " books: " + author.getBooks().size());
        }
    }

    private void printAuthorsWithBooksBefore1990() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1990, Calendar.JANUARY, 1);
        List<Author> authors = this.authorService.findByBooksAfter(calendar.getTime());
        for (Author author : authors) {
            String line = author.getFirstName() + " " + author.getLastName();
            System.out.println(line);
        }
    }

    private void printBooksAfter2000() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1);
        List<Book> books = this.bookService.booksAfterDate(calendar.getTime());
        for (Book book : books) {
            System.out.println(book.getTitle() + " " + book.getReleaseDate());
        }
    }

    @PostConstruct
    private void seedDatabase() throws IOException, ParseException {
        if (this.isDataPopulated()) {
            return;
        }
        List<Author> authors = new ArrayList<>();
        BufferedReader authorsReader = new BufferedReader(new FileReader("res/authors.txt"));
        String line = authorsReader.readLine();
        while((line = authorsReader.readLine()) != null){
            String[] data = line.split("\\s+");
            String firstName = data[0];
            String lastName = data[1];
            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);

            this.authorService.persist(author);
            authors.add(author);
        }

        List<Category> categories = new ArrayList<>();
        BufferedReader categoriesReader = new BufferedReader(new FileReader("res/categories.txt"));
        while((line = categoriesReader.readLine()) != null){
            Category category = new Category();
            category.setName(line);

            this.categoryService.persist(category);
            categories.add(category);
        }

        BufferedReader booksReader = new BufferedReader(new FileReader("res/books.txt"));
        line = booksReader.readLine();
        while((line = booksReader.readLine()) != null){
            String[] data = line.split("\\s+");
            int authorIndex = random.nextInt(authors.size());
            Author author = authors.get(authorIndex);
            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(data[1]);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            titleBuilder.setLength(titleBuilder.length() - 1);
            String title = titleBuilder.toString();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            for (int i = 0; i < 3; i++) {
                int categoryIndex = random.nextInt(categories.size());
                Category category = categories.get(categoryIndex);
                book.addCategory(category);
            }

            this.bookService.persist(book);
        }
    }

    private boolean isDataPopulated() {
        return this.authorService.count() > 0 ||
                this.categoryService.count() > 0 ||
                this.bookService.count() > 0;
    }
}
