package morning.task.service.impl;

import java.util.List;
import morning.task.dao.BookDao;
import morning.task.lib.Inject;
import morning.task.lib.Service;
import morning.task.model.Author;
import morning.task.model.Book;
import morning.task.model.Genre;
import morning.task.service.BookService;

@Service
public class BookServiceImpl implements BookService {
    @Inject
    private BookDao bookDao;

    @Override
    public Book add(Book book) {
        return bookDao.add(book);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public Book getBookByTitle(String title) {
        return bookDao.getBookByTitle(title);
    }

    @Override
    public List<Book> getListOfBooksByAuthor(Author author) {
        return bookDao.getListOfBooksByAuthor(author);
    }

    @Override
    public List<Book> getAllBooksByGenre(Genre genre) {
        return bookDao.getAllBooksByGenre(genre);
    }
}
