package com.fatec.lentu.model;

import java.util.ArrayList;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Emprestimo {
	
	/**
	 * 
	 * */
	@DatabaseField(allowGeneratedIdInsert = true, id = true, canBeNull=false)
	private Long id;
	
	/**
	 * O objeto que está sendo emprestado.
	 * */
	@DatabaseField
	private Pertence pertence;
	
	/**
	 * Para quem está emprestando.
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
	
	ArrayList<Pertence> pertences = new ArrayList<Pertence>();
	public void t() {
		pertences.indexOf(object);
	}
	
	

}
