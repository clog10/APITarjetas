package com.ibm.academia.restapi.tarjetas.servicios;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.restapi.tarjetas.repositorios.PassionRepository;

@SpringBootTest
public class PassionDAOImplTest {
	
	@Autowired
	private PassionDAO passionDao;
	
	@MockBean
	private PassionRepository passionRepository;
	
	@Test
	@DisplayName("Test: Buscar passions por id del cliente")
	void findPassionsByCliente() {

		passionDao.findPassionsByCliente(anyLong());
		
		verify(passionRepository).findPassionsByCliente(anyLong());
	}

}
