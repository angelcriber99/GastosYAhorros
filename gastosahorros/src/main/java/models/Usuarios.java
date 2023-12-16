package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuarios {

	@Id
	@Column(name = "id_usuario")
	private int id_usuario;

	@Column(name = "nombre_usuario")
	private String nombre_usuario;

	@Column(name = "contraseña")
	private String contraseña;

	public Usuarios() {
	}

	public Usuarios(String nombre_usuario, String contraseña) {
		this.nombre_usuario = nombre_usuario;
		this.contraseña = contraseña;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre() {
		return nombre_usuario;
	}

	public void setNombre(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	


	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Usuario ID: ").append(id_usuario).append("\n");
	    sb.append("Nombre de Usuario: ").append(nombre_usuario).append("\n");
	    sb.append("Contraseña: ").append("*****").append("\n"); // Puedes ocultar la contraseña por seguridad

	    return sb.toString();
	}



}
