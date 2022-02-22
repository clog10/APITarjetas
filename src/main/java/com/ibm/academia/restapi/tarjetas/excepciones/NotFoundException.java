package com.ibm.academia.restapi.tarjetas.excepciones;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6407196044322865851L;
	
	public NotFoundException(String mensaje) {
		super(mensaje);
	}

}
