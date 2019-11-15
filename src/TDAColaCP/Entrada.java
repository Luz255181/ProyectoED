package TDAColaCP;

/**
 * Clase que respresenta una entrada que contiene un par clave-valor.
 * @author Luz  Cabral & Gonzalo  Perez
 *
 * @param <K> Es el tipo de la clave de la entrada.
 * @param <V> Es el tipo del valor de la entrada.
 */
public class Entrada<K, V> implements Entry<K, V> {
  protected K clave;
  protected V valor;
  //Constructor
  /**
   * Crea una nueva entrada con la clave y el valor pasado por parámetro. 
   * @param k Es la clave de la entrada.
   * @param v Es el valor de la entrada.
   */
  public Entrada(K k, V v) {
	clave=k;
	valor=v;
  }
  //Comandos
  /**
   * Setea la clave de la entrada referenciada.
   * @param k Es la clave a setear en la entrada.
   */
  public void setKey(K k) {
	clave=k;
  }
  /**
   * Setea el valor de la entrada referenciada.
   * @param v Es el valor a setear en la entrada.
   */
  public void setValue(V v) {
	valor=v;
  }
  //Consultas
  @Override
  public K getKey() {
	return clave;
  }
  @Override
  public V getValue() {
	return valor;
  }
  public String toString() {
	return "("+getKey()+", "+getValue()+")";
  }
}