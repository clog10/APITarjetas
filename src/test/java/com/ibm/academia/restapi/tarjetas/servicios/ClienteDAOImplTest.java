package com.ibm.academia.restapi.tarjetas.servicios;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.restapi.tarjetas.repositorios.ClienteRepository;

@SpringBootTest
public class ClienteDAOImplTest {
	
	@Autowired
	private ClienteDAO clienteDao;
	
	@MockBean
	private ClienteRepository clienteRepository;

}
