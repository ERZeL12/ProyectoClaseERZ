package co.edu.uco.ucoparking.negocio.fachada.pais.impl;

import java.util.UUID;

import co.edu.uco.ucoparking.datos.dao.sql.factoria.DAOFactory;
import co.edu.uco.ucoparking.dto.PaisDTO;
import co.edu.uco.ucoparking.negocio.casouso.pais.EliminarPaisCasoUso;
import co.edu.uco.ucoparking.negocio.casouso.pais.impl.EliminarPaisCasoUsoImpl;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.negocio.fachada.pais.EliminarPaisFachada;
import co.edu.uco.ucoparking.transversal.utilitario.excepcion.UcoParkingExcepcion;

public class EliminarPaisFachadaImpl implements EliminarPaisFachada {

	private DAOFactory daoFactory;
	private EliminarPaisCasoUso casoUso;

	public EliminarPaisFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new EliminarPaisCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(final PaisDTO datos) {
		try {

			daoFactory.iniciarTransaccion();

			var dominio = new PaisDominio.Builder()
					.id(datos.getId())
					.nombre(datos.getNombre())
					.build();

			casoUso.ejecutar(dominio);

			daoFactory.confirmarTransaccion();

		} catch (UcoParkingExcepcion excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new UcoParkingExcepcion();
		} finally {
			daoFactory.cerrarConexion();
		}
	}
	
	public static void main(final String[] args) {
	    try {
	        var pais = new PaisDTO.Builder()
	                .id(UUID.fromString("217d5772-6b9d-44e4-adf7-9cd98f6d0075"))
	                .build();
	        EliminarPaisFachada fachada = new EliminarPaisFachadaImpl();
	        fachada.ejecutar(pais);
	        System.out.println("Pais eliminado exitosamente.");
	    } catch (Exception excepcion) {
	        System.err.println("Error al eliminar el pais: " + excepcion.getMessage());
	        excepcion.printStackTrace();
	    }
	}

}
