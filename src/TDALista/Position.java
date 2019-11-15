package TDALista;

/**
 * Interfaz Position
 * @author Luz  Cabral & Gonzalo  Perez
 *
 * @param <E> Es el tipo de los elementos dentro de la posición.
 */
public interface Position<E> {
  /**
   * Consulta el elemento asociado a la posición y lo retrona.
   * @return Retorna el elemento asociado a la posición
   */
  public E element();
}