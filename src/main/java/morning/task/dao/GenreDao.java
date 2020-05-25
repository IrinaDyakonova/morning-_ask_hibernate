package morning.task.dao;

import java.util.List;
import morning.task.model.Genre;

public interface GenreDao {

    Genre add(Genre genre);

    List<Genre> getAll();
}
