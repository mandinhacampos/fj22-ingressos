package br.com.caelum.ingresso.validacao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipodeIngresso;
import br.com.caelum.ingresso.model.Ingresso.Ingresso;

public class SessaoTest {

	@Test
	public void oPrecoDaSessaoDeveSerASomaDoPrecoDaSalaEDoFilme() {
		Sala sala = new Sala("Eldorado", new BigDecimal("22.5"));
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12.0"));

		BigDecimal soma = sala.getPreco().add(filme.getPreco());
		Sessao sessao = new Sessao(LocalTime.now(), filme, sala);
		assertEquals(soma, sessao.getPreco());

	}

	@Test
	public void garanteQueOLugarEstaOcupadoNãoEstaraDisponivel() {
		Lugar a1 = new Lugar("A", 1);
		Lugar a2 = new Lugar("A", 2);
		Lugar a3 = new Lugar("A", 3);

		Sala sala = new Sala("Eldorado", new BigDecimal("22.5"));
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12.0"));
		Sessao sessao = new Sessao(LocalTime.now(), filme, sala);
		Ingresso ingresso = new Ingresso(sessao, TipodeIngresso.INTEIRO, a1);
		Set<Ingresso> ingressos = Stream.of(ingresso).collect(Collectors.toSet());

		sessao.setIngressos(ingressos);

		Assert.assertFalse(sessao.isDisponivel(a1));
		Assert.assertTrue(sessao.isDisponivel(a2));
		Assert.assertTrue(sessao.isDisponivel(a3));
	}

}
