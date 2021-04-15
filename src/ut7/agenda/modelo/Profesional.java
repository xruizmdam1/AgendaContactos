package ut7.agenda.modelo;

/* 
 * @author ANTHONNY
 */

public class Profesional extends Contacto {
	private String nombreEmpresa;

	public Profesional(String nombre, String apellidos, String telefono, String email, String nombreEmpresa) {
		super(nombre, apellidos, telefono, email);
		this.setNombreEmpresa(letraMayuscula(nombreEmpresa));

	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	@Override
	public String getFirmaEmail() {
		String[] firma = { "Atentamente", "Saludos", "Saludos cordiales", "Mis mejores deseos" }; // se crea un array
																									// para la firma
		return firma[(int) (Math.random() * 3)]; // se realiza un random
	}

	private String letraMayuscula(String nombreEmpresa) {

		if (nombreEmpresa.length() == 0) {
			return nombreEmpresa;
		}
		return nombreEmpresa.substring(0, 1).toUpperCase() + nombreEmpresa.substring(1).toLowerCase();

	}

	@Override
	public String toString() {
		return "Profesional [nombreEmpresa=" + nombreEmpresa + ", getNombreEmpresa()=" + getNombreEmpresa()
				+ ", getFirmaEmail()=" + getFirmaEmail() + "]";
	}

}
