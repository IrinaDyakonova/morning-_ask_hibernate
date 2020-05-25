package morning.task.dao.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import morning.task.dao.AuthorDao;
import morning.task.exeption.DataProcessingException;
import morning.task.lib.Dao;
import morning.task.model.Author;
import morning.task.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class AuthorDaoImpl implements AuthorDao {
    private static final Logger LOGGER = Logger.getLogger(AuthorDaoImpl.class);

    @Override
    public Author add(Author author) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long authorId = (Long) session.save(author);
            transaction.commit();
            author.setId(authorId);
            LOGGER.info("author " + author.getId()
                    + " " + author.getName() + " insert");
            return author;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert author entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Author> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<Author> criteriaQuery = session
                    .getCriteriaBuilder().createQuery(Author.class);
            criteriaQuery.from(Author.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all Author ", e);
        }
    }
}
