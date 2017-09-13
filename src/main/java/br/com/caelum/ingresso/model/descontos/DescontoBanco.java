package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class DescontoBanco implements Desconto{
	private BigDecimal percentual= new BigDecimal("0.3");
	
	@Override
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal){
		return precoOriginal.subtract(trintaSobre(precoOriginal));
	}
	
	private BigDecimal trintaSobre(BigDecimal precoOriginal){
		return precoOriginal.multiply(percentual);
	}
	
	@Override
	public String getDescricao(){
		return "Desconto Banco";
	}

}
