/** 
 * @author Xabier, Catarina, Anthonny
 */

package agenda.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

	public Relacion getRelacion() {
		return relacion;
	}

	public void setRelacion(Relacion relacion) {
		this.relacion = relacion;
	}

	public boolean esCumpleaños() {
		LocalDate hoy = LocalDate.now();
		return fechaNac.getDayOfMonth() == hoy.getDayOfMonth() && fechaNac.getMonth() == hoy.getMonth();
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getFirmaEmail() {
		return "¡Un abrazo!";
	}

	public String toString() {
		return super.toString() + "\nFecha Nacimiento: " + fechaNac + "\n" + "Relación: " + relacion + "\n";
	}

	
}
