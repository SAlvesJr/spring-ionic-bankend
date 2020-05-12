package com.SAlvesjr.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.SAlvesjr.cursomc.domain.enums.EstadoPagamento;

@Entity
@Table(name = "tb_pagamento_boleto")
public class PagamentoBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;

	private Date dataVencimento;
	private Date dataPagamento;

	public PagamentoBoleto() {

	}

	public PagamentoBoleto(Long id, EstadoPagamento estado, Pedido pedido, Date datevencimento, Date dataPagemnto) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagemnto;
		this.dataVencimento = datevencimento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
