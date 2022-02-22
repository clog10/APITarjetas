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
import com.ibm.academia.restapi.tarjetas.modelo.entidades.Tarjeta;
import com.ibm.academia.restapi.tarjetas.repositorios.ClienteRepository;

@Service
public class ClienteDAOImpl extends GenericoDAOImpl<Cliente, ClienteRepository> implements ClienteDAO{
	
	@Autowired
	private TarjetaDAO tarjetaDao;

	@Autowired
	public ClienteDAOImpl(ClienteRepository repository) {
		super(repository);
	}

	@Override
	@Transactional
	public Cliente actualizar(Long clienteId, Cliente cliente) {
		Optional<Cliente> oCliente = repository.findById(clienteId);

		if (!oCliente.isPresent())
			throw new NotFoundException(String.format("El cliente con id: %d no existe", clienteId));

		Cliente clienteActualizado = null;

		oCliente.get().setNombre(cliente.getNombre());
		oCliente.get().setApellido(cliente.getApellido());
		oCliente.get().setSueldoMensual(cliente.getSueldoMensual());
		clienteActualizado = repository.save(oCliente.get());

		return clienteActualizado;
	}

	@Override
	@Transactional
	public Cliente asociarPassion(Long clienteId, Long passionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Cliente asociarTarjeta(Long clienteId, Long tarjetaId) {
		Optional<Cliente> oCliente = repository.findById(clienteId);

		if (!oCliente.isPresent())
			throw new NotFoundException(String.format("El cliente con id: %d no existe", clienteId));

		Optional<Tarjeta> oTarjeta = tarjetaDao.buscarPorId(tarjetaId);

		if (!oTarjeta.isPresent())
			throw new NotFoundException(String.format("La tarjeta con id: %d no existe", tarjetaId));
		
		List<Tarjeta> tarjetasAsociadas = (List<Tarjeta>) tarjetaDao.findTarjetasByClienteId(oCliente.get().getId());
		
		Set<Tarjeta> tarjetasCliente = new HashSet<Tarjeta>();
		
		if(!tarjetasAsociadas.isEmpty()) {
			tarjetasAsociadas.forEach(tarjeta->{
				tarjetasCliente.add(tarjeta);
			});	
		}
		
		tarjetasCliente.add(oTarjeta.get());
		
		oCliente.get().setTarjetas(tarjetasCliente);
		
		oTarjeta.get().setCliente(oCliente.get());
		tarjetaDao.guardar(oTarjeta.get());
		
		return repository.save(oCliente.get());
	}

}
