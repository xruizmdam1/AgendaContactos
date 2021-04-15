package ut7.agenda.io;

import ut7.agenda.modelo.AgendaContactos;
import ut7.agenda.modelo.Contacto;
import ut7.agenda.modelo.Personal;
import ut7.agenda.modelo.Profesional;
import ut7.agenda.modelo.Relacion;

/*
 * author ANTHONNY
 */
/**
 * Utilidades para cargar la agenda
 */
public class AgendaIO {

	public static void importar(AgendaContactos agenda) {
		String[] datos = obtenerLineasDatos(); // se crea un array que contendra todos los datos del método
		for (String linea : datos) {
			Contacto contactoNuevo = parsearLinea(linea); // llamamos al método parseaLinea y metemos el String creado
			agenda.añadirContacto(contactoNuevo); // llamamos al método añadirContacto de la clase AgendaContactos
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	private static Contacto parsearLinea(String linea) {

		String[] datos = linea.split(","); // split para separar los parametros
		int tipoDato = Integer.parseInt(datos[0].trim());
		String nombre = datos[1].trim(); // variable para parametro nombre de la clase Personal
		String apellidos = datos[2].trim();// variable para parametro apellidos de la clase Personal
		String telefono = datos[3].trim();// variable para parametro telefono de la clase Personal
		String email = datos[4].trim();// variable para parametro email de la clase Personal
		String fecha = datos[5].trim();// variable para parametro fecha nacimiento de la clase Personal

		Relacion relacion = null; // Variable de tipo Relacion
		if (tipoDato != 1) { // si el tipo de dato no es uno
			for (Relacion rel : Relacion.values()) { // entramos en un bucle que recorrera toda la clase Relacion
				if (rel.equals(datos[6].trim().toUpperCase())) {
					relacion = rel;
				}
			}
			Personal personal = new Personal(nombre, apellidos, telefono, email, fecha, relacion);
			return personal;
		} else {
			String empresa = datos[5].trim();
			Profesional profesional = new Profesional(nombre, apellidos, telefono, email, empresa);
			return profesional;
		}

	}

	/**
	 * 
	 * @return un array de String con todas las líneas de información de todos los
	 *         contactos. 1 significa contacto profesional, 2 significa contacto
	 *         personal
	 */
	private static String[] obtenerLineasDatos() {
		return new String[] { "1, Isabel, Acosta Mendioroz,  678895433 ,  iacostamen@gmail.com ,  walden estrella ",
				"2,  pedro , urruti tello , 616789654 ,  urrutitello@gmail.com , 09/03/2007, amigos",
				"1, Angel , Esteban Grande , 674544123 ,  aestebang@gmail.com ,  magma publicidad ",
				"2, elena , bueno ganuza , 6786547699 ,  ebuenogan@gmail.com , 17/03/2000, amigos",
				"2, amaia , romero sein , 642222343 ,  aromerosein@gmail.com , 09/03/2012, pareja",
				"2, Ignacio ,  Anto roth ,  688912799 , iantoroth@gmail.com ,  11/11/1969 , padre",
				"1,  Isabel ,  Acosta Marin , 678895433 ,  iacostamar@gmail.com ,  publicidad holdings ",
				"1 ,    roberto , casas maura , 666777888 ,  rocasasma@gmail.com ,  strato banca ",
				"1,juan maria, garcía oliva, 699898111, jmgarcioliva@gmail.com, conway motor ",
				"2, pedro , urruti tello , 616789654 ,  urrutitello@gmail.com , 17/03/2000, amigos",
				"1,marta, sanz iris, 622999876, msanzi@gmail.com, jump literatura ",
				"1,javier, porto luque, 691256777 , japorlu@gmail.com, gas natural ",
				"1,pablo, ponce larraoz, 689123456, pabloponce@gmail.com, la caixa",
				"1, javier, marin lancho, 666666666, jruizlanchoe@gmail.com, bbva",
				"1,juan maria, garcía oliva, 699898111, jmgarcioliva@gmail.com, conway motor ",
				"2, Berta ,  andia solano ,  621123345 , bandiasol@gmail.com ,  12/12/1999 ,  HIJA",
				"2, Ignacio ,  Anto roth ,  688912799 , iantoroth@gmail.com ,  11/11/1969 , padre",
				"  1,  roberto , casas maura , 666777888 ,  rocasasma@gmail.com ,  strato banca ",
				" 2, daniel , martin martin , 678901234 ,  damrtinmartin@gmail.com , 15/07/1980, amigos",
				"  2, pablo , martin abradelo , 667788899 ,  martinabra@gmail.com , 31/01/2010, amigos",
				"  2, susana , santaolalla bilbao , 676767676 ,  ssantaolalla@gmail.com , 17/03/1998, amigos",
				"  2, adur ,  martin merino ,  611112113 , adurmartinme@gmail.com ,  14/02/2003 , amigos" };

	}

}
