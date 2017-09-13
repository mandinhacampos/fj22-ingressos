package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.caelum.ingresso.model.Ingresso.Ingresso;

@Component
@SessionScope
public class Carrinho {

	private List<Ingresso> ingressos = new ArrayList<>();

	public void add(Ingresso ingresso) {
		ingressos.add(ingresso);
	}

	public boolean isSelecionado(Lugar lugar) {
		return getIngressos().stream().map(Ingresso::getLugar).anyMatch(l -> l.equals(lugar));
	}

	public BigDecimal getTotal() {
		return getIngressos().stream().map(Ingresso::getPreco).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
	}

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	public Compra toCompra() {
		Compra compra = new Compra(ingressos);
		ingressos.clear();
		return compra;
	}
}
