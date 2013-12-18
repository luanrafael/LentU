package com.fatec.lentu.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Emprestimo {
	
	/**
	 * Id.
	 * */
	@DatabaseField(generatedId=true)
	private Long id;
	
	/**
	 * O objeto que esta sendo emprestado.
	 * */
//	@DatabaseField(foreign = true,foreignAutoCreate=true,columnDefinition = "integer references pertence(id) on delete RESTRICT")
	@DatabaseField(foreign = true, foreignAutoRefresh=true)
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}