package ut7.agenda.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AgendaContactos {
	private Map<Character, Set<Contacto>> agenda;

	public AgendaContactos() {
		agenda = new TreeMap<>();
	}

	public void añadirContacto(Contacto contacto) {
		if (!agenda.containsKey(contacto.getNombre())){
			HashSet<Contacto> hs = new HashSet<>();
			hs.add(contacto);
			agenda.put(contacto.getPrimeraLetra(), hs);	
		}
	}

	public void contactosEnLetra(char letra) {
		ArrayList contactos = new ArrayList<>();
		Set<Map.Entry<Character, Set<Contacto>>> grupo = agenda.entrySet();
		Iterator<Map.Entry<Character, Set<Contacto>>> it = grupo.iterator();
		while (it.hasNext()) {
			Map.Entry<Character, Set<Contacto>> map = it.next();
			for(Contacto contacto : map.getValue()) {
				if (contacto.getPrimeraLetra().equals(letra)) {
					contactos.add(contacto);
				}
			}
		}
	}

	public void totalContactos() {
		int total = 0;
		Set<Character> claves = agenda.keySet();
		for(Character clave : claves) {
			total++;
		}
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
		ArrayList<Personal> lista = new ArrayList<Personal>();
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
