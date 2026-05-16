package co.edu.uco.ucoparking.controlador.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import co.edu.uco.ucoparking.controlador.respuesta.RespuestaError;
import co.edu.uco.ucoparking.transversal.utilitario.excepcion.UcoParkingExcepcion;

@RestControllerAdvice
public class ManejadorExcepciones {

	@ExceptionHandler(UcoParkingExcepcion.class)
	public ResponseEntity<RespuestaError> gestionarUcoParkingExcepcion(UcoParkingExcepcion excepcion) {
		System.err.println(excepcion.getMensajeTecnico());
		excepcion.getExcepcionRaiz().printStackTrace();

		return new ResponseEntity<>(RespuestaError.crear(excepcion.getMensajeUsuario()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<RespuestaError> gestionarExcepcion(Exception excepcion) {
		System.err.println("Excepcion no controlada.....");
		excepcion.printStackTrace();

		return new ResponseEntity<>(
				RespuestaError.crear("Se ha presentado un problema no esperado. Por favor intente de nuevo. Si el problema persiste, contacte al administrador de la aplicacion."),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
