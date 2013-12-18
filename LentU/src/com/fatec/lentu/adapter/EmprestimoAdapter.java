package com.fatec.lentu.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fatec.lentu.R;
import com.fatec.lentu.model.Emprestimo;

public class EmprestimoAdapter extends BaseAdapter{

	private List<Emprestimo> emprestimos;
	private LayoutInflater inflater;
		
	public EmprestimoAdapter(Context context, List<Emprestimo> lista) {
		this.emprestimos = lista;
		this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}
	
	@Override
	public int getCount() {
		return emprestimos.size();
	}

	@Override
	public Emprestimo getItem(int position) {
		return emprestimos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		Emprestimo emprestimo = emprestimos.get(position);
		View view = inflater.inflate(R.layout.component_emprestimo_list, null);
		TextView pertence = (TextView) view.findViewById(R.id.tbl_pertence);
		TextView amigo = (TextView) view.findViewById(R.id.tbl_amigo);
		TextView telefone = (TextView) view.findViewById(R.id.tbl_telefone);
		pertence.setText(emprestimo.getPertence().getNome());
		amigo.setText(emprestimo.getAmigo());
		telefone.setText(emprestimo.getTelefone());
		return view;
	}

	
	
}
