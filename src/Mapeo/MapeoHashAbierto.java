package Mapeo;
import java.util.Iterator;
import Cola.*;
import Exception.*;
import Lista.*;

public class MapeoHashAbierto<K,V> implements Map<K,V>
{
	protected int size;
	protected ListaDobleEnlace<Entry<K,V>>[] arreglo;
	final float carga = 0.5f;
	protected int tamano;
	
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

	private void checkKey(K key) throws InvalidKeyException
	{
		if(key == null)
			throw new InvalidKeyException("clave invalida");
	}
	
	private int codigoHash(K key)
	{
		return (Integer) key.hashCode()%tamano;
	}
	
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
	
	private int proximoPrimo(int primo)
	{
		while(!esPrimo(primo))
		{
			primo = primo + 1;
		}
		return primo;
	}
	
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
