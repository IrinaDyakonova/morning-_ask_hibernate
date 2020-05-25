package morning.task.dao;

import java.util.List;
import morning.task.model.Author;
import morning.task.model.Book;
import morning.task.model.Genre;

public interface BookDao {

    Book add(Book book);

    List<Book> getAll();

    Book getBookByTitle(String title);

    List<Book> getListOfBooksByAuthor(Author author);

    List<Book> getAllBooksByGenre(Genre genre);

}
