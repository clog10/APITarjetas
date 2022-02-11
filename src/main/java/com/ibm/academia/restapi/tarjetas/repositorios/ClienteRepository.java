package com.ibm.academia.restapi.tarjetas.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.ibm.academia.restapi.tarjetas.modelo.entidades.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
