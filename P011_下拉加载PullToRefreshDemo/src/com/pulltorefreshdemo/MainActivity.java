package com.pulltorefreshdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class MainActivity extends Activity {
	private PullToRefreshListView lv;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init1();
		init2();
	}

	private void init1() {
		lv = (PullToRefreshListView) findViewById(R.id.mylistview);
		List<String> arr = new ArrayList<String>();
		arr.add("极客学院");
		arr.add("eoe");
		// adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, new String[] { "你大爷1",
		// "你大爷2" });
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, arr);
		lv.setAdapter(adapter);
		lv.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... arg0) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						adapter.addAll("hello", "刘金兰大坏蛋");
						lv.onRefreshComplete();
					}

					@Override
					protected void onPreExecute() {
						// TODO Auto-generated method stub
						// super.onPreExecute();
					}
				}.execute();
			}

		});
	}

	private void init2() {

	}
}
