package TDAMapeo;
import java.util.Iterator;

import Auxiliar.*;
import TDACola.*;
import TDALista.*;

/**
 * Modela un Mapeo de entradas
 * @author Maria Luz Cabraly Gonzalo Perez
 *
 * @param <K> Tipo de variable de la clave.
 * @param <V> Tipo de variable del valor.
 */
public class MapeoHashAbierto<K,V> implements Map<K,V>
{
	protected int size;
	protected ListaDobleEnlace<Entry<K,V>>[] arreglo;
	final float carga = 0.5f;
	protected int tamano;
	
	/**
	 * Crea un mapeo vacio.
	 */
	public MapeoHashAbierto()
	{
		size = 0;
		arreglo = (ListaDobleEnlace<Entry<K,V>>[])new ListaDobleEnlace[11];
		tamano = 11;
		for(int i = 0;i<11;i++)
		{
			arreglo[i] = new ListaDobleEnlace<Entry<K,V>>();
		}
		
	}
	
	public int size()
	{
		return size;
	}

	public boolean isEmpty() 
	{
		return size == 0;
	}

	public V get(K key) throws InvalidKeyException 
	{
		checkKey(key);
		V toRet = null;
		ListaDobleEnlace<Entry<K,V>> lista = arreglo[codigoHash(key)];
		Iterator<Entry<K,V>> it = lista.iterator();
		boolean encontre = false;
		Entry<K,V> aux;
		while(!encontre && it.hasNext())
		{
			aux = it.next();
			if(aux.getKey().equals(key))
			{
				encontre = true;
				toRet = aux.getValue();
			}
		}
		return toRet;
	}

	public V put(K key, V value) throws InvalidKeyException 
	{
		checkKey(key);
		V toRet = null;
		ListaDobleEnlace<Entry<K,V>> lista = arreglo[codigoHash(key)];
		Iterator<Position<Entry<K,V>>> it = lista.positions().iterator();
		boolean encontre = false;
		Entrada<K,V> nuevo = new Entrada<K,V>(key, value);
		Position<Entry<K,V>> aux;
		while(!encontre && it.hasNext())
		{
			aux = it.next();
			if(aux.element().getKey().equals(key))
			{
				try
				{
					encontre = true;
					toRet = lista.set(aux, nuevo).getValue();
				}
				catch(InvalidPositionException e)
				{
					toRet = null;
				}
			}
		}
		if(!encontre)
		{
			arreglo[codigoHash(key)].addLast(nuevo);
			size++;
		}
		if(size/tamano == carga)
			reHash();
		return toRet;
	}

	public V remove(K key) throws InvalidKeyException 
	{
		checkKey(key);
		V toRet = null;
		ListaDobleEnlace<Entry<K,V>> lista = arreglo[codigoHash(key)];
		Iterator<Position<Entry<K,V>>> it = lista.positions().iterator();
		boolean encontre = false;
		Position<Entry<K,V>> aux;
		while(!encontre && it.hasNext())
		{
			aux = it.next();
			if(aux.element().getKey().equals(key))
			{
				try
				{
					encontre = true;
					toRet = lista.remove(aux).getValue();
					size--;
				}
				catch(InvalidPositionException e)
				{
					toRet = null;
				}
			}
		}
		return toRet;
	}

	public Iterable<K> keys() 
	{
		ListaDobleEnlace<K> toRet = new ListaDobleEnlace<K>();
		for(ListaDobleEnlace<Entry<K,V>> lista: arreglo)
		{
			for(Entry<K,V> ent: lista)
			{
				toRet.addLast(ent.getKey());
			}
		}
		return toRet;
	}

	public Iterable<V> values() 
	{
		ListaDobleEnlace<V> toRet = new ListaDobleEnlace<V>();
		for(ListaDobleEnlace<Entry<K,V>> lista: arreglo)
		{
			for(Entry<K,V> ent: lista)
			{
				toRet.addLast(ent.getValue());
			}
		}
		return toRet;
	}

	public Iterable<Entry<K, V>> entries() 
	{
		ListaDobleEnlace<Entry<K,V>> toRet = new ListaDobleEnlace<Entry<K,V>>();
		for(ListaDobleEnlace<Entry<K,V>> lista: arreglo)
		{
			for(Entry<K,V> ent: lista)
			{
				toRet.addLast(ent);
			}
		}
		return toRet;
	}

	/**
	 * Chequea si la clave pasada por parametro es valida.
	 * @param key Clave a verificar.
	 * @throws InvalidKeyException Si la clave pasada es invalida.
	 */
	private void checkKey(K key) throws InvalidKeyException
	{
		if(key == null)
			throw new InvalidKeyException("clave invalida");
	}
	
	/**
	 * Genera el codigo hash con respecto a la clave pasada.
	 * @param key Clave.
	 * @return Codigo Hash.
	 */
	private int codigoHash(K key)
	{
		return (Integer) key.hashCode()%tamano;
	}
	
	/**
	 * Verifica si el numero pasado es primo.
	 * @param numero Numero a verificar.
	 * @return Si es primo o no.
	 */
	private boolean esPrimo(int numero){
		  int contador = 2;
		  boolean primo=true;
		  while ((primo) && (contador!=numero)){
		    if (numero % contador == 0)
		      primo = false;
		    contador++;
		  }
		  return primo;
		}
	
	/**
	 * Busca el proximo primo al numero pasado.
	 * @param primo Numero al cual se le va a buscar el siguiente primo.
	 * @return Siguiente Primo.
	 */
	private int proximoPrimo(int primo)
	{
		while(!esPrimo(primo))
		{
			primo = primo + 1;
		}
		return primo;
	}
	
	/**
	 * Aumenta el tamanio del Mapeo una vez superado el Factor de Carga
	 */
	private void reHash()
	{
		Iterable<Entry<K,V>> entradas = entries();
		tamano = proximoPrimo(tamano+1);
		arreglo = (ListaDobleEnlace<Entry<K,V>>[])new ListaDobleEnlace[tamano];
		for(int i = 0;i<tamano;i++)
		{
			arreglo[i] = new ListaDobleEnlace<Entry<K,V>>();
		}
		for(Entry<K,V> ent:entradas)
		{
			arreglo[codigoHash(ent.getKey())].addLast(ent);
		}
	}
}