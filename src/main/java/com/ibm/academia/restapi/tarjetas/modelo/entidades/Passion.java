package com.ibm.academia.restapi.tarjetas.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.ibm.academia.restapi.tarjetas.enumeradores.TipoPassion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "passions")
public class Passion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "No puede ser nulo")
	@Column(name = "tipo_passion")
	@Enumerated(EnumType.STRING)
	private TipoPassion tipoPassion;

	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede ser vacío")
	@Column(name = "usuario_creacion", nullable = false)
	private String usuarioCreacion;

	@Column(name = "fecha_creacion", nullable = false)
	private Date fechaCreacion;

	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "cliente_passion", joinColumns = @JoinColumn(name = "cliente_id"), inverseJoinColumns = @JoinColumn(name = "passion_id"))
	private Set<Cliente> clientes;

	private static final long serialVersionUID = 5707410870482267720L;

	public Passion(Long id, TipoPassion tipoPassion, String usuarioCreacion) {
		this.id = id;
		this.tipoPassion = tipoPassion;
		this.usuarioCreacion = usuarioCreacion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Passion [id=");
		builder.append(id);
		builder.append(", tipoPassion=");
		builder.append(tipoPassion);
		builder.append(", usuarioCreacion=");
		builder.append(usuarioCreacion);
		builder.append(", fechaCreacion=");
		builder.append(fechaCreacion);
		builder.append(", fechaModificacion=");
		builder.append(fechaModificacion);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tipoPassion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passion other = (Passion) obj;
		return Objects.equals(id, other.id) && tipoPassion == other.tipoPassion;
	}

	@PrePersist
	private void antesPersistir() {
		this.fechaCreacion = new Date();
	}

	@PreUpdate
	private void antesActualizar() {
		this.fechaModificacion = new Date();
	}

}
