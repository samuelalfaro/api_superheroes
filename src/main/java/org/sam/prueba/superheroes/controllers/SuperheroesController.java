package org.sam.prueba.superheroes.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.sam.prueba.common.exceptions.BusinessException;
import org.sam.prueba.superheroes.config.SwaggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.sam.prueba.superheroes.model.Superheroe;
import org.sam.prueba.superheroes.service.SuperheroesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/superheroes")
@Api(tags = { SwaggerConfig.TAG_1 }, produces="application/json")
public class SuperheroesController {

    private final SuperheroesService service;

    @Autowired
    public SuperheroesController(SuperheroesService service) {
        this.service = service;
    }

    @GetMapping(value = "/", produces = "application/json" )
    @ApiOperation(value = "Consultar todos los súper héroes.")
    public ResponseEntity<List<Superheroe>> findAll() {
        List<Superheroe> list = service.findAll();
        if(list != null && !list.isEmpty()){
            return ResponseEntity.ok(list);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{heroeId}", produces = "application/json")
    @ApiOperation(value = "Consultar un único súper héroe por id.")
    public ResponseEntity<Superheroe> find(@PathVariable("heroeId") long heroeId) {
        Superheroe dato = service.find(heroeId);
        if (dato != null) {
            return ResponseEntity.ok(dato);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/byname", produces = "application/json" )
    @ApiOperation(value
            = "Consultar todos los súper héroes que contienen, en su nombre,"
            + " el valor de un parámetro enviado en la petición.\n"
            + "Por ejemplo, si enviamos “man” devolverá “Spiderman”,"
            + " “Superman”,“Manolito el fuerte”, etc")
    public ResponseEntity<List<Superheroe>> findByName(@RequestParam String name) {
        List<Superheroe> list = service.findByName(name);
        if (list != null && !list.isEmpty()) {
            return ResponseEntity.ok(list);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/", produces = "application/json" )
    @ApiOperation(value = "Añadir un súper héroe.")
    public ResponseEntity<Superheroe> create(Superheroe heroe) {
        if (service.create(heroe)) {
            return new ResponseEntity(heroe, HttpStatus.CREATED);
        }
        throw new BusinessException("No se ha podido añadir el héroe");
    }

    @PutMapping(value = "/", produces = "application/json" )
    @ApiOperation(value = "Modificar un súper héroe.")
    public ResponseEntity<Superheroe> update(Superheroe heroe) {
        if (service.update(heroe)) {
            return ResponseEntity.ok(heroe);
        }
        throw new BusinessException("No se ha podido modificar el héroe");
    }

    @DeleteMapping(value = "/")
    @ApiOperation(value = "Eliminar un súper héroe.")
    public ResponseEntity<?> delete(@RequestParam long heroeId) {
        if (service.delete(heroeId)) {
            return ResponseEntity.ok().build();
        }
        throw new BusinessException("No se ha podido eliminar el héroe");
    }
}