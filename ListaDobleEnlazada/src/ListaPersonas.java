import javax.swing.JOptionPane;

public class ListaPersonas {
private Nodo nodoInicio, nodoFin;



/**
 * 
 */
public ListaPersonas() {
	this.nodoInicio = this.nodoFin = null;
}



/**
 * @param inicio
 * @param fin
 */
public ListaPersonas(Nodo nodoInicio, Nodo nodoFin) {
	super();
	this.nodoInicio = nodoInicio;
	this.nodoFin = nodoFin;
}

//Método para saber si la lista tiene elementos
public boolean estaVacia() {
	return nodoInicio==null;
}

/**
 * Añadimos una nueva persona al final de la lista
 * @param elemento
 */
public void agergarPersonaFinal(Persona persona) {
	if(!estaVacia()) {
		this.nodoFin = new Nodo(persona, null, this.nodoFin);
		nodoFin.anterior.siguiente = nodoFin;
	}else {
		this.nodoInicio=this.nodoFin=new Nodo(persona);
	}
}

/**
 * Añadimos una nueva persona al inicio de la lista
 * @param elemento
 */
public void agergarPersonaInicio(Persona persona) {
	if(!estaVacia()) {
		this.nodoInicio = new Nodo(persona, this.nodoInicio, null);
		nodoInicio.siguiente.anterior = nodoInicio;
	}else {
		this.nodoInicio=this.nodoFin=new Nodo(persona);
	}
}

public void mostrarListaInicioFin() {
	if(!estaVacia()) {
		String datosPersonas="";
		Nodo aux = nodoInicio;
		while(aux != null) {
			datosPersonas = datosPersonas + "[" + aux.persona.getNombre() + "] ";
			aux=aux.siguiente;
		}
		JOptionPane.showMessageDialog(null,  datosPersonas, "Lista de personas de inicio a fin", JOptionPane.INFORMATION_MESSAGE);
	}
}

public void mostrarListaFininicio() {
	if(!estaVacia()) {
		String datosPersonas="";
		Nodo aux = nodoFin;
		while(aux != null) {
			datosPersonas = datosPersonas + "[" + aux.persona.getNombre() + "] ";
			aux=aux.anterior;
		}
		JOptionPane.showMessageDialog(null,  datosPersonas, "Lista de personas de fin a inicio", JOptionPane.INFORMATION_MESSAGE);
	}
}


}
