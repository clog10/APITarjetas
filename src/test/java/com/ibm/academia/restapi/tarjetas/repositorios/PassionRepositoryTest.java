package com.ibm.academia.restapi.tarjetas.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.tarjetas.datos.DatosDummy;
import com.ibm.academia.restapi.tarjetas.modelo.entidades.Cliente;
import com.ibm.academia.restapi.tarjetas.modelo.entidades.Passion;

@DataJpaTest
public class PassionRepositoryTest {
	
	@Autowired
	private PassionRepository passionRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@BeforeEach
	void setUp() {
		passionRepository.save(DatosDummy.passion01());
		passionRepository.save(DatosDummy.passion02());
		passionRepository.save(DatosDummy.passion03());
	}

	@AfterEach
	void tearDown() {
		passionRepository.deleteAll();
	}
	
	@Test
	@DisplayName("Test: Buscar Passions por el id del cliente")
	void findPassionsByCliente() {
		Optional<Passion> passion = passionRepository.findById(2L);
		
		Cliente cliente01 = clienteRepository.save(DatosDummy.cliente01());

		Set<Passion> passions = new HashSet<Passion>();
		passions.add(passion.get());
		
		cliente01.setPassions(passions);
		
		Set<Cliente> clientesAsociados= new HashSet<Cliente>();
		clientesAsociados.add(cliente01);

		passion.get().setClientes(clientesAsociados);
		
		clienteRepository.save(cliente01);
		passionRepository.save(passion.get());
		
		List<Passion> expected = (List<Passion>) passionRepository.findPassionsByCliente(cliente01.getId());

		assertThat(expected.size() == 1).isTrue();
		
	}

}
