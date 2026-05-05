package co.edu.uco.ucoparking.negocio.assembler.entidad;

// D: Representa un dominio (Domain) y E: Representa una Entidad (Entity)
public interface EntidadAssembler<D, E> {

	E ensamblarEntidad(D dominio);

	D ensamblarDominio(E entidad);

}
