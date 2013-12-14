package com.fatec.lentu.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Emprestimo {
	
	/**
	 * 
	 * */
	@DatabaseField(generatedId=true)
	private Long id;
	
	/**
	 * O objeto que esta° sendo emprestado.
	 * */
	@DatabaseField
	private Pertence pertence;
	
	/**
	 * Para quem est√° emprestando.
	 * */
	@DatabaseField
	private String amigo;

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
