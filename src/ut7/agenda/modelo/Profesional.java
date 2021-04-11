package ut7.agenda.modelo;

import java.util.Random;

public class Profesional extends Contacto {
	private String nombreEmpresa ;
	private enum firma {Atentamente, Saludos, Saludos_cordiales, Mis_mejores_deseos};
	
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
			int aux = new Random().nextInt(firma.values().length);
			String email = String.valueOf(aux);
			return email;
		}
		
			private String letraMayuscula (String nombreEmpresa){
				 
				if (nombreEmpresa.length() == 0 ) {
					return nombreEmpresa;
				}
				return nombreEmpresa.substring(0, 1).toUpperCase() +
						nombreEmpresa.substring(1).toLowerCase();
			 
			}

			@Override
			public String toString() {
				return "Profesional [nombreEmpresa=" + nombreEmpresa + ", getNombreEmpresa()=" + getNombreEmpresa()
						+ ", getFirmaEmail()=" + getFirmaEmail() + "]";
			}

}
