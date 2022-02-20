package com.ibm.academia.restapi.tarjetas.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.tarjetas.modelo.entidades.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
