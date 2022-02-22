package com.ibm.academia.restapi.tarjetas.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.academia.restapi.tarjetas.modelo.entidades.Passion;
import com.ibm.academia.restapi.tarjetas.repositorios.PassionRepository;

@Service
public class PassionDAOImpl extends GenericoDAOImpl<Passion, PassionRepository> implements PassionDAO{

	@Autowired
	public PassionDAOImpl(PassionRepository repository) {
		super(repository);
	}

	@Override
	public Iterable<Passion> findPassionsByCliente(Long clienteId) {
		return repository.findPassionsByCliente(clienteId);
	}

}
