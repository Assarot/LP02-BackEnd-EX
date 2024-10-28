package pe.edu.upeu.LP2_clase01.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="libro")
public class Libro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="titulo")
	private String titulo;
	@Column(name="paginas")
	private int paginas;
	@Column(name="edicion")
	private String edicion;
	@Column(name="estado")
	private char estado;
	
	@ManyToOne
	@JoinColumn(name="seccion_id", nullable = false)
	private Seccion seccion;
	
	@ManyToOne
	@JoinColumn(name="editorial_id", nullable = false)
	private Editorial editorial;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JsonIgnore
	@JoinTable(
			name="libro_autor",
			joinColumns = @JoinColumn(name="libro_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="autor_id", referencedColumnName = "id")
	)
	private Set<Autor> autores;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "libro")
	@JsonIgnore
	private Set<DetallePrestamo> detalles;
}
