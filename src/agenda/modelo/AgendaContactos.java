/**
 * @author Xabier, Catarina, Anthonny
 */

package agenda.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class AgendaContactos {
	private Map<Character, Set<Contacto>> agenda;

	public AgendaContactos() {
		agenda = new TreeMap<>();
	}

	public void añadirContacto(Contacto contacto) {
		if (!agenda.containsKey(contacto.getPrimeraLetra())) {
			HashSet<Contacto> hs = new HashSet<>();
			hs.add(contacto);
			agenda.put(contacto.getPrimeraLetra(), hs);
		} else {
			agenda.get(contacto.getPrimeraLetra()).add(contacto);
		}
	}

	public ArrayList<Contacto> contactosEnLetra(char letra) {
		ArrayList<Contacto> contactosEnLetra = new ArrayList<>();
		letra = Character.toUpperCase(letra);
		if (agenda.containsKey(letra)) {
			Set<Contacto> contactos = agenda.get(letra);
			Iterator<Contacto> it = contactos.iterator();
			while (it.hasNext()) {
				Contacto contacto = it.next();
				contactosEnLetra.add(contacto);
			}
		}
		return contactosEnLetra;
	}

	public int totalContactos() {
		int total = 0;
		for (Map.Entry<Character, Set<Contacto>> contactos : agenda.entrySet()) {
			for (Contacto contacto : contactos.getValue()) {
				total++;
			}
		}
		return total;
	}

	@Override
	public String toString() {
		String str = getClass().getSimpleName() + "\n";

		Set<Map.Entry<Character, Set<Contacto>>> entradas = agenda.entrySet();

		for (Entry<Character, Set<Contacto>> entry : entradas) {
			str += "\n" + entry.getKey() + " (" + entry.getValue().size() + " contacto/s)\n---------------------\n";
			Set<Contacto> valores = entry.getValue();
			for (Contacto valor : valores) {
				str += valor.toString() + "\n";
			}
		}
		str += "---------------------\n(" + totalContactos() + " Contacto/s)\n";
		return str;
	}

	public List<Contacto> buscarContactos(String texto) {
		ArrayList<Contacto> buscaContactos = new ArrayList<>();

		Set<Map.Entry<Character, Set<Contacto>>> grupo = agenda.entrySet();
		Iterator<Map.Entry<Character, Set<Contacto>>> it = grupo.iterator();
		while (it.hasNext()) {
			Map.Entry<Character, Set<Contacto>> map = it.next();
			for (Contacto contacto : map.getValue()) {
				if (contacto.getApellidos().contains(texto) || contacto.getNombre().contains(texto)) {
					buscaContactos.add(contacto);
				}
			}
		}
		return buscaContactos;
	}

	public List<Personal> personalesEnLetra(char letra) {
		letra = Character.toUpperCase(letra);
		ArrayList<Personal> personalesEnLetra = new ArrayList<>();
		Set<Contacto> contactos = agenda.get(letra);
		if (contactos == null) {
			return null;
		}
		for (Contacto contacto : contactos) {
			if (contacto instanceof Personal) {
				if (contacto.getPrimeraLetra().equals(letra)) {
					personalesEnLetra.add((Personal) contacto);
				}
			}
		}
		return personalesEnLetra;
	}

	public List<Personal> felicitar() {
		ArrayList<Personal> felicitados = new ArrayList<>();
		Iterator<Map.Entry<Character, Set<Contacto>>> it = agenda.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<Character, Set<Contacto>> cumpleaños = it.next();

			for (Contacto contacto : cumpleaños.getValue()) {

				if (contacto instanceof Personal) {
					if (contacto.esCumpleaños()) {
						felicitados.add((Personal) contacto);
					}
				}
			}
		}
		return felicitados;
	}

	public Map<Relacion, List<String>> personalesPorRelacion() {

		Map<Relacion, List<String>> mapita = new TreeMap<>();
		for (Character i : agenda.keySet()) {
			Iterator<Contacto> it = agenda.get(i).iterator();
			while (it.hasNext()) {
				Contacto c = it.next();
				if (c instanceof Personal) {
					String cadena = c.getNombre() + ' ' + c.getApellidos();
					Relacion relacion = ((Personal) c).getRelacion();
					if (mapita.containsKey(relacion)) {
						mapita.get(relacion).add(cadena);
					} else {
						List<String> contactos = new ArrayList<>();
						contactos.add(cadena);
						mapita.put(relacion, contactos);
					}
				}
			}
		}
		return mapita;

	}

	public List<Personal> personalesOrdenadosPorFechaNacimiento(char letra) {

		List<Personal> lista = new ArrayList<>();
		if (!agenda.containsKey(letra)) {
			return null;
		}
		lista = personalesEnLetra(letra);
		Collections.sort(lista, new Comparator<Personal>() {
			public int compare(Personal personal1, Personal personal2) {
				return personal1.getFechaNac().compareTo(personal2.getFechaNac());
			}

		});
		return lista;
	}
}