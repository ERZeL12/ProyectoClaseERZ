package co.edu.uco.ucoparking.negocio.assembler.dto;

public interface DTOAssembler<D, R> {
	
		D ensamblarDominio(T dto);
		
		T ensamblarDTO(D dominio);

	}
}
