package co.edu.uco.ucoparking.negocio.fachada.pais.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.ucoparking.datos.dao.sql.factoria.DAOFactory;
import co.edu.uco.ucoparking.dto.PaisDTO;
import co.edu.uco.ucoparking.negocio.casouso.pais.ConsultarTodosPaisesCasoUso;
import co.edu.uco.ucoparking.negocio.casouso.pais.impl.ConsultarTodosPaisesCasoUsoImpl;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.negocio.fachada.pais.ConsultarTodosPaisesFachada;
import co.edu.uco.ucoparking.transversal.utilitario.excepcion.UcoParkingExcepcion;

public class ConsultarTodosPaisesFachadaImpl implements ConsultarTodosPaisesFachada {

	private DAOFactory daoFactory;
	private ConsultarTodosPaisesCasoUso casoUso;

	public ConsultarTodosPaisesFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarTodosPaisesCasoUsoImpl(daoFactory);
	}

	@Override
	public List<PaisDTO> ejecutar(final PaisDTO datos) {
		try {

			var dominioFiltro = new PaisDominio.Builder().build();

			var dominiosResultado = casoUso.ejecutar(dominioFiltro);

			List<PaisDTO> dtosResultado = new ArrayList<>();
			for (PaisDominio dominio : dominiosResultado) {
				dtosResultado.add(new PaisDTO.Builder()
						.id(dominio.getId())
						.nombre(dominio.getNombre())
						.build());
			}
			return dtosResultado;

		} catch (UcoParkingExcepcion excepcion) {
			throw excepcion;
		} catch (Exception excepcion) {
			throw new UcoParkingExcepcion();
		} finally {
			daoFactory.cerrarConexion();
		}
	}

}
