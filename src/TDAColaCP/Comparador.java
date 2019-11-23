package TDAColaCP;
import java.util.Comparator;

/**
 * Clase Comparador que brinda m�toos para comparar dos elementos comparables.
 * @author Luz  Cabral & Gonzalo  Perez
 *
 * @param <K> Es el tipo de los elementos a comparar.
 */
public class Comparador<K extends Comparable<K>> implements Comparator<K> {
  /**
   * Compara a los dos elementos pasados por par�metro
   * @return Retorna un n�mero positivo si el primer elemento es mayor que el segundo, 
   * 		 un n�mero negativo si el segundo elemento es mayor que el primero y 
   * 		 un 0 si los elementos son iguales.
   */
  public int compare(K elemento1, K elemento2) {
	return elemento1.compareTo(elemento2);
  }
}