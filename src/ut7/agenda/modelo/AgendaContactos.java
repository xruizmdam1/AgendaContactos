package ut7.agenda.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* 
 * @author XABIER, CATARINA, ANTHONNY
 */

public class AgendaContactos {
	private Map<Character, Set<Contacto>> agenda;

	public AgendaContactos() {
		agenda = new TreeMap<Character, Set<Contacto>>();
	}

	public void añadirContacto() {

	}

	public void contactosEnLetra() {

	}

	public void totalContactos() {

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(false);

		return sb.toString();
	}

	public List<Contacto> buscarContactos(String texto) {
		ArrayList<Contacto> buscarContactos = new ArrayList<>();

		Set<Map.Entry<Character, Set<Contacto>>> grupo = agenda.entrySet();
		Iterator<Map.Entry<Character, Set<Contacto>>> it = grupo.iterator();
		while (it.hasNext()) {
			Map.Entry<Character, Set<Contacto>> map = it.next();
			for (Contacto contacto : map.getValue()) {
				if (contacto.getApellidos().equals(texto) || contacto.getNombre().equals(texto)) {
					buscarContactos.add(contacto);
				}
			}
		}
		return buscarContactos;
	}

	public List<Personal> personalesEnLetra(char letra) {
		ArrayList<Personal> personalesEnLetra = new ArrayList<>();

		Set<Map.Entry<Character, Set<Contacto>>> grupito = agenda.entrySet();
		Iterator<Map.Entry<Character, Set<Contacto>>> it = grupito.iterator();
		while (it.hasNext()) {
			Map.Entry<Character, Set<Contacto>> mapita = it.next();
			for (Contacto contacto : mapita.getValue()) {

			}
		}
		return null;
	}

	public List<Personal> felicitar() {
		ArrayList<Personal> lista = new ArrayList<Personal>();
		String fecha = "";
		for (Personal persona : lista) {
			if (persona.esCumpleaños(fecha)) {
				lista.add(persona);
			}
		}

		return lista;
	}

	public void personalesPorRelacion() {

	}

	public List<Personal> personalesOrdenadosPorFechaNacimiento(char letra) {

		return null;

	}

}
