package com.fatec.lentu.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fatec.lentu.R;
import com.fatec.lentu.model.Pertence;

public class PertenceAdapter extends BaseAdapter{

	private List<Pertence> pertences;
	private LayoutInflater inflater;
		
	public PertenceAdapter(Context context, List<Pertence> lista) {
		this.pertences = lista;
		this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}
	
	@Override
	public int getCount() {
		return pertences.size();
	}

	@Override
	public Pertence getItem(int position) {
		return pertences.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		Pertence pertence = pertences.get(position);
		View view = inflater.inflate(R.layout.component_pertence_list, null);
		TextView nome = (TextView) view.findViewById(R.id.tbl_nome);
		TextView categoria = (TextView) view.findViewById(R.id.tbl_categoria);
		nome.setText(pertence.getNome());
		categoria.setText(pertence.getCategoria());
		return view;
	}

	
	
}
