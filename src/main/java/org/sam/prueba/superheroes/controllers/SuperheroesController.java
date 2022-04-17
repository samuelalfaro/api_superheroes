package org.sam.prueba.superheroes.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.sam.prueba.superheroes.config.SwaggerConfig;
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


    @GetMapping(value = "/", produces = "application/json" )
    @ApiOperation(value = "Consultar todos los súper héroes.")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{heroeId}", produces = "application/json")
    @ApiOperation(value = "Consultar un único súper héroe por id.")
    public ResponseEntity<?> find(@PathVariable("heroeId") long heroeId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/byname", produces = "application/json" )
    @ApiOperation(value
            = "Consultar todos los súper héroes que contienen, en su nombre,"
            + " el valor de un parámetro enviado en la petición.\n"
            + "Por ejemplo, si enviamos “man” devolverá “Spiderman”,"
            + " “Superman”,“Manolito el fuerte”, etc")
    public ResponseEntity<?> findByName(@RequestParam String name) {
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/", produces = "application/json" )
    @ApiOperation(value = "Añadir un súper héroe.")
     public ResponseEntity<?> create(Object heroe) {
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/", produces = "application/json" )
    @ApiOperation(value = "Modificar un súper héroe.")
    public ResponseEntity<?> update(Object heroe) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/")
    @ApiOperation(value = "Eliminar un súper héroe.")
    public ResponseEntity<?> delete(@RequestParam long heroeId) {
        return ResponseEntity.ok().build();
    }
}
