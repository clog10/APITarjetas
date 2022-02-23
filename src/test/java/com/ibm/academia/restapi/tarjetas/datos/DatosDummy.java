package com.ibm.academia.restapi.tarjetas.datos;

import java.math.BigDecimal;

import com.ibm.academia.restapi.tarjetas.enumeradores.TipoPassion;
import com.ibm.academia.restapi.tarjetas.modelo.entidades.Cliente;
import com.ibm.academia.restapi.tarjetas.modelo.entidades.Passion;
import com.ibm.academia.restapi.tarjetas.modelo.entidades.Tarjeta;

public class DatosDummy {

	public static Cliente cliente01() {
		return new Cliente(null, "Carlos", "Loaeza", "522217", 23, new BigDecimal(25000.00), "Clog10");
	}
	
	public static Cliente cliente02() {
		return new Cliente(null, "Valeria", "Zarate", "52287217", 25, new BigDecimal(55000.00), "Clog10");
	}
	
	public static Cliente cliente03() {
		return new Cliente(null, "Maria", "Loaeza", "722217", 18, new BigDecimal(15000.00), "Clog10");
	}
	
	public static Tarjeta tarjeta01() {
		return new Tarjeta(null, "Platinum", "Clog10");
	}
	
	public static Tarjeta tarjeta02() {
		return new Tarjeta(null, "Oro", "Clog10");
	}
	
	public static Tarjeta tarjeta03() {
		return new Tarjeta(null, "B+Smart", "Clog10");
	}
	
	public static Passion passion01() {
		return new Passion(null, TipoPassion.SHOPPING, "Clog10");
	}
	
	public static Passion passion02() {
		return new Passion(null, TipoPassion.TRAVELS, "Clog10");
	}
	
	public static Passion passion03() {
		return new Passion(null, TipoPassion.BUSINESS, "Clog10");
	}
}
