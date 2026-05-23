package co.edu.uco.ucoparking.negocio.casouso.pais.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.ucoparking.datos.dao.sql.factoria.DAOFactory;
import co.edu.uco.ucoparking.entidad.PaisEntidad;
import co.edu.uco.ucoparking.negocio.casouso.pais.ConsultarTodosPaisesCasoUso;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;

public class ConsultarTodosPaisesCasoUsoImpl implements ConsultarTodosPaisesCasoUso {

	private DAOFactory daoFactory;

	public ConsultarTodosPaisesCasoUsoImpl(final DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public List<PaisDominio> ejecutar(final PaisDominio datos) {
		return consultarTodos();
	}

	// 1. Consultar todos los paises y mapear entidad a dominio
	private List<PaisDominio> consultarTodos() {
		List<PaisEntidad> entidadesEncontradas = daoFactory.getPaisDAO().consultarTodos();
		List<PaisDominio> dominiosResultado = new ArrayList<>();

		if (UtilObjeto.esNulo(entidadesEncontradas)) {
			return dominiosResultado;
		}

		for (PaisEntidad entidad : entidadesEncontradas) {
			dominiosResultado.add(new PaisDominio.Builder()
					.id(entidad.getId())
					.nombre(entidad.getNombre())
					.build());
		}
		return dominiosResultado;
	}

}
