package com.ibm.academia.restapi.tarjetas.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.tarjetas.excepciones.NotFoundException;
import com.ibm.academia.restapi.tarjetas.modelo.entidades.Cliente;
import com.ibm.academia.restapi.tarjetas.servicios.ClienteDAO;

@RestController
@RequestMapping("/restapi")
public class ClienteController {

	private final static Logger logger = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private ClienteDAO clienteDao;

	/**
	 * Enpoint para guardar un cliente
	 * 
	 * @param alumno
	 * @return Retorna el cliente guardado
	 * @author CJGL - 20-02-2022
	 */
	@PostMapping("/cliente")
	public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente) {
		Cliente clienteGuardado = clienteDao.guardar(cliente);
		return new ResponseEntity<Cliente>(clienteGuardado, HttpStatus.CREATED);
	}

	/**
	 * Endpoint para buscar todos los clientes
	 * 
	 * @return Retorna una lista con los clientes
	 * @exception NotFoundException En caso de que no existan clientes
	 * @author CJGL - 20-02-2022
	 */
	@GetMapping("/clientes/lista")
	public ResponseEntity<?> obtenerTodos() {
		List<Cliente> clientes = (List<Cliente>) clienteDao.buscarTodos();
		if (clientes.isEmpty())
			throw new NotFoundException("No existen clientes");

		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}

	/**
	 * Endpoint para buscar un cliente por id
	 * 
	 * @param clienteId
	 * @return Retorna el cliente encontrado
	 * @exception NotFoundException En caso de que no exista cliente con ese id
	 * @author CJGL - 20-02-2022
	 */
	@GetMapping("/cliente/clienteId/{clienteId}")
	public ResponseEntity<?> obtenerClientePorId(@PathVariable Long clienteId) {
		Optional<Cliente> oCliente = clienteDao.buscarPorId(clienteId);
		if (!oCliente.isPresent())
			throw new NotFoundException(String.format("El cliente con id: %d no existe", clienteId));
		return new ResponseEntity<Cliente>(oCliente.get(), HttpStatus.OK);
	}

	/**
	 * Endpoint para eliminar un cliente por su id
	 * 
	 * @param clienteId
	 * @return @return Solo retorna el estatus
	 * @exception NotFoundException En caso de que falle buscando el cliente o no
	 *                              exista
	 * @author CJGL - 20-02-2022
	 */
	@DeleteMapping("/cliente/eliminar/clienteId/{clienteId}")
	public ResponseEntity<?> eliminarCliente(@PathVariable Long clienteId) {
		Optional<Cliente> oCliente = clienteDao.buscarPorId(clienteId);
		if (!oCliente.isPresent())
			throw new NotFoundException(String.format("El cliente con id: %d no existe", clienteId));

		clienteDao.eliminarPorId(oCliente.get().getId());
		return new ResponseEntity<String>("El cliente con id: " + clienteId + " se eliminó", HttpStatus.NO_CONTENT);
	}

	/**
	 * Endpoint para actualizar un cliente
	 * 
	 * @param clienteId
	 * @param cliente
	 * @param result
	 * @return Retorna el cliente actualizado
	 * @author CJGL - 16-02-2022
	 */
	@PutMapping("/cliente/actualizar/clienteId/{clienteId}")
	public ResponseEntity<?> actualizarCliente(@PathVariable Long clienteId, @RequestBody Cliente cliente,
			BindingResult result) {
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if (result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors().stream()
					.map(errores -> "Campo: " + errores.getField() + " " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}

		Cliente clienteActualizado = null;

		try {
			clienteActualizado = clienteDao.actualizar(clienteId, cliente);
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw e;
		}

		return new ResponseEntity<Cliente>(clienteActualizado, HttpStatus.OK);
	}

	/**
	 * Endpoint para asociar un clienta con una tarjeta
	 * 
	 * @param clienteId
	 * @param tarjetaId
	 * @return Retorna el cliente con su asociación
	 * @author CJGL - 20-02-2022
	 */
	@PutMapping("/cliente/asociar-tarjeta")
	public ResponseEntity<?> asociarTarjeta(@RequestParam Long clienteId, @RequestParam Long tarjetaId) {
		Cliente cliente = clienteDao.asociarTarjeta(clienteId, tarjetaId);
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

}
