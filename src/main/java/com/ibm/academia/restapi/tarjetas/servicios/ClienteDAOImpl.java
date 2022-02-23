package com.ibm.academia.restapi.tarjetas.servicios;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.tarjetas.excepciones.NotFoundException;
import com.ibm.academia.restapi.tarjetas.modelo.entidades.Cliente;
import com.ibm.academia.restapi.tarjetas.modelo.entidades.Passion;
import com.ibm.academia.restapi.tarjetas.modelo.entidades.Tarjeta;
import com.ibm.academia.restapi.tarjetas.repositorios.ClienteRepository;

@Service
public class ClienteDAOImpl implements ClienteDAO {

	private final ClienteRepository clienteRepository;

	@Autowired
	private TarjetaDAO tarjetaDao;

	@Autowired
	private PassionDAO passionDao;

	@Autowired
	public ClienteDAOImpl(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Cliente> buscarPorId(Long id) {
		return clienteRepository.findById(id);
	}

	@Override
	@Transactional
	public Cliente guardar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	@Transactional
	public void eliminarPorId(Long id) {
		clienteRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Iterable<Cliente> guardarVarios(Iterable<Cliente> clientes) {
		return clienteRepository.saveAll(clientes);
	}

	@Override
	@Transactional
	public Cliente actualizar(Long clienteId, Cliente cliente) {
		Optional<Cliente> oCliente = clienteRepository.findById(clienteId);

		if (!oCliente.isPresent())
			throw new NotFoundException(String.format("El cliente con id: %d no existe", clienteId));

		Cliente clienteActualizado = null;

		oCliente.get().setNombre(cliente.getNombre());
		oCliente.get().setApellido(cliente.getApellido());
		oCliente.get().setSueldoMensual(cliente.getSueldoMensual());
		clienteActualizado = clienteRepository.save(oCliente.get());

		return clienteActualizado;
	}

	@Override
	@Transactional
	public Cliente asociarPassion(Long clienteId, Long passionId) {
		Optional<Cliente> oCliente = clienteRepository.findById(clienteId);

		if (!oCliente.isPresent())
			throw new NotFoundException(String.format("El cliente con id: %d no existe", clienteId));

		Optional<Passion> oPassion = passionDao.buscarPorId(passionId);

		if (!oPassion.isPresent())
			throw new NotFoundException(String.format("La passion con id: %d no existe", passionId));

		Set<Passion> passions = new HashSet<Passion>();
		Set<Cliente> clientes = new HashSet<Cliente>();

		List<Passion> passionsAsociadas = (List<Passion>) passionDao.findPassionsByCliente(clienteId);

		if (!passionsAsociadas.isEmpty()) {
			passionsAsociadas.forEach(passion -> {
				passions.add(passion);
			});
		}

		passions.add(oPassion.get());
		clientes.add(oCliente.get());

		oPassion.get().setClientes(clientes);
		oCliente.get().setPassions(passions);

		passionDao.guardar(oPassion.get());

		return clienteRepository.save(oCliente.get());
	}

	@Override
	@Transactional
	public Cliente asociarTarjeta(Long clienteId, Long tarjetaId) {
		Optional<Cliente> oCliente = clienteRepository.findById(clienteId);

		if (!oCliente.isPresent())
			throw new NotFoundException(String.format("El cliente con id: %d no existe", clienteId));

		Optional<Tarjeta> oTarjeta = tarjetaDao.buscarPorId(tarjetaId);

		if (!oTarjeta.isPresent())
			throw new NotFoundException(String.format("La tarjeta con id: %d no existe", tarjetaId));

		List<Tarjeta> tarjetasAsociadas = (List<Tarjeta>) tarjetaDao.findTarjetasByClienteId(oCliente.get().getId());

		Set<Tarjeta> tarjetasCliente = new HashSet<Tarjeta>();

		if (!tarjetasAsociadas.isEmpty()) {
			tarjetasAsociadas.forEach(tarjeta -> {
				tarjetasCliente.add(tarjeta);
			});
		}

		tarjetasCliente.add(oTarjeta.get());

		oCliente.get().setTarjetas(tarjetasCliente);

		oTarjeta.get().setCliente(oCliente.get());
		tarjetaDao.guardar(oTarjeta.get());

		return clienteRepository.save(oCliente.get());
	}

}
