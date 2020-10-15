package ejemplo.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class AppMap {

	public static void main(String[] args) {
		
		//Hashmap no permite almacenar dos valores con la misma llave, y la ordenaci�n es de forma aleatoria, aunque si la llave
		//es un integer lo ordenar� ascendentemente por valor de llave. De los valores duplicados se queda con el �ltimo
		Map<Integer, String> mapa = new HashMap<Integer, String>();
		
		mapa.put(4, "Carlos");
		mapa.put(1, "Sonia");
		mapa.put(3, "Raul");
		mapa.put(5, "Diego");
		mapa.put(2, null);
		
		Iterator it = mapa.keySet().iterator();
		while (it.hasNext()) {
			Integer key = (Integer) it.next();
			System.out.println("Clave: " + key + ", Valor: " + mapa.get(key));
		}
		
		//Para TREEMAP no admite duplicados y ordena los valores de mapa de forma ascendente
		//Para LINKEDHASHMAP no admite duplicados y ordena los valores en funci�n de en qu� orden se agregan a la lista
		
		for (Entry<Integer, String> ent : mapa.entrySet()) {
			System.out.println(ent.getKey() + " - " + ent.getValue());
		}

	}

}
