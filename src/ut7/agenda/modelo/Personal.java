package ut7.agenda.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Personal extends Contacto {

	private LocalDate fechaNac;
	private String fechaCorrecta;
	private Relacion relacion;
	private String fecha;

	public Personal(String nombre, String apellidos, String telefono, String email, String fecha, Relacion relacion) {
		super(nombre, apellidos, telefono, email);
		this.fecha = fecha;
		this.relacion = relacion;

		fechaNac = LocalDate.parse(fecha);
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd MMM yyyy");
		fechaCorrecta = fechaNac.format(formateador);
	}

	public boolean esCumpleaños(String fecha) {
		LocalDate ld = LocalDate.parse(fecha);
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd MMM yyyy");
		String fechaCumple = ld.format(formateador);
		return fechaCorrecta == fechaCumple;

	}

	public String toString() {
		super.toString();
		return ("Fecha Nacimiento: " + fechaCorrecta + "\n" + "Relación: " + relacion);
	}
}
