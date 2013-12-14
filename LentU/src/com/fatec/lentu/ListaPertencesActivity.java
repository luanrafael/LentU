package com.fatec.lentu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboListActivity;
import android.os.Bundle;

import com.fatec.lentu.adapter.PertenceAdapter;
import com.fatec.lentu.dao.PertenceDao;
import com.fatec.lentu.model.Pertence;

public class ListaPertencesActivity extends RoboListActivity{

	private PertenceDao pertenceDao;
	private List<Pertence> pertences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pertences = new ArrayList<Pertence>();
		registerForContextMenu(getListView());
		pertenceDao = new PertenceDao(this);
		updateList();
	}
	
	private void updateList() {
		try {
			pertences = pertenceDao.loadAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setListAdapter(new PertenceAdapter(this, pertences));
	}
	
}
