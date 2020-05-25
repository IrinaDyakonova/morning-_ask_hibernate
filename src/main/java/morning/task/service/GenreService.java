package morning.task.service;

import java.util.List;
import morning.task.model.Genre;

public interface GenreService {

    Genre add(Genre genre);

    List<Genre> getAll();
}
