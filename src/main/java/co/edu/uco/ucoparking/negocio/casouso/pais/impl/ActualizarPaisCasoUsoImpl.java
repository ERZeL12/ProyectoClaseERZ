package co.edu.uco.ucoparking.negocio.casouso.pais.impl;

import java.util.UUID;

import co.edu.uco.ucoparking.datos.dao.sql.factoria.DAOFactory;
import co.edu.uco.ucoparking.entidad.PaisEntidad;
import co.edu.uco.ucoparking.negocio.casouso.pais.ActualizarPaisCasoUso;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilTexto;
import co.edu.uco.ucoparking.transversal.utilitario.excepcion.UcoParkingExcepcion;

public class ActualizarPaisCasoUsoImpl implements ActualizarPaisCasoUso {

	private DAOFactory daoFactory;

	public ActualizarPaisCasoUsoImpl(final DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(final PaisDominio datos) {
		validarIntegridadDatosPais(datos);
		validarExistePais(datos.getId());
		validarNoExistaOtroPaisConMismoNombre(datos.getNombre(), datos.getId());
		actualizarPais(datos);
	}

	// 1. Validacion de integridad de datos
	private void validarIntegridadDatosPais(final PaisDominio pais) {
		if (UtilObjeto.esNulo(pais)) {
			throw new UcoParkingExcepcion();
		}
		if (UtilObjeto.esNulo(pais.getId())) {
			throw new UcoParkingExcepcion();
		}
		if (UtilTexto.esNula(pais.getNombre()) || pais.getNombre().trim().isEmpty()) {
			throw new UcoParkingExcepcion();
		}
		if (pais.getNombre().trim().length() > 100) {
			throw new UcoParkingExcepcion();
		}
	}

	// 2. Validar que el pais a actualizar exista
	private void validarExistePais(final UUID id) {
		var paisEncontrado = daoFactory.getPaisDAO().consultarPorId(id);
		if (UtilObjeto.esNulo(paisEncontrado)) {
			throw new UcoParkingExcepcion();
		}
	}

	// 3. No debe existir otro pais con el mismo nombre (diferente al que se actualiza)
	private void validarNoExistaOtroPaisConMismoNombre(final String nombre, final UUID idActual) {
		var paisEntidadFiltro = new PaisEntidad.Builder().nombre(nombre).build();
		var resultados = daoFactory.getPaisDAO().consultarPorFiltro(paisEntidadFiltro);

		if (!UtilObjeto.esNulo(resultados) && !resultados.isEmpty()) {
			for (var pais : resultados) {
				if (!pais.getId().equals(idActual)) {
					throw new UcoParkingExcepcion();
				}
			}
		}
	}

	// 4. Actualizar la informacion del pais
	private void actualizarPais(final PaisDominio pais) {
		var paisEntidad = new PaisEntidad.Builder()
				.id(pais.getId())
				.nombre(pais.getNombre())
				.build();
		daoFactory.getPaisDAO().actualizar(pais.getId(), paisEntidad);
	}

}
