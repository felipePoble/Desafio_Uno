package com.fjpp.desafio.controller;

import com.fjpp.desafio.model.DesafioResponse;
import com.fjpp.desafio.service.MainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private MainService servicio;

    /**
     * Método principal utilizado para hacer la llamada al servicio, para que este último se encargue de calcular
     * las fechas faltantes y devolverle el objeto esperado listo al controlador.
     *
     * @return El objeto DesafioResponse listo obtenido desde el servicio.
     */
    @GetMapping("/desafio/api")
    public ResponseEntity<DesafioResponse> desafio() {
        return servicio.getResponseFromGdd();
    }

}
