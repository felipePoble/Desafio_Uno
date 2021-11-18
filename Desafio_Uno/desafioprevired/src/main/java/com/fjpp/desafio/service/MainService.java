package com.fjpp.desafio.service;

import org.springframework.http.ResponseEntity;

import com.fjpp.desafio.model.DesafioResponse;

public interface MainService {

    public ResponseEntity<DesafioResponse> getResponseFromGdd();

}
