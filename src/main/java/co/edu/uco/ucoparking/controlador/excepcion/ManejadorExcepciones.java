package co.edu.uco.ucoparking.controlador.excepcion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import co.edu.uco.ucoparking.controlador.respuesta.RespuestaError;
import co.edu.uco.ucoparking.transversal.utilitario.excepcion.UcoParkingExcepcion;

@RestControllerAdvice
public class ManejadorExcepciones {
	
	private static final  Logger logger = LoggerFactory.getLogger(ManejadorExcepciones.class);

	@ExceptionHandler(UcoParkingExcepcion.class)
	public ResponseEntity<RespuestaError> gestionarUcoParkingExcepcion(final UcoParkingExcepcion excepcion) {
		logger.error(excepcion.getMensajeTecnico(), excepcion.getExcepcionRaiz());

		return new ResponseEntity<>(RespuestaError.crear(excepcion.getMensajeUsuario()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<RespuestaError> gestionarExcepcion(final Exception excepcion) {
		
		var mensajeUsuario = "Se ha presentado un problema no esperado. Por favor intente de nuevo. Si el problema persiste, contacte al administrador de la aplicacion.";
		var mensajeTecnico = "Se presento un flujo no controlado dentro de la aplicacion en el controlador, revisar el log para mas detalles.";
		logger.error(mensajeTecnico, excepcion);

		return new ResponseEntity<>(
				RespuestaError.crear(mensajeUsuario),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
