package morning.task.dao.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import morning.task.dao.BookDao;
import morning.task.exeption.DataProcessingException;
import morning.task.lib.Dao;
import morning.task.model.Author;
import morning.task.model.Book;
import morning.task.model.Genre;
import morning.task.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class BookDaoImpl implements BookDao {
    private static final Logger LOGGER = Logger.getLogger(BookDaoImpl.class);

    @Override
    public Book add(Book book) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long bookId = (Long) session.save(book);
            transaction.commit();
            book.setId(bookId);
            LOGGER.info("book " + book.getId()
                    + " " + book.getName() + " insert");
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert book entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Book> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<Book> criteriaQuery = session
                    .getCriteriaBuilder().createQuery(Book.class);
            criteriaQuery.from(Book.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all Book ", e);
        }
    }

    @Override
    public Book getBookByTitle(String title) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Book> query = session.createQuery(
                    "From Book where name = :title");
            query.setParameter("title", title);
            return query.list().get(0);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get available book", e);
        }
    }

    @Override
    public List<Book> getListOfBooksByAuthor(Author author) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Book> query = session.createQuery(
                    "From Book where author = :author");
            query.setParameter("author", author);
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get available books", e);
        }
    }

    @Override
    public List<Book> getAllBooksByGenre(Genre genre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Book> query = session.createQuery(
                    "From Book where genre = :genre");
            query.setParameter("genre", genre);
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get available books", e);
        }
    }
}
