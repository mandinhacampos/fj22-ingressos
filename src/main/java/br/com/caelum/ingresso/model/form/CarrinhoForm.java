package br.com.caelum.ingresso.model.form;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipodeIngresso;
import br.com.caelum.ingresso.model.Ingresso.Ingresso;

public class CarrinhoForm {

	private List<Ingresso> ingressos = new ArrayList<>();

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	public List<Ingresso> toIngressos(SessaoDao sessaoDao, LugarDao lugarDao) {
		System.out.println(ingressos);
		return this.ingressos.stream().map(ingresso -> {
			System.out.println(ingresso);
			Sessao sessao = sessaoDao.findOne(ingresso.getSessao().getId());
			Lugar lugar = lugarDao.findOne(ingresso.getLugar().getId());
			TipodeIngresso tipoDeIngresso = ingresso.getTipoDeIngresso();
			return new Ingresso(sessao, tipoDeIngresso, lugar);
		}).collect(Collectors.toList());

	}

}
