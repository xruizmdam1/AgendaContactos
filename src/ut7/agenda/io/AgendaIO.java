/** 
 * @author Xabier, Catarina, Anthonny
 */

package ut7.agenda.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import ut7.agenda.modelo.AgendaContactos;
import ut7.agenda.modelo.Contacto;
import ut7.agenda.modelo.Personal;
import ut7.agenda.modelo.Profesional;
import ut7.agenda.modelo.Relacion;

/**
 * Utilidades para cargar la agenda
 */

public class AgendaIO {

	public static int importar(AgendaContactos agenda, String nombre) {
		File f = new File(nombre);
		BufferedReader entrada = null;
		int contador = 0;
		try {
			entrada = new BufferedReader(new FileReader(f));
			String linea = entrada.readLine();
			while (linea != null) {
				agenda.añadirContacto(parsearLinea(linea));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error al leer linea: " + e.getMessage());
			contador++;
		} finally {
			try {
				entrada.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar entrada: " + e.getMessage());
			}
		}
		return contador;
	}

	public static void exportarPersonales(AgendaContactos agenda, String nombre) throws IOException {

		PrintWriter entrada = null;
		entrada = new PrintWriter(new BufferedWriter(new FileWriter(nombre)));
		entrada.print((agenda));
		entrada.close();
	}

	
	private static Contacto parsearLinea(String linea) {

		String[] datos = linea.split("\\,+"); // split para separar los parametros
		int tipoDato = Integer.parseInt(datos[0].trim());
		if (tipoDato != 1) { 
			return new Personal(datos[1].trim(),datos[2].trim(),datos[3].trim(),
					datos[4].trim(),datos[5].trim(),Relacion.valueOf(datos[6].trim()));
		} 
		return new Profesional(datos[1].trim(),datos[2].trim(),datos[3].trim(),datos[4].trim(),datos[5].trim());
		
	}
}
