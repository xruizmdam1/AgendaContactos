package ut7.agenda.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Personal extends Contacto {

	private LocalDate fechaNac;
	private String fechaCorrecta;
	private Relacion relacion;

	public Personal(String nombre, String apellidos, String telefono, String email, String fecha, Relacion relacion) {
		super(nombre, apellidos, telefono, email);
		this.fechaNac = fechaNac;
		this.relacion = relacion;

		fechaNac = LocalDate.parse(fecha);
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd MMM yyyy");
		fechaCorrecta = fechaNac.format(formateador);
	}

	public String toString() {
		super.toString();
		return ("Fecha Nacimiento: " + fechaCorrecta + "\n" + "Relación: " + relacion);
	}
}
