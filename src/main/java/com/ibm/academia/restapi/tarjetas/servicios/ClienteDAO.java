package com.ibm.academia.restapi.tarjetas.servicios;

import java.util.Optional;

import com.ibm.academia.restapi.tarjetas.modelo.entidades.Cliente;

public interface ClienteDAO {
	
	public Optional<Cliente> buscarPorId(Long id);

	public Cliente guardar(Cliente entidad);

	public Iterable<Cliente> buscarTodos();

	public void eliminarPorId(Long id);

	public Iterable<Cliente> guardarVarios(Iterable<Cliente> entities);

	public Cliente actualizar(Long clienteId, Cliente cliente);

	public Cliente asociarPassion(Long clienteId, Long passionId);

	public Cliente asociarTarjeta(Long clienteId, Long tarjetaId);

}
