import javax.swing.JOptionPane;

public class ListaPersonas {
	private Nodo nodoInicio, nodoFin;

	private Nodo aux;

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
		return nodoInicio == null;
	}

	/**
	 * Añadimos una nueva persona al final de la lista
	 * 
	 * @param elemento
	 */
	public void agergarPersonaFinal(Persona persona) {
		if (!estaVacia()) {
			this.nodoFin = new Nodo(persona, null, this.nodoFin);
			nodoFin.anterior.siguiente = nodoFin;
		} else {
			this.nodoInicio = this.nodoFin = new Nodo(persona);
		}
	}

	/**
	 * Añadimos una nueva persona al inicio de la lista
	 * 
	 * @param elemento
	 */
	public void agergarPersonaInicio(Persona persona) {
		if (!estaVacia()) {
			this.nodoInicio = new Nodo(persona, this.nodoInicio, null);
			nodoInicio.siguiente.anterior = nodoInicio;
		} else {
			this.nodoInicio = this.nodoFin = new Nodo(persona);
		}
	}

	/**
	 * Método para insertar en una posición específica siempre y cuando exista
	 * 
	 * @param pos
	 * @param x
	 */
	void insertarEnPosicion(int pos, Persona persona) {
		if (pos <= tamaño()) {
			Nodo nodoInsertar = new Nodo(persona);
			if (pos == 0) {
				nodoInsertar.siguiente = nodoInicio;
				if (nodoInicio != null)
					nodoInicio.anterior = nodoInsertar;
				nodoInicio = nodoInsertar;
			} else if (pos == tamaño()) {
				aux = nodoInicio;
				while (aux.siguiente != null) {
					aux = aux.siguiente;
				}
				aux.siguiente = nodoInsertar;
				nodoInsertar.anterior = aux;
				nodoInsertar.siguiente = null;
			} else {
				aux = nodoInicio;
				for (int f = 1; f <= pos - 1; f++)
					aux = aux.siguiente;
				Nodo siguiente = aux.siguiente;
				aux.siguiente = nodoInsertar;
				nodoInsertar.anterior = aux;
				nodoInsertar.siguiente = siguiente;
				siguiente.anterior = nodoInsertar;
			}
		}
	}
	
	/**
	 * Método que obtiene una persona en una posición específica
	 * @param pos
	 * @return
	 */
	public void obtenerDePosicion (int pos) {
        if (pos <= tamaño())    {
            String persona;
            if (pos == 0) {
                persona = "[" + nodoInicio.persona.getNombre() + " " + nodoInicio.persona.getApellido1() + ", "
						+ nodoInicio.persona.getDni() + "] ";
            } else {
                aux = nodoInicio;
                for (int i = 1 ; i <= pos ; i++)
                    aux = aux.siguiente;
                Nodo prox = aux.siguiente;
                aux.siguiente = prox.siguiente;
                Nodo sig=prox.siguiente;
                if (sig!=null)
                    sig.anterior=aux;
                persona = "[" + prox.persona.getNombre() + " " + prox.persona.getApellido1() + ", "
						+ prox.persona.getDni() + "] ";
            }
            JOptionPane.showMessageDialog(null, persona, "Persona",
					JOptionPane.INFORMATION_MESSAGE);
        }
        else
        	JOptionPane.showMessageDialog(null, "No existe la posición especificada", "Error",
					JOptionPane.ERROR_MESSAGE);
    }

	/**
	 * Método para mostrar las personas listadas de inicio a fin
	 */
	public void mostrarListaInicioFin() {
		if (!estaVacia()) {
			String datosPersonas = "";
			Nodo aux = nodoInicio;
			while (aux != null) {
				datosPersonas = datosPersonas + "[" + aux.persona.getNombre() + " " + aux.persona.getApellido1() + ", "
						+ aux.persona.getDni() + "] ";
				aux = aux.siguiente;
			}
			JOptionPane.showMessageDialog(null, datosPersonas, "Lista de personas de inicio a fin",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Método para mostrar las personas listadas de fin a inicio
	 */
	public void mostrarListaFininicio() {
		if (!estaVacia()) {
			String datosPersonas = "";
			Nodo aux = nodoFin;
			while (aux != null) {
				datosPersonas = datosPersonas + "[" + aux.persona.getNombre() + " " + aux.persona.getApellido1() + ", "
						+ aux.persona.getDni() + "] ";
				aux = aux.anterior;
			}
			JOptionPane.showMessageDialog(null, datosPersonas, "Lista de personas de fin a inicio",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Método que borra una persona en la posición indicada
	 * @param pos
	 */
    public void borrar (int pos)
    {
        if (pos <= tamaño())    {
            if (pos == 0) {
                nodoInicio = nodoInicio.siguiente;
                if (nodoInicio!=null)
                    nodoInicio.anterior=null;
            } else {
                aux = nodoInicio;
                for (int i = 1 ; i <= pos ; i++)
                    aux = aux.siguiente;
                Nodo prox = nodoInicio.siguiente;
                prox=prox.siguiente;
                aux.siguiente = prox;
                if (prox!=null)
                    prox.anterior=aux;
            }
        }
    }

	/**
	 * Método para eliminar los componentes de la lista
	 */
	public void vaciarLista() {
		if (!estaVacia())
			this.nodoInicio = this.nodoFin = null;
	}

	/**
	 * Método que devuelve el tamaño de la lista
	 * 
	 * @return
	 */
	public int tamaño() {
		int toret = 0;
		aux = nodoInicio;
		while (aux != null) {
			aux = aux.siguiente;
			toret++;
		}

		return toret;
	}

	/**
	 * Método que busca un DNI en la lista, si existe devuelve true
	 * 
	 * @param x
	 * @return boolean
	 */
	public boolean existeDNI(String dni) {
		aux = nodoInicio;
		while (aux != null) {
			if (aux.persona.getDni().equals(dni))
				return true;
			aux = aux.siguiente;
		}
		return false;
	}
	
	/**
	 * Método que busca un DNI en la lista, y devuelve su posición
	 * 
	 * @param x
	 * @return boolean
	 */
	public int obtenerPosicionDNI(String dni) {
		aux = nodoInicio;
		int pos = -1;
		boolean encontrado = false;
		while (aux != null || encontrado) {
			if (aux.persona.getDni().equals(dni))
				encontrado=true;
			else
				pos++;
			aux = aux.siguiente;
			
		}
		return pos;
	}

}
