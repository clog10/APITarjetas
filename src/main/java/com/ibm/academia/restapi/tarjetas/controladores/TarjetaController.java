package com.ibm.academia.restapi.tarjetas.controladores;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.tarjetas.enumeradores.TipoPassion;
import com.ibm.academia.restapi.tarjetas.servicios.TarjetaDAO;

@RestController
@RequestMapping("/restapi")
public class TarjetaController {

	private final static Logger logger = LoggerFactory.getLogger(TarjetaController.class);

	@Autowired
	private TarjetaDAO tarjetaDao;

	@GetMapping("/obtener-tipo-tarjeta")
	public ResponseEntity<?> obtenerTipoTarjeta(@RequestParam TipoPassion passion, @RequestParam BigDecimal salario,
			@RequestParam Integer edad) {
		String tipoTarjeta = tarjetaDao.obtenerTipoTarjeta(passion, salario, edad);
		
		return new ResponseEntity<String>(tipoTarjeta, HttpStatus.OK);
	}

}
