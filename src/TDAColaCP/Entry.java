package TDAColaCP;

/**
 * Interafaz Entry que brinda los m�todos que puede realizar una entrada.
 * @author Luz  Cabral & Gonzalo  Perez
 *
 * @param <K> Es el tipo de la clave de la entrada.
 * @param <V> Es el tipo del valor de la entrada.
 */
public interface Entry<K, V> {
  /**
   * Consulta la clave de la entrada.
   * @return Retorna la clave de la entrada.
   */
  public K getKey();
  /**
   * Consulta el valor de la entrada.
   * @return Retorna el valor de la entrada.
   */
  public V getValue();
}