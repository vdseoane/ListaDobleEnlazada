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

//M�todo para saber si la lista tiene elementos
	public boolean estaVacia() {
		return nodoInicio == null;
	}

	/**
	 * A�adimos una nueva persona al final de la lista
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
	 * A�adimos una nueva persona al inicio de la lista
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
	 * M�todo para insertar en una posici�n espec�fica siempre y cuando exista
	 * 
	 * @param pos
	 * @param x
	 */
	void insertarEnPosicion(int pos, Persona persona) {
		if (pos <= tama�o()) {
			Nodo nodoInsertar = new Nodo(persona);
			if (pos == 0) {
				nodoInsertar.siguiente = nodoInicio;
				if (nodoInicio != null)
					nodoInicio.anterior = nodoInsertar;
				nodoInicio = nodoInsertar;
			} else if (pos == tama�o()) {
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
	 * M�todo que obtiene una persona en una posici�n espec�fica
	 * @param pos
	 * @return
	 */
	public Persona obtenerDePosicion (int pos) {
		Persona persona = null;
        if (pos <= tama�o() && pos!=Utils.ENTERO_NULO)    {
            if (pos == 0) {
                persona = nodoInicio.persona;
            } else {
                aux = nodoInicio;
                for (int i = 1 ; i <= pos ; i++)
                    aux = aux.siguiente;
                
            }if(aux!=null)
            persona = aux.persona;
        }
        return persona;
    }

	/**
	 * M�todo para mostrar las personas listadas de inicio a fin
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
	 * M�todo para mostrar las personas listadas de fin a inicio
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
	 * M�todo que borra una persona en la posici�n indicada
	 * @param pos
	 */
    public boolean borrar (int pos)
    {
    	boolean toret = false;
        if (pos <= tama�o() && pos!=Utils.ENTERO_NULO)    {
            if (pos == 0) {
                nodoInicio = nodoInicio.siguiente;
                if (nodoInicio!=null)
                    nodoInicio.anterior=null;
                toret=true;
            } else {
                aux = nodoInicio;
                for (int i = 1 ; i <= pos-2 ; i++)
                	aux = aux.siguiente;
                Nodo prox = aux.siguiente;
                aux.siguiente = prox.siguiente;
                Nodo siguiente=prox.siguiente;
                if (siguiente!=null)
                    siguiente.anterior=aux;
                toret=true;
            }
        }
        
        return toret;
    }

	/**
	 * M�todo para eliminar los componentes de la lista
	 */
	public void vaciarLista() {
		if (!estaVacia())
			this.nodoInicio = this.nodoFin = null;
	}

	/**
	 * M�todo que devuelve el tama�o de la lista
	 * 
	 * @return
	 */
	public int tama�o() {
		int toret = 0;
		aux = nodoInicio;
		while (aux != null) {
			aux = aux.siguiente;
			toret++;
		}

		return toret;
	}

	/**
	 * M�todo que busca un DNI en la lista, si existe devuelve true
	 * 
	 * @param x
	 * @return boolean
	 */
	public boolean existeDNI(String dni) {
		aux = nodoInicio;
		while (aux != null) {
			if (aux.persona!=null && aux.persona.getDni().equals(dni))
				return true;
			aux = aux.siguiente;
		}
		return false;
	}
	
	/**
	 * M�todo que busca un DNI en la lista, y devuelve su posici�n
	 * 
	 * @param x
	 * @return boolean
	 */
	public int obtenerPosicionDNI(String dni) {
		aux = nodoInicio;
		int pos = Utils.ENTERO_CERO;
		boolean encontrado = false;
		while (aux != null && !encontrado) {
			if (aux.persona.getDni().equals(dni))
				encontrado=true;
			else
				pos++;
			aux = aux.siguiente;

			
		}
		return pos;
	}

}
