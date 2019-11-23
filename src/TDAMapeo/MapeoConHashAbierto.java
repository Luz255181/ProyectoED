package TDAMapeo;
import java.util.Iterator;
import Auxiliar.BoundaryViolationException;
import Auxiliar.EmptyListException;
import Auxiliar.InvalidKeyException;
import Auxiliar.InvalidPositionException;
import TDALista.Position;
import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;

/**
 * Clase que implementa un mapeo utilizando una tabla hash abierta.
 * @author Gonzalo  Perez
 *
 * @param <K> Es el tipo de las claves del mapeo.
 * @param <V> Es el tipo de los valores del mapeo.
 */
public class MapeoConHashAbierto<K, V> implements Map<K, V>{
  protected PositionList<Entrada<K, V>>[] arreglo;
  protected int tamañoInicial=11;
  protected int cantEntradas;
  private final float fc=0.75f;
		  
  //Constructor
  /**
   * Crea un mapeo vacío con una capacidad inicial para 11 elementos.
   */
  public MapeoConHashAbierto() {
	arreglo=(PositionList<Entrada<K, V>> []) new PositionList[tamañoInicial];
	for (int i=0; i<tamañoInicial; i++)
		arreglo[i]=new ListaDoblementeEnlazada<Entrada<K, V>>();
	cantEntradas=0;
  }
  //Consultas
  /**
   * Computa el valor hash de la clave pasada por parámetro.
   * @param clave Es la clave a computar su valor hash.
   * @return Retorna el valor hash de la clave pasada por parámetro.
   */
  protected int hash(K clave) {
	return (clave.hashCode()) % arreglo.length;
  }
  @Override
  public V get(K clave) throws InvalidKeyException {
	if (clave==null)
	   throw new InvalidKeyException("La clave es invalida.");
	int bucket=hash(clave);
	Entrada<K, V> entrada=null;
	boolean encontre=false;
	V valor=null;
	Iterator<Entrada<K,V>> it=arreglo[bucket].iterator();
	while (it.hasNext() && !encontre) {
		  entrada=it.next();
		  if (entrada.getKey().equals(clave)) {
			 encontre=true;
			 valor=entrada.getValue();
		  }
	}
	return valor;
  }
  @Override
  public V put(K clave, V valor) throws InvalidKeyException {
	if (clave==null)
		throw new InvalidKeyException("La clave es invalida.");
	int bucket=hash(clave);
	Iterator<Entrada<K, V>> it=arreglo[bucket].iterator();
	Entrada<K, V> entrada=null;
	boolean encontre=false;
	V valorAnterior=null;
	while (it.hasNext() && !encontre) {
		  entrada=it.next();
		  if (entrada.getKey().equals(clave)) {
			  valorAnterior=entrada.getValue();
			  entrada.setValue(valor);
			  encontre=true;
		  }
	}
	if (!encontre) {
	   entrada=new Entrada<K,V>(clave, valor);
	   arreglo[bucket].addLast(entrada);
	   cantEntradas++;
	   if (cantEntradas/tamañoInicial>=fc)
		   redimensionar();
	}
	return valorAnterior;
  }
  /**
   * Redimensiona el tamaño del mapeo extendiendolo cuando se supera el valor establecido 
   * del factor de carga.
   */
  private void redimensionar() {
	try {
		int nuevoTamaño=proximoPrimo(cantEntradas*2);
		PositionList<Entrada<K, V>> [] arregloAnt=arreglo;
		arreglo=(PositionList<Entrada<K, V>>[]) new PositionList[nuevoTamaño];
		for (int r=0; r<arreglo.length; r++)
			arreglo[r]=new ListaDoblementeEnlazada<Entrada<K, V>>();
		PositionList<Entrada<K, V>> listaAnt;
		Entrada<K,V> entrada=null;
		int bucket;
		for (int i=0; i<arregloAnt.length; i++) {
			listaAnt=arregloAnt[i];
			while (!listaAnt.isEmpty()) {
				  entrada=listaAnt.remove(listaAnt.first());
				  bucket=hash(entrada.getKey());
				  arreglo[bucket].addLast(entrada);
			}
			arregloAnt[i]=null;
		}
	}
	catch (EmptyListException | InvalidPositionException e) {
		  e.printStackTrace();
	}
  }
  /**
   * Calcula el proximo número primo al tamaño pasado por parámetro.
   * @param nuevoTamaño Es el nuevo tamaño del mapeo.
   * @return Retorna el proximo número primo al número pasado por parámetro.
   */
  private int proximoPrimo(int nuevoTamaño) {
	int resultado=nuevoTamaño;
	while (!esPrimo(resultado))
		  if (resultado % 2==0)
			  resultado++;
		  else resultado=resultado+2;
	return resultado;
  }
  /**
   * Consulta si el número pasado por parámetro es primo.
   * @param n Es el número a verificar si es primo.
   * @return Retorna verdadero si el número pasado por parámetro es primo, falso en caso contrario.
   */
  private boolean esPrimo(int n) {
	boolean es;
	if (n>1)
	   es=true;
	else es=false;
	int div=2;
	while (div<n && es) {
		  if (n % div==0)
			 es=false;
		  div++;
	}
	return es;
  }
  @Override
  public V remove(K clave) throws InvalidKeyException {
	if (clave==null)
	   throw new InvalidKeyException("La clave es invalida.");
	try {
		int bucket=hash(clave);
		V valor=null;
		boolean encontre=false;
		if (!arreglo[bucket].isEmpty()) {
			Position<Entrada<K, V>> pos=arreglo[bucket].first();
			while (!encontre && pos!=null) {
				if (pos.element().getKey().equals(clave)) {
					valor=pos.element().getValue();
					arreglo[bucket].remove(pos);
					encontre=true;
					cantEntradas--;
				}
				else pos=(pos==arreglo[bucket].last()) ? null : arreglo[bucket].next(pos);
			}
		}
		return valor;
	}
	catch (InvalidPositionException | BoundaryViolationException | EmptyListException e) {
		  e.printStackTrace(); return null;
	}
  }
  @Override
  public Iterable<K> keys() {
	PositionList<K> claves=new ListaDoblementeEnlazada<K>();
	for (int i=0; i<arreglo.length; i++) {
		for (Position<Entrada<K, V>> pos:arreglo[i].positions())
			claves.addLast(pos.element().getKey());
	}
	return claves;
  }
  @Override
  public Iterable<V> values() {
    PositionList<V> valores=new ListaDoblementeEnlazada<V>();
	for (int i=0; i<arreglo.length; i++) {
		for (Position<Entrada<K, V>> pos:arreglo[i].positions())
			  valores.addLast(pos.element().getValue());
	}
	return valores;
  }
  @Override
  public Iterable<Entry<K, V>> entries() {
	PositionList<Entry<K, V>> entradas=new ListaDoblementeEnlazada<Entry<K, V>>();
	for (int i=0; i<arreglo.length; i++) {
		for (Position<Entrada<K, V>> pos:arreglo[i].positions())
		entradas.addLast(pos.element());
	}
	return entradas;
  }
  @Override
  public int size() {
	return cantEntradas;
  }
  @Override
  public  boolean isEmpty() {
	return (cantEntradas==0);
  }
}