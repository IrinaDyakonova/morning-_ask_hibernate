package morning.task.dao.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import morning.task.dao.GenreDao;
import morning.task.exeption.DataProcessingException;
import morning.task.lib.Dao;
import morning.task.model.Genre;
import morning.task.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class GenreDaoImpl implements GenreDao {
    private static final Logger LOGGER = Logger.getLogger(GenreDaoImpl.class);

    @Override
    public Genre add(Genre genre) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long genreId = (Long) session.save(genre);
            transaction.commit();
            genre.setId(genreId);
            LOGGER.info("genre " + genre.getId()
                    + " " + genre.getName() + " insert");
            return genre;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert genre entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Genre> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<Genre> criteriaQuery = session
                    .getCriteriaBuilder().createQuery(Genre.class);
            criteriaQuery.from(Genre.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all genre ", e);
        }
    }
}
