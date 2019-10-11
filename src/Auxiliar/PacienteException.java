package Auxiliar;

/**
 * Clase Paciente Exception que extiende de la clase Exception.
 * Modela la excepción que se produce al intentar buscar un paciente inexistente.
 * @author Luz Cabral & Gonzalo Perez
 *
 */
public class PacienteException extends Exception {
	/**
	 * Inicializa la excepción y describe su origen..
	 * @param msj Mensaje que describe el origen del disparo de la excepción.
	 */
	public PacienteException(String msj) {
		super(msj);
	}
}