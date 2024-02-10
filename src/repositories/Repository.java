package repositories;

import java.util.List;

public interface Repository<T> {
    void create();
    boolean update(T type);

    boolean deleteById(int id);

    T findById(int id);

    List<T> findAll();
}
