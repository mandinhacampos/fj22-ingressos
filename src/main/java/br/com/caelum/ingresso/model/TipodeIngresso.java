package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.descontos.Desconto;
import br.com.caelum.ingresso.model.descontos.DescontoBanco;
import br.com.caelum.ingresso.model.descontos.DescontoEstudante;
import br.com.caelum.ingresso.model.descontos.SemDesconto;

public enum TipodeIngresso {
	
	INTEIRO(new SemDesconto()),
	ESTUDANTE(new DescontoEstudante()),
	BANCO(new DescontoBanco());
	
	private final Desconto desconto;
	
	TipodeIngresso(Desconto desconto){
		this.desconto=desconto;
	}
	
	public BigDecimal aplicaDesconto(BigDecimal valor){
		return desconto.aplicarDescontoSobre(valor);
	}
	
	public String getDescricao(){
		return desconto.getDescricao();
	}

	public Desconto getDesconto() {
		return desconto;
	}
}

