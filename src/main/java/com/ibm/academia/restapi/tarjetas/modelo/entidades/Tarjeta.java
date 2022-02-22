package com.ibm.academia.restapi.tarjetas.modelo.entidades;

import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tarjetas")
public class Tarjeta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede ser vacío")
	@Column(name = "tipo_tarjeta", nullable = false)
	private String tipoTarjeta;

	@ManyToOne(optional = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "FK_CLIENTE_ID"))
	@JsonIgnoreProperties({"tarjetas"})
	private Cliente cliente;

	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede ser vacío")
	@Column(name = "usuario_creacion", nullable = false)
	private String usuarioCreacion;

	@Column(name = "fecha_creacion", nullable = false)
	private Date fechaCreacion;

	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;

	public Tarjeta(Long id, String tipoTarjeta, String usuarioCreacion) {
		this.id = id;
		this.tipoTarjeta = tipoTarjeta;
		this.usuarioCreacion = usuarioCreacion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tarjeta [id=");
		builder.append(id);
		builder.append(", tipoTarjeta=");
		builder.append(tipoTarjeta);
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
		return Objects.hash(id, tipoTarjeta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarjeta other = (Tarjeta) obj;
		return Objects.equals(id, other.id) && Objects.equals(tipoTarjeta, other.tipoTarjeta);
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
