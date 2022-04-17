package org.sam.prueba.superheroes.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.sam.prueba.superheroes.model.Superheroe;
import org.sam.prueba.superheroes.service.SuperheroesService;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class SuperheroesServiceImp implements SuperheroesService {

    private final NamedParameterJdbcTemplate namedJdbc;

    @Autowired
    public SuperheroesServiceImp(NamedParameterJdbcTemplate namedJdbc) {
        this.namedJdbc = namedJdbc;
    }

    @Override
    public List<Superheroe> findAll() {
        return namedJdbc.query("select *"
                + " from TBL_SUPERHEROES",
                BeanPropertyRowMapper.newInstance(Superheroe.class)
        );
    }

    @Override
    public Superheroe find(long heroeId) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", heroeId);
        try {
            return namedJdbc.queryForObject("select *"
                    + " from TBL_SUPERHEROES"
                    + " where id= :id",
                    namedParameters,
                    BeanPropertyRowMapper.newInstance(Superheroe.class)
            );
        } catch (IncorrectResultSizeDataAccessException noFound) {
        }
        return null;
    }

    @Override
    public List<Superheroe> findByName(String name) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("name", "%" + name.toUpperCase() + "%");
        return namedJdbc.query("select *"
                + " from TBL_SUPERHEROES"
                + " where UCASE(nombre) like :name",
                namedParameters,
                BeanPropertyRowMapper.newInstance(Superheroe.class)
        );
    }

    @Override
    public boolean create(Superheroe heroe) {
        try {
            int numRows = namedJdbc.update(
                    "INSERT INTO TBL_SUPERHEROES ("
                    + " id, nombre"
                    + ") VALUES ("
                    + ":id,:nombre"
                    + ")",
                    new BeanPropertySqlParameterSource(heroe)
            );
            return numRows == 1;
        } catch (DataAccessException posible_violacion_clave_primaria) {
        }
        return false;
    }

    @Override
    public boolean update(Superheroe heroe) {
        int numRows = namedJdbc.update(
                "UPDATE TBL_SUPERHEROES SET"
                + " nombre= :nombre"
                + " WHERE id= :id",
                new BeanPropertySqlParameterSource(heroe)
        );
        return numRows == 1;
    }

    @Override
    public boolean delete(long heroeId) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", heroeId);
        int numRows = namedJdbc.update(
                "DELETE"
                + " FROM TBL_SUPERHEROES"
                + " WHERE id= :id",
                namedParameters
        );
        return numRows == 1;
    }
}
