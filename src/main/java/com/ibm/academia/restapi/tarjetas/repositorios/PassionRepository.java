package com.ibm.academia.restapi.tarjetas.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.ibm.academia.restapi.tarjetas.modelo.entidades.Passion;

public interface PassionRepository extends CrudRepository<Passion, Long> {

}
