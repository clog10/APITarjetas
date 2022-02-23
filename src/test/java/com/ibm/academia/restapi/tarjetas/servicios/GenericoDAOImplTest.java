package com.ibm.academia.restapi.tarjetas.servicios;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.restapi.tarjetas.modelo.entidades.Passion;
import com.ibm.academia.restapi.tarjetas.repositorios.PassionRepository;

@SpringBootTest
public class GenericoDAOImplTest {

	@Autowired
	private GenericoDAO<Passion> genericoDao;
	
	@MockBean
	private PassionRepository passionRepository;
	
	@Test
	@DisplayName("Test: Buscar por ID")
	void buscarPorId() {
		genericoDao.buscarPorId(anyLong());

		verify(passionRepository).findById(anyLong());
	}
	
	@Test
	@DisplayName("Test: Guardar")
	void guardar() {

		genericoDao.guardar(any());

		verify(passionRepository).save(any());
	}

	@Test
	@DisplayName("Test: Buscar todos los elementos")
	void buscarTodos() {
		genericoDao.buscarTodos();

		verify(passionRepository).findAll();
	}
	
	@Test
	@DisplayName("Test: Guardar Varios")
	void guardarVarios() {
		genericoDao.guardarVarios(anyList());

		verify(passionRepository).saveAll(anyList());

	}
}
