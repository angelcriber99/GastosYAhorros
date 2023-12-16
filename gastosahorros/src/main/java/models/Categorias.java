package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categorias {

	@Id
	@Column(name = "id_categoria")
	private int id_categoria;
	
	@Column(name = "nombre_categoria")
	private String nombre_categoria;
	
	@Column(name = "descripcion_categoria")
	private String descripcion_categoria;

	public Categorias() {
	}
	
    public Categorias(String nombre_categoria, String descripcion_categoria) {
        this.nombre_categoria = nombre_categoria;
        this.descripcion_categoria = descripcion_categoria;
    }

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getNombre_categoria() {
		return nombre_categoria;
	}

	public void setNombre_categoria(String nombre_categoria) {
		this.nombre_categoria = nombre_categoria;
	}

	public String getDescripcion_categoria() {
		return descripcion_categoria;
	}

	public void setDescripcion_categoria(String descripcion_categoria) {
		this.descripcion_categoria = descripcion_categoria;
	}
	

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Categoría ID: ").append(id_categoria).append("\n");
	    sb.append("Nombre de Categoría: ").append(nombre_categoria).append("\n");
	    sb.append("Descripción de Categoría: ").append(descripcion_categoria).append("\n");

	    return sb.toString();
	}

	
	
	
}
