package ut7.agenda.modelo;

import java.util.Random;

public class Profesional extends Contacto {
	private String nombreGuardado ;
    public enum firma {
        Atentamente, Saludos, Saludos_Cordiales, Mis_Mejores_Deseos
    };
		public Profesional(String nombre, String apellidos, String telefono,
				String email) {
		
			 super(nombre, apellidos, telefono, email);
				
				char[] caracteres = nombre.toCharArray();
				caracteres[0] = Character.toUpperCase(caracteres[0]);
				 for (int i = 0; i < nombre.length()- 2; i++) 
					    if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',')
					      caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
				 nombreGuardado = new String(caracteres);
				int emails = new Random().nextInt(firma.values().length);
				email = String.valueOf(emails);

				
				 nombre = nombreGuardado;
		}
		@Override
		public String toString() {
			return "Profesional [nombreGuardado=" + nombreGuardado + ", getNombreGuardado()=" + getNombreGuardado()
					+ ", getNombre()=" + getNombre() + ", getApellidos()=" + getApellidos() + ", getTelefono()="
					+ getTelefono() + ", getEmail()=" + getEmail() + ", hashCode()=" + hashCode()
					+ ", getPrimeraLetra()=" + getPrimeraLetra() + ", toString()=" + super.toString() + ", getClass()="
					+ getClass() + "]";
		}
		public String getNombreGuardado() {
			return nombreGuardado;
		}
		public void setNombreGuardado(String nombreGuardado) {
			this.nombreGuardado = nombreGuardado;
		}
	
		
}
