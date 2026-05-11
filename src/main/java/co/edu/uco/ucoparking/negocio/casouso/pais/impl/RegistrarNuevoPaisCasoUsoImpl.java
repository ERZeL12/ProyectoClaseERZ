package co.edu.uco.ucoparking.negocio.casouso.pais.impl;

import java.util.UUID;

import co.edu.uco.ucoparking.datos.dao.sql.factoria.DAOFactory;
import co.edu.uco.ucoparking.entidad.PaisEntidad;
import co.edu.uco.ucoparking.negocio.casouso.pais.RegistrarNuevoPaisCasoUso;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilTexto;
import co.edu.uco.ucoparking.transversal.utilitario.excepcion.UcoParkingExcepcion;

public class RegistrarNuevoPaisCasoUsoImpl implements RegistrarNuevoPaisCasoUso {

	private DAOFactory daoFactory;

	public RegistrarNuevoPaisCasoUsoImpl(final DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(final PaisDominio datos) {
		validarIntegridadDatosPais(datos);
		validarNoExistaOtroPaisConMismoNombre(datos.getNombre());
		guardarNuevoPais(datos);
	}

	// 1. Validacion de integridad de datos: tipo de dato, longitud, obligatoriedad, formato, rango
	private void validarIntegridadDatosPais(final PaisDominio pais) {
		if (UtilObjeto.esNulo(pais)) {
			throw new UcoParkingExcepcion();
		}
		if (UtilTexto.esNula(pais.getNombre()) || pais.getNombre().trim().isEmpty()) {
			throw new UcoParkingExcepcion();
		}
		if (pais.getNombre().trim().length() > 100) {
			throw new UcoParkingExcepcion();
		}
	}

	// 2. No debe existir otro pais con el mismo nombre
	private void validarNoExistaOtroPaisConMismoNombre(final String nombre) {
		var paisEntidadFiltro = new PaisEntidad.Builder().nombre(nombre).build();
		var resultados = daoFactory.getPaisDAO().consultarPorFiltro(paisEntidadFiltro);

		if (!UtilObjeto.esNulo(resultados) && !resultados.isEmpty()) {
			throw new UcoParkingExcepcion();
		}
	}

	// 3. Generar id unico para el nuevo pais
	private UUID generarIdUnicoNuevoPais() {
		return UUID.randomUUID();
	}

	// 4. Guardar la informacion del nuevo pais
	private void guardarNuevoPais(final PaisDominio pais) {
		var idNuevoPais = generarIdUnicoNuevoPais();
		var paisEntidad = new PaisEntidad.Builder()
				.id(idNuevoPais)
				.nombre(pais.getNombre())
				.build();
		daoFactory.getPaisDAO().crear(paisEntidad);
	}

}
