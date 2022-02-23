package com.ibm.academia.restapi.tarjetas.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.tarjetas.datos.DatosDummy;
import com.ibm.academia.restapi.tarjetas.modelo.entidades.Cliente;
import com.ibm.academia.restapi.tarjetas.modelo.entidades.Tarjeta;

@DataJpaTest
public class TarjetaRepositoryTest {
	
	@Autowired
	private TarjetaRepository tarjetaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@BeforeEach
	void setUp() {
		tarjetaRepository.save(DatosDummy.tarjeta01());
		tarjetaRepository.save(DatosDummy.tarjeta02());
		tarjetaRepository.save(DatosDummy.tarjeta03());
	}

	@AfterEach
	void tearDown() {
		tarjetaRepository.deleteAll();
	}
	
	@Test
	@DisplayName("Test: Buscar Tarjetas por el id del cliente")
	void findTarjetasByClienteId() {
		Cliente cliente01 = clienteRepository.save(DatosDummy.cliente01());
		
		List<Tarjeta> tarjetas =  (List<Tarjeta>) tarjetaRepository.findAll();
		
		Set<Tarjeta> tarjetasAsociadas = new HashSet<Tarjeta>();
		
		tarjetas.forEach(tarjeta->{
			tarjetasAsociadas.add(tarjeta);
			tarjeta.setCliente(cliente01);
			tarjetaRepository.save(tarjeta);
		});
		
		cliente01.setTarjetas(tarjetasAsociadas);
		
		clienteRepository.save(cliente01);
		
		List<Tarjeta> expected = (List<Tarjeta>) tarjetaRepository.findTarjetasByClienteId(cliente01.getId());

		assertThat(expected.size() == 3).isTrue();
 	}
	
}
