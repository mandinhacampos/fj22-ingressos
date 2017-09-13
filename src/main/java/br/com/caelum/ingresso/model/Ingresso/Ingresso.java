package br.com.caelum.ingresso.model.Ingresso;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipodeIngresso;
import br.com.caelum.ingresso.model.descontos.Desconto;

@Entity
public class Ingresso {
	
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	private Sessao sessao;
	@ManyToOne
	private Lugar lugar;
	private BigDecimal preco;
	@Enumerated(EnumType.STRING)
	private TipodeIngresso tipoDeIngresso;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipodeIngresso getTipoDeIngresso() {
		return tipoDeIngresso;
	}

	public void setTipoDeIngresso(TipodeIngresso tipoDeIngresso) {
		this.tipoDeIngresso = tipoDeIngresso;
	}

	/**
	 * @deprecated hibernate only
	 */
	
	public Ingresso(){
	}

	public Ingresso(Sessao sessao, TipodeIngresso tipoDeIngresso, Lugar lugar){
		this.sessao=sessao;
		this.tipoDeIngresso = tipoDeIngresso;
		this.preco=this.tipoDeIngresso.aplicaDesconto(sessao.getPreco());
		this.lugar=lugar;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Sessao getSessao(){
		return sessao;
	}

	public BigDecimal getPreco(){
		return preco;
	}
}