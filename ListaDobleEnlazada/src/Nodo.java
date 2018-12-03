
public class Nodo {
	Persona persona;
	Nodo siguiente, anterior;
	
	/**
	 * @param persona
	 */
	public Nodo(Persona persona) {
		this(persona, null, null);
	}
	
	/**
	 * @param persona
	 * @param siguiente
	 * @param anterior
	 */
	public Nodo(Persona persona, Nodo siguiente, Nodo anterior) {
		super();
		this.persona = persona;
		this.siguiente = siguiente;
		this.anterior = anterior;
	}
	
	

}
