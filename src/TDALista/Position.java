package TDALista;

/**
 * Interfaz Position
 * @author Luz Cabral y Gonzalo Perez
 *
 * @param <E> Es el tipo de los elementos dentro de la posici�n.
 */
public interface Position<E> {
  /**
   * Consulta el elemento asociado a la posici�n y lo retorna.
   * @return Retorna el elemento asociado a la posici�n
   */
  public E element();
}