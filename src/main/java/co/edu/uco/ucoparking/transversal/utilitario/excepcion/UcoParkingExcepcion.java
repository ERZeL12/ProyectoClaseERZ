package co.edu.uco.ucoparking.transversal.utilitario.excepcion;

public class UcoParkingExcepcion extends RuntimeException {

    private static final long serialVersionUID = -127481128908084318L;

    private String mensajeUsuario;
    private String mensajeTecnico;
    private Exception excepcionRaiz;
    
    public UcoParkingExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcion) {
		super();
		this.mensajeUsuario = mensajeUsuario;
		this.mensajeTecnico = mensajeTecnico;
		this.excepcionRaiz = excepcion;
	}

    public UcoParkingExcepcion() {
        super();
    }

    public UcoParkingExcepcion(final String mensaje) {
        super(mensaje);
    }

    public String getMensajeUsuario() {
        return mensajeUsuario;
    }

    public void setMensajeUsuario(final String mensajeUsuario) {
        this.mensajeUsuario = mensajeUsuario;
    }

    public String getMensajeTecnico() {
        return mensajeTecnico;
    }

    public void setMensajeTecnico(final String mensajeTecnico) {
        this.mensajeTecnico = mensajeTecnico;
    }

    public Exception getExcepcionRaiz() {
        return excepcionRaiz;
    }

    public void setExcepcionRaiz(final Exception excepcionRaiz) {
        this.excepcionRaiz = excepcionRaiz;
    }

}