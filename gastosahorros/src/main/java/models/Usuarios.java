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

	@Column(name = "contrase�a")
	private String contrase�a;

	public Usuarios() {
	}

	public Usuarios(String nombre_usuario, String contrase�a) {
		this.nombre_usuario = nombre_usuario;
		this.contrase�a = contrase�a;
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

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	


	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Usuario ID: ").append(id_usuario).append("\n");
	    sb.append("Nombre de Usuario: ").append(nombre_usuario).append("\n");
	    sb.append("Contrase�a: ").append("*****").append("\n"); // Puedes ocultar la contrase�a por seguridad

	    return sb.toString();
	}



}
