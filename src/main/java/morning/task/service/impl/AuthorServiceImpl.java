package morning.task.service.impl;

import java.util.List;
import morning.task.dao.AuthorDao;
import morning.task.lib.Inject;
import morning.task.lib.Service;
import morning.task.model.Author;
import morning.task.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Inject
    private AuthorDao authorDao;

    @Override
    public Author add(Author author) {
        return authorDao.add(author);
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }
}
