package co.edu.uco.ucoparking.negocio.casouso.pais.impl;

import co.edu.uco.ucoparking.datos.dao.sql.factoria.DAOFactory;
import co.edu.uco.ucoparking.entidad.PaisEntidad;
import co.edu.uco.ucoparking.negocio.casouso.pais.ConsultarPaisPorIdCasoUso;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;
import co.edu.uco.ucoparking.transversal.utilitario.excepcion.UcoParkingExcepcion;

public class ConsultarPaisPorIdCasoUsoImpl implements ConsultarPaisPorIdCasoUso {

	private DAOFactory daoFactory;

	public ConsultarPaisPorIdCasoUsoImpl(final DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public PaisDominio ejecutar(final PaisDominio datos) {
		validarIntegridadDatos(datos);
		return consultarPais(datos);
	}

	// 1. Validar que el id no sea nulo
	private void validarIntegridadDatos(final PaisDominio pais) {
		if (UtilObjeto.esNulo(pais)) {
			throw new UcoParkingExcepcion();
		}
		if (UtilObjeto.esNulo(pais.getId())) {
			throw new UcoParkingExcepcion();
		}
	}

	// 2. Consultar el pais por id y convertir entidad a dominio
	private PaisDominio consultarPais(final PaisDominio pais) {
		PaisEntidad entidadEncontrada = daoFactory.getPaisDAO().consultarPorId(pais.getId());

		if (UtilObjeto.esNulo(entidadEncontrada)) {
			throw new UcoParkingExcepcion();
		}

		return new PaisDominio.Builder()
				.id(entidadEncontrada.getId())
				.nombre(entidadEncontrada.getNombre())
				.build();
	}

}
