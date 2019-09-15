package br.barretuino.modelagem;
/**
 * Projeto de Gerência
 * Modelagem Conceitual de Medida
 * @autor Prof. Paulo Barreto
 * @date 21/09/2019
 */

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


public class Medida {
	private float luminosidade;
	private GregorianCalendar data;
	
	public Medida(){
		
	}

	public float getLuminosidade() {
		return luminosidade;
	}

	public void setLuminosidade(float luminosidade) {
		this.luminosidade = luminosidade;
	}

	public GregorianCalendar getData() {
		return data;
	}

	public void setData(GregorianCalendar data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "Medida: " + getLuminosidade() 
		+ " - " + new SimpleDateFormat("dd/mm/yyyy - hh:mm:ss:ms").format(getData());
	}
}