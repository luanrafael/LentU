package com.fatec.lentu.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fatec.lentu.R;
import com.fatec.lentu.model.Emprestimo;

public class EmprestimoAdapter extends BaseAdapter {
	
	private List<Emprestimo> emprestimos;
	private LayoutInflater inflater;

	@Override
	public int getCount() {
		return emprestimos.size();
	}

	@Override
	public Emprestimo getItem(int item) {
		return emprestimos.get(item);
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
		pertence.setText(emprestimo.getPertence().getNome());
		amigo.setText(emprestimo.getAmigo());
		return view;
	}

}
