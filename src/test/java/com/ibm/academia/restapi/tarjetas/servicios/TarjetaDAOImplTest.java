package com.ibm.academia.restapi.tarjetas.servicios;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.restapi.tarjetas.repositorios.TarjetaRepository;

@SpringBootTest
public class TarjetaDAOImplTest {

	@Autowired
	private TarjetaDAO tarjetaDao;
	
	@MockBean
	private TarjetaRepository tarjetaRepository;
	
	@Test
	@DisplayName("Test: Buscar tarjetas por id del cliente")
	void findTarjetasByClienteId() {

		tarjetaDao.findTarjetasByClienteId(anyLong());
		
		verify(tarjetaRepository).findTarjetasByClienteId(anyLong());
	}
}
