package Mapeo;

public interface Entry<K,V> 
{
	/**
	 * retorna la clave de la entrada
	 * @return clave de la entrada
	 */
	public K getKey();
	
	/**
	 * retorna el valor de la entrada
	 * @return valor de la entrada
	 */
	public V getValue();
}
