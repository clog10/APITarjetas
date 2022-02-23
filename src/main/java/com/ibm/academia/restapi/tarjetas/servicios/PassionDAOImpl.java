package com.ibm.academia.restapi.tarjetas.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.tarjetas.modelo.entidades.Passion;
import com.ibm.academia.restapi.tarjetas.repositorios.PassionRepository;

@Service
public class PassionDAOImpl implements PassionDAO{
	private PassionRepository passionRepository;

	@Autowired
	public PassionDAOImpl(PassionRepository passionRepository) {
		this.passionRepository = passionRepository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Passion> buscarPorId(Long id) {
		return passionRepository.findById(id);
	}

	@Override
	@Transactional
	public Passion guardar(Passion passion) {
		return passionRepository.save(passion);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Passion> buscarTodos() {
		return passionRepository.findAll();
	}

	@Override
	@Transactional
	public void eliminarPorId(Long id) {
		passionRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Iterable<Passion> guardarVarios(Iterable<Passion> passions) {
		return passionRepository.saveAll(passions);
	}

	@Override
	public Iterable<Passion> findPassionsByCliente(Long passionId) {
		return passionRepository.findPassionsByCliente(passionId);
	}

}
