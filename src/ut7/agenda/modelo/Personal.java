package ut7.agenda.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/* 
 * @author CATARINA
 */

public class Personal extends Contacto {

	private LocalDate fechaNac;
	private Relacion relacion;

	public Personal(String nombre, String apellidos, String telefono, String email, String fechaNac,
			Relacion relacion) {
		super(nombre, apellidos, telefono, email);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.fechaNac = LocalDate.parse(fechaNac, dtf);
		this.relacion = relacion;

	}

	public boolean esCumpleaños(String fecha) {
		return fechaNac.isEqual(LocalDate.now());
	}

	public String getFirmaEmail() {
		return "¡Un abrazo!";
	}

	public String toString() {
		super.toString();
		return ("Fecha Nacimiento: " + fechaNac + "\n" + "Relación: " + relacion);
	}
}
