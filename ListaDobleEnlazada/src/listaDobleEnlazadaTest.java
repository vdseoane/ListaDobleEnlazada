

import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class listaDobleEnlazadaTest {
	/*
	 * @BeforeClass: Es usado para escribir código que correra antes de todas las
	 * pruebas.
	 * 
	 * @Before: Código que correra antes de cada uno de los tests
	 * 
	 * @Test: Código de la prueba unitaria
	 * 
	 * @After: Código que corre antes de cada una de las pruebas
	 * 
	 * @AfterClass: Código que corre despues de todas las pruebas.
	 */
	
	private static ListaPersonas listadoPersonas;
	private static Persona persona1;
	private static Persona persona2;
	private static Persona persona3;
	private static Logger log;
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("Iniciando bateria de test");
		System.out.println("Creando listado de personas");
		
		listadoPersonas = new ListaPersonas();
	}

	/**
	 * Se crean las personas de nuevo antes de cada test
	 */
	@Before
	public void before() {
		System.out.println("Creando personas para listado");
		
		crearPersonas();
		insertarPersonasEnLista(persona1, persona2, persona3);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void insertarPersonaInicioTest() {
		System.out.println("Insertando persona al incio");
		Persona persona = new Persona("Persona", "Inicio", "Test", "41290443S");
		
		listadoPersonas.agergarPersonaInicio(persona);
		
		Persona personaEsperada = listadoPersonas.obtenerDePosicion(Utils.ENTERO_CERO);
		
		Assert.assertNotNull(personaEsperada);
		Assert.assertEquals(persona.getNombre(), personaEsperada.getNombre());
		Assert.assertEquals(persona.getDni(), personaEsperada.getDni());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void insertarPersonaFinalTest() {
		System.out.println("Insertando persona al final");
		Persona persona = new Persona("Persona", "Final", "Test", "41290443S");
		
		listadoPersonas.agergarPersonaFinal(persona);
		
		Persona personaEsperada = listadoPersonas.obtenerDePosicion(listadoPersonas.tamaño()-1);
		
		Assert.assertNotNull(personaEsperada);
		Assert.assertEquals(persona.getNombre(), personaEsperada.getNombre());
		Assert.assertEquals(persona.getDni(), personaEsperada.getDni());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void insertarPersonaPosicionTest() {
		int pos = 2;
		System.out.println("Insertando persona en posición: " + pos);
		Persona persona = new Persona("Persona", "Pos2", "Test", "41290443S");
		
		listadoPersonas.insertarEnPosicion(pos, persona);
		
		Persona personaEsperada = listadoPersonas.obtenerDePosicion(pos);
		
		Assert.assertNotNull(personaEsperada);
		Assert.assertEquals(persona.getNombre(), personaEsperada.getNombre());
		Assert.assertEquals(persona.getDni(), personaEsperada.getDni());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void existeDNITrueTest() {
		System.out.println("Comprobando DNI true");
		
		Assert.assertTrue(listadoPersonas.existeDNI(persona1.getDni()));

	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void existeDNIFalseTest() {
		String dniFalso="00000000A";
System.out.println("Comprobando DNI false");
		
		Assert.assertFalse(listadoPersonas.existeDNI(dniFalso));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void borrarPersonaEspecificaTest() {
		int pos = 2;
		System.out.println("Borrando persona en posición: " + pos);
		
		Assert.assertTrue(listadoPersonas.borrar(pos));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void vaciarListadoTest() {
		System.out.println("Vaciando listado");
		int tamañoEsperado = Utils.ENTERO_CERO;
		listadoPersonas.vaciarLista();
		
		Assert.assertEquals(tamañoEsperado, listadoPersonas.tamaño());
	}

	/**
	 * Se eliminan los casos de prueba para volver a generarlos
	 */
	@After
	public void after() {
		System.out.println("Eliminando listado de personas");
		vaciarListado();
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("Eliminando listado de personas");
		listadoPersonas = null;
	}
	
	private void crearPersonas() {
		persona1 = new Persona("Pepe", "Sanchez", "Diaz", "93809558X");
		persona1 = new Persona("Paco", "Ferreiro", "Nogueiras", "33496135Q");
		persona1 = new Persona("Manolo", "Seoane", "Puga", "63824222G");
	}
	
	private void insertarPersonasEnLista(Persona persona1, Persona persona2, Persona persona3) {
		listadoPersonas.agergarPersonaInicio(persona3);
		listadoPersonas.agergarPersonaInicio(persona2);
		listadoPersonas.agergarPersonaInicio(persona1);	
	}
	
	private void vaciarListado() {
		listadoPersonas.vaciarLista();
	}
}
