package TDAColaCP;
import java.util.Comparator;

public class Comparador<K extends Comparable<K>> implements Comparator<K> {
  public int compare(K elemento1, K elemento2) {
	return elemento1.compareTo(elemento2);
  }
}