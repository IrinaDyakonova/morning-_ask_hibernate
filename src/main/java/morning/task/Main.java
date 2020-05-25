package morning.task;

import morning.task.lib.Injector;
import morning.task.model.Author;
import morning.task.model.Book;
import morning.task.model.Genre;
import morning.task.service.AuthorService;
import morning.task.service.BookService;
import morning.task.service.GenreService;

public class Main {
    private static Injector injector = Injector.getInstance("morning.task");
    private static AuthorService authorService
            = (AuthorService) injector.getInstance(AuthorService.class);
    private static BookService bookService
            = (BookService) injector.getInstance(BookService.class);
    private static GenreService genreService
            = (GenreService) injector.getInstance(GenreService.class);

    public static void main(String[] args) {

        Genre genre1 = new Genre();
        genre1.setName("comedy");
        genreService.add(genre1);

        Genre genre2 = new Genre();
        genre2.setName("psychology");
        genreService.add(genre2);

        Genre genre3 = new Genre();
        genre3.setName("philosophy");
        genreService.add(genre3);
        genreService.getAll().forEach(System.out::println);

        Author author1 = new Author();
        author1.setName("Nikolai Gogol");
        authorService.add(author1);

        Author author2 = new Author();
        author2.setName("Oscar Wilde");
        authorService.add(author2);

        Author author3 = new Author();
        author3.setName("NEW Nikolai Gogol");
        authorService.add(author3);

        authorService.getAll().forEach(System.out::println);

        Book book1 = new Book();
        book1.setAuthor(author1);
        book1.setGenre(genre1);
        book1.setName("The auditor");
        bookService.add(book1);

        Book book2 = new Book();
        book2.setAuthor(author2);
        book2.setGenre(genre2);
        book2.setName("The Picture of Dorian Grey");
        bookService.add(book2);

        bookService.getAll().forEach(System.out::println);

        bookService.getAllBooksByGenre(genre1)
                .forEach(System.out::println);
        System.out.println(bookService.getBookByTitle("The auditor"));
        bookService.getListOfBooksByAuthor(author2)
                .forEach(System.out::println);

    }
}
