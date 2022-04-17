package org.sam.prueba.superheroes.service;

import java.util.List;
import org.sam.prueba.superheroes.model.Superheroe;

public interface SuperheroesService {

    public List<Superheroe> findAll();

    public Superheroe find(long heroeId);

    public List<Superheroe> findByName(String name);

    public boolean create(Superheroe heroe);

    public boolean update(Superheroe heroe);

    public boolean delete(long heroeId);
}
