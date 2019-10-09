package Mapeo;
/**
 * Modela las entradas del Mapeo.
 * @author Maria Luz Cabral y Gonzalo Perez
 *
 * @param <K> Tipo de variable de la clave.
 * @param <V> Tipo de variable del valor.
 */
public class Entrada <K,V> implements Entry<K,V>
{
	protected K key;
	protected V value;

	/**
	 * Crea una entrada vacia.
	 */
	public Entrada()
	{
		key =  null;
		value = null;
	}
	
	/**
	 * Crea una entrada con clave y valor.
	 * @param k Clave.
	 * @param v Valor.
	 */
	public Entrada(K k, V v)
	{
		key = k;
		value = v;
	}
	
	public K getKey() 
	{
		return key;
	}

	public V getValue() 
	{
		return value;
	}
	
	/**
	 * Setea la clave.
	 * @param k Clave.
	 */
	public void setKey(K k)
	{
		key = k;
	}
	/**
	 * Setea el valor.
	 * @param v Valor.
	 */
	public void setValue(V v)
	{
		value = v;
	}
}
