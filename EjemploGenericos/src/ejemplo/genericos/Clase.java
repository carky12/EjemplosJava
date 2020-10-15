package ejemplo.genericos;


/**
 * @author Carlos
 * Convenci�n de nombres para los tipos de par�metros
 * E - Elemento
 * K - Key
 * N - Number
 * T - Tipo
 * V - Value
 * S, U, V, etc - M�s tipos...
 * 
 */
public class Clase<T> {
	
	private T objeto;

	public Clase(T objeto) {		
		this.objeto = objeto;
	}
	
	public void mostrarTipo() {
		System.out.println("T es un objeto de tipo: " + objeto.getClass());
	}
	
	

}
