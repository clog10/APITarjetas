package com.ibm.academia.restapi.tarjetas.servicios;

import java.math.BigDecimal;

import com.ibm.academia.restapi.tarjetas.enumeradores.TipoPassion;
import com.ibm.academia.restapi.tarjetas.modelo.entidades.Tarjeta;

public interface TarjetaDAO extends GenericoDAO<Tarjeta>{
	
	public Iterable<Tarjeta> findTarjetasByClienteId(Long clienteId);

	public String obtenerTipoTarjeta(TipoPassion passion, BigDecimal salario, Integer edad);
	
}
