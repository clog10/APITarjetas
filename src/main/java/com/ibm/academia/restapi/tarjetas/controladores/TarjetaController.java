package com.ibm.academia.restapi.tarjetas.controladores;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.tarjetas.enumeradores.TipoPassion;
import com.ibm.academia.restapi.tarjetas.excepciones.NotFoundException;
import com.ibm.academia.restapi.tarjetas.modelo.entidades.Tarjeta;
import com.ibm.academia.restapi.tarjetas.servicios.TarjetaDAO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/restapi")
public class TarjetaController {

	@Autowired
	private TarjetaDAO tarjetaDao;

	/**
	 * Endpoint que retorna el tipo de tarjeta disponible dependiendo de los parametros ingresados
	 * @param passion
	 * @param salario
	 * @param edad
	 * @return Retorna en un mensaje los tipos de tarjetas disponibles
	 */
	@GetMapping("/obtener-tipo-tarjeta")
	public ResponseEntity<?> obtenerTipoTarjeta(@RequestParam TipoPassion passion, @RequestParam BigDecimal salario,
			@RequestParam Integer edad) {
		String tipoTarjeta = tarjetaDao.obtenerTipoTarjeta(passion, salario, edad);
		
		return new ResponseEntity<String>(tipoTarjeta, HttpStatus.OK);
	}
	
	
	/**
	 * Endpoint para consultar todas las tarjetas
	 * 
	 * @return Retorna una lista de tarjetas
	 * @exception NotFoundException En caso de que falle buscando las tarjetas o no
	 *                              existan
	 * @author CJGL - 20-02-2022
	 */
	@ApiOperation(value = "Consultar todas las tarjetas")
	@ApiResponses({ @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
			@ApiResponse(code = 404, message = "No hay elementos en la bd") })
	@GetMapping("/tarjetas/lista")
	public ResponseEntity<?> listarTodas() {
		List<Tarjeta> tarjetas = (List<Tarjeta>) tarjetaDao.buscarTodos();

		if (tarjetas.isEmpty())
			throw new NotFoundException("No existen tarjetas en la base de datos");

		return new ResponseEntity<List<Tarjeta>>(tarjetas, HttpStatus.OK);
	}

	/**
	 * Enpoint para consultar una tarjeta por id
	 * 
	 * @param tarjetaId
	 * @return Retorna un objeto de tipo tarjeta
	 * @exception NotFoundException En caso de que falle buscando la tarjeta o no
	 *                              exista
	 * @author CJGL - 20-02-2022
	 */
	@GetMapping("/tarjeta/tarjetaId/{tarjetaId}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long tarjetaId) {
		Optional<Tarjeta> oTarjeta = tarjetaDao.buscarPorId(tarjetaId);

		if (!oTarjeta.isPresent())
			throw new NotFoundException(String.format("La tarjeta con id: %d no existe", tarjetaId));

		return new ResponseEntity<Tarjeta>(oTarjeta.get(), HttpStatus.OK);
	}
	
	/**
	 * Endpoint para guardar una tarjeta
	 * 
	 * @param tarjeta
	 * @param result
	 * @return Retorna la tarjeta Guardada
	 * @author CJGL - 20-02-2022
	 */
	@PostMapping("/tarjeta")
	public ResponseEntity<?> guardar(@Valid @RequestBody Tarjeta tarjeta, BindingResult result) {

		Map<String, Object> validaciones = new HashMap<String, Object>();
		if (result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors().stream()
					.map(errores -> "Campo: " + errores.getField() + " " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}

		Tarjeta tarjetaGuardada = tarjetaDao.guardar(tarjeta);
		return new ResponseEntity<Tarjeta>(tarjetaGuardada, HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint para eliminar una tarjeta por id
	 * 
	 * @param tarjetaId
	 * @return Solo retorna el estatus
	 * @exception NotFoundException En caso de que falle buscando la tarjeta o no
	 *                              exista
	 * @author CJGL - 20-02-2022
	 */
	@DeleteMapping("/tarjeta/eliminar/tarjetaId/{tarjetaId}")
	public ResponseEntity<?> eliminar(@PathVariable Long tarjetaId) {

		Optional<Tarjeta> oTarjeta = tarjetaDao.buscarPorId(tarjetaId);

		if (!oTarjeta.isPresent())
			throw new NotFoundException(String.format("La tarjeta con id: %d no existe", tarjetaId));

		tarjetaDao.eliminarPorId(tarjetaId);
		return new ResponseEntity<>("La tarjeta con id: " + tarjetaId + " fué eliminada", HttpStatus.NO_CONTENT);
	}

}
