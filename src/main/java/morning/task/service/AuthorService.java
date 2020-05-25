package morning.task.service;

import java.util.List;
import morning.task.model.Author;

public interface AuthorService {
    Author add(Author author);

    List<Author> getAll();
}
