package co.edu.uco.ucoparking.negocio.assembler.entidad.impl;

import co.edu.uco.ucoparking.entidad.PaisEntidad;
import co.edu.uco.ucoparking.negocio.assembler.entidad.EntidadAssembler;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.UtilObjeto;

public final class PaisEntidadAssembler implements EntidadAssembler<PaisDominio, PaisEntidad> {

	// Singleton
	private static EntidadAssembler<PaisDominio, PaisEntidad> INSTANCE;

	private PaisEntidadAssembler() {
		super();
	}

	public synchronized static final EntidadAssembler<PaisDominio, PaisEntidad> getInstance() {
		if (UtilObjeto.esNulo(INSTANCE)) {
			INSTANCE = new PaisEntidadAssembler();
		}
		return INSTANCE;
	}

	// PaisDominio a PaisEntidad
	@Override
	public PaisEntidad ensamblarEntidad(final PaisDominio dominio) {
		var paisAEnsamblar = UtilObjeto.obtenerValorDefecto(dominio, new PaisDominio.Builder().build());
		return new PaisEntidad.Builder()
				.id(paisAEnsamblar.getId())
				.nombre(paisAEnsamblar.getNombre())
				.build();
	}

	// PaisEntidad a PaisDominio
	@Override
	public PaisDominio ensamblarDominio(final PaisEntidad entidad) {
		var paisAEnsamblar = UtilObjeto.obtenerValorDefecto(entidad, new PaisEntidad.Builder().build());
		return new PaisDominio.Builder()
				.id(paisAEnsamblar.getId())
				.nombre(paisAEnsamblar.getNombre())
				.build();
	}
}
