package com.helder.cursoSpring.model;

import javax.persistence.Entity;

import com.helder.cursoSpring.model.enums.EstadoPagamento;

import lombok.Data;

@Entity
@Data
public class PagamentoComCartao extends Pagamento{
	
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	public PagamentoComCartao() {
		
	}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}	
}
