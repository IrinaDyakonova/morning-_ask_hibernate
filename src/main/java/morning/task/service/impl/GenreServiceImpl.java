package morning.task.service.impl;

import java.util.List;
import morning.task.dao.GenreDao;
import morning.task.lib.Inject;
import morning.task.lib.Service;
import morning.task.model.Genre;
import morning.task.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {
    @Inject
    private GenreDao genreDao;

    @Override
    public Genre add(Genre genre) {
        return genreDao.add(genre);
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }
}
