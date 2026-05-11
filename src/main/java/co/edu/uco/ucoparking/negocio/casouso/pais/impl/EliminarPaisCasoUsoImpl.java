package co.edu.uco.ucoparking.negocio.casouso.pais.impl;

import co.edu.uco.ucoparking.datos.dao.sql.factoria.DAOFactory;
import co.edu.uco.ucoparking.negocio.casouso.pais.EliminarPaisCasoUso;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;
import co.edu.uco.ucoparking.transversal.utilitario.excepcion.UcoParkingExcepcion;

public class EliminarPaisCasoUsoImpl implements EliminarPaisCasoUso {

	private DAOFactory daoFactory;

	public EliminarPaisCasoUsoImpl(final DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(final PaisDominio datos) {
		validarIntegridadDatosPais(datos);
		validarExistePais(datos);
		eliminarPais(datos);
	}

	// 1. Validar que el id no sea nulo
	private void validarIntegridadDatosPais(final PaisDominio pais) {
		if (UtilObjeto.esNulo(pais)) {
			throw new UcoParkingExcepcion("Los datos del pais son obligatorios");
		}
		if (UtilObjeto.esNulo(pais.getId())) {
			throw new UcoParkingExcepcion("El identificador del pais es obligatorio para eliminar");
		}
	}

	// 2. Validar que el pais a eliminar exista
	private void validarExistePais(final PaisDominio pais) {
		var paisEncontrado = daoFactory.getPaisDAO().consultarPorId(pais.getId());
		if (UtilObjeto.esNulo(paisEncontrado)) {
			throw new UcoParkingExcepcion("No existe un pais registrado con el identificador proporcionado");
		}
	}

	// 3. Eliminar el pais
	private void eliminarPais(final PaisDominio pais) {
		daoFactory.getPaisDAO().eliminar(pais.getId());
	}

}