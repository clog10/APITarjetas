package com.ibm.academia.restapi.tarjetas.controladores;

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
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.tarjetas.excepciones.NotFoundException;
import com.ibm.academia.restapi.tarjetas.modelo.entidades.Passion;
import com.ibm.academia.restapi.tarjetas.servicios.PassionDAO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/restapi")
public class PassionController {
	@Autowired
	private PassionDAO passionDao;

	/**
	 * Endpoint para consultar todas las passions
	 * 
	 * @return Retorna una lista de passions
	 * @exception NotFoundException En caso de que falle buscando las passions o no
	 *                              existan
	 * @author CJGL - 20-02-2022
	 */
	@ApiOperation(value = "Consultar todas las passions")
	@ApiResponses({ @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
			@ApiResponse(code = 404, message = "No hay elementos en la bd") })
	@GetMapping("/passions/lista")
	public ResponseEntity<?> listarTodas() {
		List<Passion> passions = (List<Passion>) passionDao.buscarTodos();

		if (passions.isEmpty())
			throw new NotFoundException("No existen passions en la base de datos");

		return new ResponseEntity<List<Passion>>(passions, HttpStatus.OK);
	}

	/**
	 * Enpoint para consultar una passion por id
	 * 
	 * @param tarjetaId
	 * @return Retorna un objeto de tipo passion
	 * @exception NotFoundException En caso de que falle buscando la passion o no
	 *                              exista
	 * @author CJGL - 20-02-2022
	 */
	@GetMapping("/passion/passionId/{passionId}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long passionId) {
		Optional<Passion> oPassion = passionDao.buscarPorId(passionId);

		if (!oPassion.isPresent())
			throw new NotFoundException(String.format("La Passion con id: %d no existe", passionId));

		return new ResponseEntity<Passion>(oPassion.get(), HttpStatus.OK);
	}
	
	/**
	 * Endpoint para guardar una Passion
	 * 
	 * @param passion
	 * @param result
	 * @return Retorna la Passion Guardada
	 * @author CJGL - 20-02-2022
	 */
	@PostMapping("/passion")
	public ResponseEntity<?> guardar(@Valid @RequestBody Passion passion, BindingResult result) {

		Map<String, Object> validaciones = new HashMap<String, Object>();
		if (result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors().stream()
					.map(errores -> "Campo: " + errores.getField() + " " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}

		Passion passionGuardada = passionDao.guardar(passion);
		return new ResponseEntity<Passion>(passionGuardada, HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint para eliminar una passion por id
	 * 
	 * @param passionId
	 * @return Solo retorna el estatus
	 * @exception NotFoundException En caso de que falle buscando la passion o no
	 *                              exista
	 * @author CJGL - 20-02-2022
	 */
	@DeleteMapping("/passion/eliminar/passionId/{passionId}")
	public ResponseEntity<?> eliminar(@PathVariable Long passionId) {

		Optional<Passion> oPassion = passionDao.buscarPorId(passionId);

		if (!oPassion.isPresent())
			throw new NotFoundException(String.format("La tarjeta con id: %d no existe", passionId));

		passionDao.eliminarPorId(passionId);
		return new ResponseEntity<>("La passion con id: " + passionId + " fué eliminada", HttpStatus.NO_CONTENT);
	}

}
