package com.ibm.academia.restapi.tarjetas.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.ibm.academia.restapi.tarjetas.modelo.entidades.Tarjeta;

public interface TarjetaRepository extends CrudRepository<Tarjeta, Long> {

}
