package com.SAlvesjr.cursomc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.SAlvesjr.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@Table(name = "tb_pagamento_cartao")
@JsonTypeName("pagamentoComCartao")
public class PagamentoCartao extends Pagamento {
	private static final long serialVersionUID = 1L;

	private Integer numerosParcelas;

	public PagamentoCartao() {

	}

	public PagamentoCartao(Long id, EstadoPagamento estado, Pedido pedido, Integer numerosParcelas) {
		super(id, estado, pedido);
		this.numerosParcelas = numerosParcelas;
	}

	public Integer getNumerosParcelas() {
		return numerosParcelas;
	}

	public void setNumerosParcelas(Integer numerosParcelas) {
		this.numerosParcelas = numerosParcelas;
	}

}
