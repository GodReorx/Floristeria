package Connections.DAO;

import FlowerStore.Interfaces.GardenElements;

import java.util.List;

public class GardenElementsDAO<T extends GardenElements> implements GenericDAO {
    @Override
    public T findById(int id) {
        // Implementa la lógica para encontrar un producto por su ID en la base de datos
        return null;
    }

    @Override
    public List<T> findAll() {
        // Implementa la lógica para encontrar todos los productos en la base de datos
        return null;
    }

    @Override
    public void create(Object object) {
        // Implementa la lógica para crear un nuevo producto en la base de datos
    }

    @Override
    public void update(Object object) {
        // Implementa la lógica para actualizar un producto existente en la base de datos
    }

    @Override
    public void delete(Object object) {
        // Implementa la lógica para eliminar un producto existente de la base de datos
    }
}
