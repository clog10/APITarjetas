package com.ibm.academia.restapi.tarjetas.servicios;

import java.util.Optional;

import com.ibm.academia.restapi.tarjetas.modelo.entidades.Passion;

public interface PassionDAO{
	
	public Optional<Passion> buscarPorId(Long id);

	public Passion guardar(Passion passion);

	public Iterable<Passion> buscarTodos();

	public void eliminarPorId(Long id);

	public Iterable<Passion> guardarVarios(Iterable<Passion> entities);

	public Iterable<Passion> findPassionsByCliente(Long clienteId);
	
}
