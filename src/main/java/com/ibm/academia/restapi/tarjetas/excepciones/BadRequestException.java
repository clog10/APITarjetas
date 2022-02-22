package com.ibm.academia.restapi.tarjetas.excepciones;


public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -3330560048137569028L;
	
	public BadRequestException(String mensaje) {
		super(mensaje);
	}

}
