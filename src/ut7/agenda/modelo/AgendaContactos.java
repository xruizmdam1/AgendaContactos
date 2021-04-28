/**
 * @author Xabier, Catarina, Anthonny
 */

package ut7.agenda.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		if (!agenda.containsKey(contacto.getNombre())) {
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
			for (Contacto contacto : map.getValue()) {
				if (contacto.getPrimeraLetra().equals(letra)) {
					contactos.add(contacto);
				}
			}
		}
	}

	public void totalContactos() {
		int total = 0;
		Set<Character> claves = agenda.keySet();
		for (Character clave : claves) {
			total++;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		ArrayList<Contacto> ar = new ArrayList<>();
		Set<Map.Entry<Character, Set<Contacto>>> set = agenda.entrySet();
		Iterator<Map.Entry<Character, Set<Contacto>>> it = set.iterator();
		while (it.hasNext()) {
			Map.Entry<Character, Set<Contacto>> map = it.next();
			for (Contacto contacto : map.getValue()) {
				sb.append(contacto.toString());
			}
		}
		return sb.toString();
	}

	public List<Contacto> buscarContactos(String texto) {
		ArrayList<Contacto> buscaContactos = new ArrayList<>();

		Set<Map.Entry<Character, Set<Contacto>>> grupo = agenda.entrySet();
		Iterator<Map.Entry<Character, Set<Contacto>>> it = grupo.iterator();
		while (it.hasNext()) {
			Map.Entry<Character, Set<Contacto>> map = it.next();
			for (Contacto contacto : map.getValue()) {
				if (contacto.getApellidos().equals(texto) || contacto.getNombre().contains(texto)) {
					buscaContactos.add(contacto);
				}
			}
		}
		return buscaContactos;
	}

	public List<Personal> personalesEnLetra(char letra) {
		ArrayList<Personal> personalesEnLetra = new ArrayList<>();

		Set<Map.Entry<Character, Set<Contacto>>> grupito = agenda.entrySet();
		Iterator<Map.Entry<Character, Set<Contacto>>> it = grupito.iterator();
		while (it.hasNext()) {
			Map.Entry<Character, Set<Contacto>> mapita = it.next();
			for (Contacto contacto : mapita.getValue()) {
				if (contacto instanceof Personal) {
					if (contacto.getPrimeraLetra().equals(letra)) {
						personalesEnLetra.add((Personal) contacto);
					}
				}
				return personalesEnLetra;
			}
		}
		return null;
	}

	public List<Personal> felicitar() {
		LocalDate fechaNac = LocalDate.now(); // se cre variabke de tipo localDate
		String fechaNacimiento = fechaNac.format(DateTimeFormatter.ofPattern("dd-MMM-yy")); // convierto la variable en
																							// un string
		// realice esto para poder argumentar parametros al llamar al método
		// escumpleaños
		ArrayList<Personal> felicitados = new ArrayList<>();
		Iterator<Map.Entry<Character, Set<Contacto>>> it = agenda.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<Character, Set<Contacto>> cumpleaños = it.next();

			for (Contacto contacto : cumpleaños.getValue()) {

				if (contacto instanceof Personal) {
					if (((Personal) contacto).esCumpleaños(fechaNacimiento) == true) {
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

		ArrayList<Personal> lista = new ArrayList<>();
		Collections.sort(lista, new Comparator<Personal>() {
			public int compare(Personal personal1, Personal personal2) {
				return personal1.getFechaNac().compareTo(personal2.getFechaNac());
			}

		});
		return lista;
	}

}
