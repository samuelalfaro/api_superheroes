package org.sam.prueba.superheroes.service;

import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sam.prueba.superheroes.model.Superheroe;
import org.sam.prueba.superheroes.service.imp.SuperheroesServiceImp;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author sam
 */
public class SuperheroesServiceTest {
    
    private static NamedParameterJdbcTemplate buildJdbc() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addDefaultScripts()
                .build();
        return new NamedParameterJdbcTemplate(dataSource);
    }

    private static SuperheroesService buildService() {
        return new SuperheroesServiceImp(buildJdbc());
    }

    public SuperheroesServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of findAll method, of class SuperheroesService.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        SuperheroesService instance = buildService();
        List<Superheroe> result = instance.findAll();
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of find method, of class SuperheroesService.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        long heroeId = 0L;
        SuperheroesService instance = buildService();
        Superheroe expResult = null;
        Superheroe result = instance.find(heroeId);
        assertEquals(expResult, result);
        
        heroeId = 11L;
        result = instance.find(heroeId);
        assertTrue(result != null);
    }

    /**
     * Test of findByName method, of class SuperheroesService.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        String name = "OC";
        SuperheroesService instance = buildService();
        List<Superheroe> result = instance.findByName(name);
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of create method, of class SuperheroesService.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Superheroe heroe = new Superheroe( 11L, "Repetido");
        SuperheroesService instance = buildService();
        boolean expResult = false;
        boolean result = instance.create(heroe);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class SuperheroesService.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Superheroe heroe = new Superheroe( 11L, "Repetido");
        SuperheroesService instance = buildService();
        boolean expResult = true;
        boolean result = instance.update(heroe);
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class SuperheroesService.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        long heroeId = 0L;
        SuperheroesService instance = buildService();
        boolean expResult = false;
        boolean result = instance.delete(heroeId);
        assertEquals(expResult, result);
    }
    
}
