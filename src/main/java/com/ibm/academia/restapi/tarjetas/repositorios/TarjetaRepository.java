package com.ibm.academia.restapi.tarjetas.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.tarjetas.modelo.entidades.Tarjeta;

@Repository
public interface TarjetaRepository extends CrudRepository<Tarjeta, Long> {

	public Iterable<Tarjeta> findTarjetasByClienteId(Long clienteId);
	
}
