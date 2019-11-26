package Auxiliar;

/**
 * Clase PacienteException que extiende de Exception.
 * Modela la excepci�n que se produce al intentar buscar un paciente inexistente.
 * @author Luz Cabral y Gonzalo Perez
 *
 */
public class PacienteException extends Exception {
	/**
	 * Inicializa la excepci�n y describe su origen.
	 * @param msj Mensaje que describe el origen del disparo de la excepci�n.
	 */
	public PacienteException(String msj) {
		super(msj);
	}
}