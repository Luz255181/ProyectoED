package Auxiliar;

/**
 * Clase InvalidPositionException que extiende de la clase Exception.
 * Modela la excepción que se produce ante una posición que es invalida.
 * @author Luz  Cabral & Gonzalo  Perez
 *
 */
public class InvalidPositionException extends Exception {
  /**
   * Inicializa una excepción por posición invalido y describe su origen.
   * @param msj Mensaje que describe el origen del disparo de excepción.
   */
  public InvalidPositionException(String msj) {
	super(msj);
  }
}