package co.edu.uco.ucoparking.transversal.excepcion;

public class UCOParkingRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UCOParkingRuntimeException(final String mensaje) {
		super(mensaje);
	}

	public UCOParkingRuntimeException(final String mensaje, final Throwable causa) {
		super(mensaje, causa);
	}

}
