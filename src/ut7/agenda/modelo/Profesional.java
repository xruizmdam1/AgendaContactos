package ut7.agenda.modelo;

import java.util.Random;

public class Profesional extends Contacto {
	private String nombreGuardado ;
    
		public Profesional(String nombre, String apellidos, String telefono,
				String email) {
		
			 super(nombre, apellidos, telefono, email);
				
				char[] caracteres = nombre.toCharArray();
				caracteres[0] = Character.toUpperCase(caracteres[0]);
				 for (int i = 0; i < nombre.length()- 2; i++) 
					    if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',')
					      caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
				 nombreGuardado = new String(caracteres);
				
				
				 nombre = nombreGuardado;
		}
				public String getNombreGuardado() {
			return nombreGuardado;
		}
		public void setNombreGuardado(String nombreGuardado) {
			this.nombreGuardado = nombreGuardado;
		}
		@Override
		public String getFirmaEmail() {
		    int firma = new Random().nextInt(Firma.values().length);
		   String email = String.valueOf(firma);
		   return email;
		}
	
		@Override
		public String toString() {
			return "Profesional [nombreGuardado=" + nombreGuardado + ", getNombreGuardado()=" + getNombreGuardado()
					+ ", getNombre()=" + getNombre() + ", getApellidos()=" + getApellidos() + ", getTelefono()="
					+ getTelefono() + ", getEmail()=" + getEmail() + ", hashCode()=" + hashCode()
					+ ", getPrimeraLetra()=" + getPrimeraLetra() + ", toString()=" + super.toString() + ", getClass()="
					+ getClass() + "]";
		}

}
