package Connections.DAO;
import java.util.List;

public interface GenericDAO<T> {
        T findById(int id);
        List<T> findAll();
        void create(T object);
        void update(T object);
        void delete(T object);
}
