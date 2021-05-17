/** 
 * @author Xabier, Catarina, Anthonny
 */

package agenda.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

import agenda.modelo.AgendaContactos;
import agenda.modelo.Contacto;
import agenda.modelo.Personal;
import agenda.modelo.Profesional;
import agenda.modelo.Relacion;

/**
 * Utilidades para cargar la agenda de contactos
 */

public class AgendaIO {

	/**
	 * Se extrae la información del fichero que se proporciona y se añade el
	 * contacto a la agenda haciendo uso de un método de la clase AgendaContactos
	 * 
	 * @param agenda
	 * @param nombre
	 * @return
	 */
	public static int importar(AgendaContactos agenda, String nombre) {
		InputStream input = AgendaIO.class.getClassLoader().getResourceAsStream(nombre);
		BufferedReader entrada = null;
		int contador = 0;
		try {
			entrada = new BufferedReader(new InputStreamReader(input));
			String linea = entrada.readLine();
			while (linea != null) {
				agenda.añadirContacto(parsearLinea(linea));
				linea = entrada.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error al leer linea: " + e.getMessage());
			contador++;
		} catch (IllegalArgumentException e) {
			System.out.println("Error en argumentos: " + e.getMessage());
		} finally {
			try {
				entrada.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar entrada: " + e.getMessage());
			}
		}
		return contador;
	}

	/**
	 * Guardamos los contactos personales agrupados por la relación en el fichero
	 * 
	 * @param agenda
	 * @param nombre
	 * @throws IOException
	 */
	public static void exportarPersonales(AgendaContactos agenda, String nombreF) throws IOException {

		File f = new File(nombreF);
		PrintWriter salida = null;
		salida = new PrintWriter(new BufferedWriter(new FileWriter(f)));
		Map<Relacion, List<String>> personales = agenda.personalesPorRelacion();
		for (Relacion relacion : personales.keySet()) {
			salida.println(relacion + "\n " + personales.get(relacion).toString() + "\n");
		}
		salida.close();
	}

	/**
	 * Tiene un parametro de tipo String de la cual vamos a extraer sus datos para
	 * crear un contacto
	 * 
	 * @param linea
	 * @throws DateTimeParseException
	 * @throws IllegalArgumentException
	 * @throws NumberFormatException
	 */
	private static Contacto parsearLinea(String linea)
			throws NumberFormatException, DateTimeParseException, IllegalArgumentException {
		String[] datos = linea.split("\\,+"); // split para separar los parametros
		int tipoDato = Integer.parseInt(datos[0].trim());
		if (tipoDato != 1) {
			return new Personal(datos[1].trim(), datos[2].trim(), datos[3].trim(), datos[4].trim(), datos[5].trim(),
					Relacion.valueOf(datos[6].trim()));
		}
		return new Profesional(datos[1].trim(), datos[2].trim(), datos[3].trim(), datos[4].trim(), datos[5].trim());
	}
}