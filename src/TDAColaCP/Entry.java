package TDAColaCP;

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