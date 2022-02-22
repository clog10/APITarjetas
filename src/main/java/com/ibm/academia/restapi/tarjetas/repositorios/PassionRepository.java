package com.ibm.academia.restapi.tarjetas.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.tarjetas.modelo.entidades.Passion;

@Repository
public interface PassionRepository extends CrudRepository<Passion, Long> {

	@Query("select p from Passion p join fetch p.clientes c where c.id=?1")
	public Iterable<Passion> findPassionsByCliente(Long clienteId);
}
