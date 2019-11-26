package Auxiliar;

/**
 * Clase PacienteException que extiende de Exception.
 * Modela la excepción que se produce al intentar buscar un paciente inexistente.
 * @author Luz Cabral y Gonzalo Perez
 *
 */
public class PacienteException extends Exception {
	/**
	 * Inicializa la excepción y describe su origen.
	 * @param msj Mensaje que describe el origen del disparo de la excepción.
	 */
	public PacienteException(String msj) {
		super(msj);
	}
}