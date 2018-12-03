import java.awt.TextField;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ListaDoblePersonas {

	public static void main(String[] args) {
		ListaPersonas listaPersonas = new ListaPersonas();

		int opcion = 0, elemento;
		JTextField nombre = new JTextField();
		JTextField apellido1 = new JTextField();
		JTextField apellido2 = new JTextField();
		;
		JTextField dni = new JTextField();
		Object[] mensaje = { "Nombre: ", nombre, "Primer apellido: ", apellido1, "Segundo apellido: ", apellido2,
				"DNI: ", dni };

		do {
			try {
				opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
						"1. Agregar persona al incio \n" + "2. Agregar persona al final \n"
								+ "3. Mostrar lista de principio a fin \n" + "4. Mostrar lista de fin a inicio \n"
								+ "5. Salir \n",
						"Opciones", JOptionPane.INFORMATION_MESSAGE));

				switch (opcion) {
				case 1:
					opcion = JOptionPane.showConfirmDialog(null, mensaje, "Añadir persona al inicio",
							JOptionPane.OK_CANCEL_OPTION);
					if (opcion == JOptionPane.OK_OPTION) {
						Persona persona = new Persona(nombre.getText(), apellido1.getText(), apellido2.getText(),
								dni.getText());
						listaPersonas.agergarPersonaInicio(persona);
					}
					break;
				case 2:
					opcion = JOptionPane.showConfirmDialog(null, mensaje, "Añadir persona al final",
							JOptionPane.OK_CANCEL_OPTION);
					if (opcion == JOptionPane.OK_OPTION) {
						Persona persona = new Persona(nombre.getText(), apellido1.getText(), apellido2.getText(),
								dni.getText());
						listaPersonas.agergarPersonaFinal(persona);
					}
					break;
				case 3:
					if (!listaPersonas.estaVacia()) {
						listaPersonas.mostrarListaInicioFin();
					} else {
						JOptionPane.showMessageDialog(null, "No hay personas en la lista", "Listado vacío",
								JOptionPane.INFORMATION_MESSAGE);
					}
					break;
				case 4:
					if (!listaPersonas.estaVacia()) {
						listaPersonas.mostrarListaFininicio();
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

		} while (opcion != 5);

	}

}
