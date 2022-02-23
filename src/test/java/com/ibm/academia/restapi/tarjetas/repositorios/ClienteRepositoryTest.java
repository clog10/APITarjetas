package com.ibm.academia.restapi.tarjetas.repositorios;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.tarjetas.datos.DatosDummy;


@DataJpaTest
public class ClienteRepositoryTest {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@BeforeEach
	void setUp() {
		clienteRepository.save(DatosDummy.cliente01());
		clienteRepository.save(DatosDummy.cliente02());
		clienteRepository.save(DatosDummy.cliente03());
	}

	@AfterEach
	void tearDown() {
		clienteRepository.deleteAll();
	}

}
