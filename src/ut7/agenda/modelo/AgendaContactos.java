package ut7.agenda.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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

		return null;

	}

	public List<Personal> personalesEnLetra(char letra) {

		return null;
	}

	public List<Personal> felicitar() {
		List<Personal> lista = new ArrayList<Personal>();
		String fecha = "";
		for(Personal persona : lista) {
			if(persona.esCumpleaños( fecha)) {
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
