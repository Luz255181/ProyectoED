package TDAColaCP;
import java.util.Comparator;
import Auxiliar.EmptyPriorityQueueException;
import Auxiliar.InvalidKeyException;

/**
 * Clase Heap que implementa una cola con prioridad 
 * @author Luz Cabral y Gonzalo Perez
 *
 * @param <K> Es el tipo de la prioridad de la cola con prioridad.
 * @param <V> Es el tipo del valor asociado a la prioridad.
 */
public class Heap<K, V> implements PriorityQueue<K, V> {
  protected Entrada<K, V> [] arreglo;
  protected Comparator<K> comp;
  protected int tama�o;

  /**
   * Crea un heap con capacidad inicial para 100 elementos, incializa el tama�o en 0 y 
   * setea el valor de comp con el compardor pasado por par�metro.
   * @param comparador Es el comparador que se va a utilizar en el heap.
   */
  public Heap(Comparator<K> comparador) {
	arreglo=(Entrada<K, V> [])new Entrada[100];
	comp=comparador;
	tama�o=0;
  }
  //Consultas
  @Override
  public Entry<K,V> min() throws EmptyPriorityQueueException {
	if (tama�o==0)
		throw new EmptyPriorityQueueException("La cola con prioridad est� vac�a.");
	return arreglo[1];
  }
  @Override
  public Entry<K,V> insert(K prioridad,V valor) throws InvalidKeyException {
	if (prioridad==null)
		throw new InvalidKeyException("La clave es invalida.");
	Entrada<K, V> [] arregloAux;
	Entrada<K, V> aux;
	if (tama�o==arreglo.length-1) {
		arregloAux=(Entrada<K, V>[]) new Entrada[arreglo.length*2];
		for (int r=0; r<arreglo.length; r++)
			arregloAux[r]=arreglo[r];
		arreglo=arregloAux;
	}
	Entrada<K, V> nuevaEntrada=new Entrada<K, V>(prioridad, valor);
	tama�o++;
	arreglo[tama�o]=nuevaEntrada;
	int i=tama�o;
	boolean seguir=true;
	while (i>1 && seguir) {
		if (comp.compare(arreglo[i].getKey(), arreglo[i/2].getKey())<0) {
			aux=arreglo[i];
			arreglo[i]=arreglo[i/2];
			arreglo[i/2]=aux;
			i=i/2;
		}
		else seguir=false;
	}
	return nuevaEntrada;
  }
  @Override
  public Entry<K,V> removeMin() throws EmptyPriorityQueueException {
	if (tama�o==0)
		throw new EmptyPriorityQueueException("La cola con prioridad est� vac�a.");
	Entry<K, V> minimo=arreglo[1];
	int i, hijoIzq, hijoDer, posMin=1;
	boolean seguir, tieneHI, tieneHD;
	if (tama�o==1) {
		arreglo[tama�o]=null; tama�o=0;
	}
	else {
		 arreglo[1]=arreglo[tama�o];
		 arreglo[tama�o]=null;
		 tama�o--;
		 //Burbujeo la nueva ra�z hasta encontrar su ubicaci�n correcta
		 i=1;
		 seguir=true;
		 while (seguir) {
			   hijoIzq=i*2;
			   hijoDer=i*2+1;
			   tieneHI=(hijoIzq<=tama�o);
			   tieneHD=(hijoDer<=tama�o);
			   if (!tieneHI)
				   seguir=false;
			   else {
				    posMin=hijoIzq;
				    if (tieneHD)
				    	if (comp.compare(arreglo[hijoIzq].getKey(), arreglo[hijoDer].getKey())>0)
				    		posMin=hijoDer;
				    if (comp.compare(arreglo[i].getKey(), arreglo[posMin].getKey())>0) {
				    	Entrada<K, V> aux=arreglo[i];
				    	arreglo[i]=arreglo[posMin];
				    	arreglo[posMin]=aux;
				    	i=posMin;
				    }
				    else seguir=false;
			   }
		 }
	}
	return minimo;
  }
  @Override
  public int size() {
	return tama�o;
  }
  @Override
  public boolean isEmpty() {
	return (tama�o==0);
  }
}