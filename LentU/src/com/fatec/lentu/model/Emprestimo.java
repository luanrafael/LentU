package com.fatec.lentu.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Emprestimo {
	
	/**
	 * Id.
	 * */
	@DatabaseField(generatedId=true, allowGeneratedIdInsert = true)
	private Long id;
	
	/**
	 * O objeto que estaá sendo emprestado.
	 * */
	@DatabaseField
	private Pertence pertence;
	
	/**
	 * Para quem está emprestando.
	 * */
	@DatabaseField
	private String amigo;
	
	/**
	 * Telefone da pessoa que foi emprestado para envio SMS.
	 * */
	@DatabaseField
	private String telefone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pertence getPertence() {
		return pertence;
	}

	public void setPertence(Pertence pertence) {
		this.pertence = pertence;
	}

	public String getAmigo() {
		return amigo;
	}

	public void setAmigo(String amigo) {
		this.amigo = amigo;
	}
}
