package co.edu.uco.ucoparking.transversal.excepcion;

public class UCOParkingException extends Exception {

	private static final long serialVersionUID = 1L;

	public UCOParkingException(final String mensaje) {
		super(mensaje);
	}

	public UCOParkingException(final String mensaje, final Throwable causa) {
		super(mensaje, causa);
	}

}
