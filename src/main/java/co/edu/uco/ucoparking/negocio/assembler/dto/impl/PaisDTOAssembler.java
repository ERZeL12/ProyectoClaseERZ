package co.edu.uco.ucoparking.negocio.assembler.dto.impl;


import co.edu.uco.ucoparking.controlador.dto.PaisDTO;
import co.edu.uco.ucoparking.datos.util.UtilObjeto;
import co.edu.uco.ucoparking.negocio.assembler.dto.DTOAssembler;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;

public final class PaisDTOAssembler implements DTOAssembler<PaisDominio, PaisDTO> {

private  static DTOAssembler<PaisDominio, PaisDTO> INSTANCE;

private PaisDTOAssembler() {
	super();
}

public synchronized static final DTOAssembler<PaisDominio, PaisDTO> getInstance() {
	
	if (utilObjeto.esNulo(INSTANCE)) {
		INSTANCE = new PaisDTOAssembler();
	}
	
	return INSTANCE;
}
	
	@Override
	public PaisDominio ensamblarDominio(final PaisDTO dto) {
		var paisAEnsamblar = UtilObjeto.obtenerObjetoDeObjeto(dto, new PaisDTO.Builder().build(9));
		return new PaisDominio.Builder().id(paisAEnsamblar.getId()).nombre(paisAEnsamblar.getNombre()).build();
	}

	@Override
	public PaisDominio ensamblarDominio(final PaisDominio dto) {
		var paisAEnsamblar = UtilObjeto.obtenerObjetoDeObjeto(dto, new PaisDominio.Builder().build(9));
		return new PaisDTO.Builder().id(paisAEnsamblar.getId()).nombre(paisAEnsamblar.getNombre()).build();
	}

	public static void main(String[] args) {
		var miPaisDTO = new PaisDTO.Builder().id(null).build();
		var miPaisDominio = PaisDTOAssembler.getInstance().ensamblarDominio(miPaisDTO);
	}
	
}
