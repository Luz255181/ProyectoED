package TDAColaCP;

public class Entrada<K, V> implements Entry<K, V> {
  protected K clave;
  protected V valor;
  //Constructor
  public Entrada(K k, V v) {
	clave=k;
	valor=v;
  }
  //Comandos
  public void setKey(K k) {
	clave=k;
  }
  public void setValue(V v) {
	valor=v;
  }
  //Consultas
  public K getKey() {
	return clave;
  }
  public V getValue() {
	return valor;
  }
  public String toString() {
	return "("+getKey()+", "+getValue()+")";
  }
}