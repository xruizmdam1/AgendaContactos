/** 
 * @author Xabier, Catarina, Anthonny
 */

package agenda.modelo;

import java.time.LocalDate;

public abstract class Contacto implements Comparable<Contacto> {
	private String nombre;
	private String apellidos;
	private String telefono;
	private String email;

	public Contacto(String nombre, String apellidos, String telefono, String email) {
		this.nombre = nombre.toUpperCase();
		this.apellidos = apellidos.toUpperCase();
		this.telefono = telefono;
		this.email = email.toLowerCase();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return email.hashCode();
	}

	public Character getPrimeraLetra() {
		return apellidos.charAt(0);
	}

	public boolean equals(Contacto c) {
		if (c == null) {
			return false;
		}
		if (c == this) {
			return true;
		}
		if (c.getClass() != this.getClass()) {
			return false;
		}
		Contacto con = (Contacto) c;
		return con.getApellidos().equals(c.getApellidos()) && con.getNombre().equals(c.getNombre())
				&& con.getEmail().equals(c.getEmail());
	}

	public int compareTo(Contacto otro) {
		if (this.apellidos.equals(otro.apellidos)) {
			return 0;
		}
		if (this.apellidos.compareTo(otro.apellidos) > 0) {
			return 1;
		}
		return -1;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getApellidos() + ", " + getNombre() + " (" + getClass().getSimpleName().toUpperCase() + ")" + "\n" +
				 "Tfno: " + getTelefono() + " | " + "email: " + getEmail());
		return sb.toString();
	}

	public abstract boolean esCumpleaños();
	public abstract String getFirmaEmail();
	public abstract LocalDate getFechaNac(); 
}