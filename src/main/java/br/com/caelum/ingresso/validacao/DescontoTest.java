package br.com.caelum.ingresso.validacao;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipodeIngresso;
import br.com.caelum.ingresso.model.Ingresso.Ingresso;
import br.com.caelum.ingresso.model.descontos.DescontoBanco;
import br.com.caelum.ingresso.model.descontos.DescontoEstudante;
import br.com.caelum.ingresso.model.descontos.SemDesconto;

public class DescontoTest {
	
	@Test
	public void desconto30PorcentoParaClientesBanco(){
		Lugar lugar = new Lugar("A",1);
		Sala sala = new Sala ("IMAX", new BigDecimal("20.5"));
		Filme filme = new Filme ("Rogue one", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12.0"));
		Sessao sessao = new Sessao(LocalTime.now(), filme, sala);
		Ingresso ingresso = new Ingresso(sessao, TipodeIngresso.BANCO, lugar);
		
		BigDecimal precoEsperado = new BigDecimal("22.75");
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
	
	@Test
	public void desconto50PorcentoParaEstudantes(){
		Lugar lugar = new Lugar("A",1);
		Sala sala = new Sala ("IMAX", new BigDecimal("20.5"));
		Filme filme = new Filme ("Rogue one", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12.0"));
		Sessao sessao = new Sessao(LocalTime.now(), filme, sala);
		Ingresso ingresso = new Ingresso(sessao, TipodeIngresso.ESTUDANTE, lugar);
		
		BigDecimal precoEsperado = new BigDecimal("16.25");
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
	
	@Test
	public void semDesconto(){
		Lugar lugar = new Lugar("A",1);
		Sala sala = new Sala ("IMAX", new BigDecimal("20.5"));
		Filme filme = new Filme ("Rogue one", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12.0"));
		Sessao sessao = new Sessao(LocalTime.now(), filme, sala);
		Ingresso ingresso = new Ingresso(sessao, TipodeIngresso.INTEIRO, lugar);
		
		BigDecimal precoEsperado = new BigDecimal("32.5");
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}

}
