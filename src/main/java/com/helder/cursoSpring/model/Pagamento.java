package com.helder.cursoSpring.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.helder.cursoSpring.model.enums.EstadoPagamento;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Mapeamento de herança na super classe, nas sub classes basta colocar
												// o @Entity

@Data
@EqualsAndHashCode(of = { "id" })
public abstract class Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id // Esse ID não pode ser alto increment pq o ID dessa classe deve ser o msm id
	private Integer id; // do pedido correspondente a esse pagamento
	private Integer estado;

	@JsonIgnore
	@OneToOne // representa o msm id
	@JoinColumn(name = "pedido_id") // cria coluna com esse nome
	@MapsId // para garanti que vai ser o mesmo ID do pedido
	private Pedido pedido;

	public Pagamento() {

	}

	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = estado.getCod();
		this.pedido = pedido;
	}
}
