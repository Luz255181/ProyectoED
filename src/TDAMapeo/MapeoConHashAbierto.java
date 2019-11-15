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
 * @author Luz  Cabral & Gonzalo  Perez
 *
 * @param <K> Es el tipo de la clave del mapeo.
 * @param <V> Es el tipo del valor del mapeo.
 */
public class MapeoConHashAbierto<K, V> implements Map<K, V>{
  protected PositionList<Entrada<K, V>>[] arreglo;
  protected int tama�oInicial=11;
  protected int cantEntradas;
  private final float fc=0.75f;
		  
  //Constructor
  /**
   * Crea un nuevo mapeo vac�o con una capacidad inicial para 11 elementos.
   */
  public MapeoConHashAbierto() {
	arreglo=(PositionList<Entrada<K, V>> []) new PositionList[tama�oInicial];
	for (int i=0; i<tama�oInicial; i++)
		arreglo[i]=new ListaDoblementeEnlazada<Entrada<K, V>>();
	cantEntradas=0;
  }
  //Consultas
  /**
   * Computa el valor hash de la clave pasada por par�metro.
   * @param clave Es la clave usada para calcular el valor hash
   * @return Retorna el valor hash de la clave pasada por par�metro.
   */
  protected int hash(K clave) {
	return (clave.hashCode()) % arreglo.length;
  }
  @Override
  public V get(K clave) throws InvalidKeyException {
	if (clave==null)
	   throw new InvalidKeyException("La clave es invalida.");
	int bucket=hash(clave);
	Entrada<K, V> entrada;
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
	PositionList<Entrada<K, V>> lista=arreglo[bucket];
	Iterator<Entrada<K, V>> it=lista.iterator();
	Entrada<K, V> entrada;
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
	   lista.addLast(entrada);
	   cantEntradas++;
	   if (cantEntradas/tama�oInicial>=fc)
		   redimensionar();
	}
	return valorAnterior;
  }
  /**
   * Redimensiona el tama�o del mapeo extendiendolo cuando se supera el valor 
   * establecido del factor de carga.
   */
  private void redimensionar() {
	try {
		int nuevoTama�o=proximoPrimo(cantEntradas*2);
		PositionList<Entrada<K, V>> [] arregloAnt=arreglo;
		arreglo=(PositionList<Entrada<K, V>>[]) new PositionList[nuevoTama�o];
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
   * Calcula el proximo n�mero primo al tama�o pasado por par�metro.
   * @param nuevoTama�o Es el nuevo tama�o del mapeo.
   * @return Retorna el proximo n�mero primo al n�mero pasado por par�metro.
   */
  private int proximoPrimo(int nuevoTama�o) {
	int resultado=nuevoTama�o;
	while (!esPrimo(resultado))
		  if (resultado % 2==0)
			  resultado++;
		  else resultado=resultado+2;
	return resultado;
  }
  /**
   * Consulta si el n�mero pasado por par�metro es primo.
   * @param n Es el n�mero a verificar si es primo
   * @return Retorna verdadero si el n�mero pasado por par�metro es primo, falso en caso contrario
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
		Position<Entrada<K, V>> pos=arreglo[bucket].first();
		while (!encontre && pos!=null) {
			  if (pos.element().getKey().equals(clave)) {
				 valor=pos.element().getValue();
				 arreglo[bucket].remove(pos);
				 encontre=true;
				 cantEntradas--;
			  }
			  else {
				   if (pos==arreglo[bucket].last())
					  pos=null;
				   else pos=arreglo[bucket].next(pos);
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
	Iterator<Entrada<K, V>> it;
	Entrada<K, V> entrada;
	for (int i=0; i<arreglo.length; i++) {
		it=arreglo[i].iterator();
		while (it.hasNext()) {
			  entrada=it.next();
			  claves.addLast(entrada.getKey());
		}
	}
	return claves;
  }
  @Override
  public Iterable<V> values() {
    PositionList<V> valores=new ListaDoblementeEnlazada<V>();
	Iterator<Entrada<K, V>> it;
	Entrada<K, V> entrada;
	for (int i=0; i<arreglo.length; i++) {
		it=arreglo[i].iterator();
		while (it.hasNext()) {
			  entrada=it.next();
			  valores.addLast(entrada.getValue());
		}
	}
	return valores;
  }
  @Override
  public Iterable<Entry<K, V>> entries() {
	PositionList<Entry<K, V>> entradas=new ListaDoblementeEnlazada<Entry<K, V>>();
	Entrada<K, V> entrada;
	Iterator<Entrada<K, V>> it;
	for (int i=0; i<arreglo.length; i++) {
		it=arreglo[i].iterator();
	  	while (it.hasNext()) {
	  		  entrada=it.next();
	  		  entradas.addLast(entrada);
	  	}
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