package com.ibm.academia.restapi.tarjetas.servicios;

import com.ibm.academia.restapi.tarjetas.modelo.entidades.Cliente;

public interface ClienteDAO extends GenericoDAO<Cliente> {

	public Cliente actualizar(Long clienteId, Cliente cliente);

	public Cliente asociarPassion(Long clienteId, Long passionId);

	public Cliente asociarTarjeta(Long clienteId, Long tarjetaId);

}
