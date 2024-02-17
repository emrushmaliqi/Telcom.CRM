package repositories;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    void create(T type);
    boolean update(T type);

    boolean deleteById(int id);

    Optional<T> findById(int id);

    Optional<List<T>> findAll();
}
