import java.awt.TextField;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Inicio {

	static int opcion = 0;
	static int elemento = 0;
	static int posicionEscogida = 0;
	static Persona persona;
	static ListaPersonas listaPersonas = new ListaPersonas();
	static JTextField nombre = new JTextField();
	static JTextField apellido1 = new JTextField();
	static JTextField apellido2 = new JTextField();
	static JTextField dni = new JTextField();
	static JTextField pos = new JTextField();
	static JTextField opcionMenu = new JTextField();
	static Object[] mensaje = { "Nombre: ", nombre, "Primer apellido: ", apellido1, "Segundo apellido: ", apellido2,
			"DNI: ", dni };
	static Object[] posicion = { "Posición ", pos };
	static Object[] dniMensaje = { "DNI de la persona: ", dni };
	static Object[] menu = { "1. Agregar persona al incio \n 2. Agregar persona al final \n	3. Agregar persona en posición específica \n 4. Mostrar lista de principio a fin \n 5. Mostrar lista de fin a inicio \n 6. Mostrar persona en posición específica \n 7. Buscar DNI \n 8. Borrar persona en posición específica \n 9. Vaciar listado \n 10. Salir \n", opcionMenu };

	public static void main(String[] args) {

		do {
			try {
				opcion = iniciarMenu();
				switch (opcion) {
				case 1:
					agregarPersona(Utils.INICIO, Utils.ENTERO_NULO);
					break;
				case 2:
					agregarPersona(Utils.FINAL, Utils.ENTERO_NULO);
					break;
				case 3:
					posicionEscogida = pedirPosicion();
					agregarPersona(Utils.POSICION, posicionEscogida);

					break;
				case 4:
					if (!listaPersonas.estaVacia()) {
						listaPersonas.mostrarListaInicioFin();
					} else {
						JOptionPane.showMessageDialog(null, "No hay personas en la lista", "Listado vacío",
								JOptionPane.INFORMATION_MESSAGE);
					}
					break;
				case 5:
					if (!listaPersonas.estaVacia()) {
						listaPersonas.mostrarListaFininicio();
					} else {
						JOptionPane.showMessageDialog(null, "No hay personas en la lista", "Listado vacío",
								JOptionPane.INFORMATION_MESSAGE);
					}
					break;
				case 6:
					posicionEscogida = pedirPosicion();
					persona=listaPersonas.obtenerDePosicion(posicionEscogida);
					 String texto = "[" + persona.getNombre() + " " + persona.getApellido1() + ", "
								+ persona.getDni() + "] ";
			            JOptionPane.showMessageDialog(null, texto, "Persona",
								JOptionPane.INFORMATION_MESSAGE);
					break;
				case 7:
					pedirDNI();
					break;
				case 8:
					posicionEscogida = pedirPosicion();
					Boolean borrado = listaPersonas.borrar(posicionEscogida);
					if(borrado)
						JOptionPane.showMessageDialog(null, "Se ha eliminado la persona indicada",
								"Persona borrada", JOptionPane.INFORMATION_MESSAGE);
					break;
				case 9:
					if (!listaPersonas.estaVacia()) {
						listaPersonas.vaciarLista();
						JOptionPane.showMessageDialog(null, "Se han eliminado todas las personas de la lista",
								"Personas borradas", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "No hay personas en la lista", "Listado vacío",
								JOptionPane.INFORMATION_MESSAGE);
					}
					break;

				default:
					break;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
			}

		} while (opcion != 10);

	}

	/**
	 * Inicializa el menu de opciones
	 * @return
	 */
	private static int iniciarMenu() {
		opcion = JOptionPane.showConfirmDialog(null, menu,
				"Opciones", JOptionPane.OK_CANCEL_OPTION);
		
		if (opcion == JOptionPane.OK_OPTION) {
			opcion = Integer.parseInt(opcionMenu.getText());
		}else {
			opcion= Utils.ENTERO_10;
		}
		
		return opcion;
	}

	/**
	 * Solicita una posición del listado, comprobando que sea válida
	 * @return
	 */
	private static int pedirPosicion() {
		int toret = Utils.ENTERO_NULO;
		int tamaño = listaPersonas.tamaño();
		opcion = JOptionPane.showConfirmDialog(null, posicion, "Añadir persona en posición determinada",
				JOptionPane.OK_CANCEL_OPTION);
		if (opcion == JOptionPane.OK_OPTION) {
			//Debemos obtener la posicion real de la lista
			int posicionEscogida = Integer.parseInt(pos.getText()) -1;
			if (posicionEscogida < 0 || posicionEscogida > listaPersonas.tamaño()-1)
				JOptionPane.showMessageDialog(null, "Posoción no valida. Escoga una posición entre 1 y " + tamaño,
						"Posición no válida", JOptionPane.INFORMATION_MESSAGE);
			else
				toret = posicionEscogida;
		}else {
			JOptionPane.getRootFrame().dispose();
		}

		return toret;
	}

	/**
	 * Muestra el cuadro para incluir una nueva persona y llama al método correspondiente según la opción seleccionada
	 * @param lugar
	 * @param posicionEscogida
	 */
	private static void agregarPersona(int lugar, int posicionEscogida) {
		opcion = JOptionPane.showConfirmDialog(null, mensaje, "Añadir persona al inicio", JOptionPane.OK_CANCEL_OPTION);
		if (opcion == JOptionPane.OK_OPTION) {
			Persona persona = new Persona(nombre.getText(), apellido1.getText(), apellido2.getText(), dni.getText());
			switch (lugar) {
			case Utils.INICIO:
				listaPersonas.agergarPersonaInicio(persona);
				break;
			case Utils.FINAL:
				listaPersonas.agergarPersonaFinal(persona);
				break;
			case Utils.POSICION:
				listaPersonas.insertarEnPosicion(posicionEscogida, persona);
				break;
			}
		}else {
			JOptionPane.getRootFrame().dispose();
		}

	}

	/**
	 * Solicita a través de un diálogo el DNI a buscar
	 */
	private static void pedirDNI() {
		opcion = JOptionPane.showConfirmDialog(null, dniMensaje, "Introduce un DNI", JOptionPane.OK_CANCEL_OPTION);
		if (opcion == JOptionPane.OK_OPTION) {
			if(listaPersonas.existeDNI(dni.getText())) {
				persona = listaPersonas.obtenerDePosicion(listaPersonas.obtenerPosicionDNI(dni.getText()));
				 String texto = "[" + persona.getNombre() + " " + persona.getApellido1() + ", "
							+ persona.getDni() + "] ";
		            JOptionPane.showMessageDialog(null, texto, "Persona",
							JOptionPane.INFORMATION_MESSAGE);
			}
		}else {
			JOptionPane.getRootFrame().dispose();
		}
	}

}
