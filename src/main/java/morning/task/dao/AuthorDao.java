package morning.task.dao;

import java.util.List;
import morning.task.model.Author;

public interface AuthorDao {

    Author add(Author author);

    List<Author> getAll();
}
