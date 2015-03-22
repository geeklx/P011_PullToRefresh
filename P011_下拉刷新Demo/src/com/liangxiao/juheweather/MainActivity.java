package com.liangxiao.juheweather;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.liangxiao.juheweather.swiperefresh.PullToRefreshBase;
import com.liangxiao.juheweather.swiperefresh.PullToRefreshBase.OnRefreshListener;
import com.liangxiao.juheweather.swiperefresh.PullToRefreshScrollView;
import com.liangxiao.juheweather1.R;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class MainActivity extends Activity {
	PullToRefreshScrollView mPullPullToRefreshScrollView;
	ScrollView mScrollView;

	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.activity_main);
		// tv = (TextView) findViewById(R.id.tv);
		init();
		// api
		// init_api();
	}

	private void init() {
		mPullPullToRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.pull_refresh_scrollview);
		mPullPullToRefreshScrollView
				.setOnRefreshListener(new OnRefreshListener<ScrollView>() {

					@Override
					public void onRefresh(
							PullToRefreshBase<ScrollView> refreshView) {
						new Thread(new Runnable() {

							@Override
							public void run() {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									e.printStackTrace();
								}
								CloseScrollView();
							}
						}).start();
					}

				});

		mScrollView = mPullPullToRefreshScrollView.getRefreshableView();

	}

	private void CloseScrollView() {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				mPullPullToRefreshScrollView.onRefreshComplete();
			}
		});
	}

	private void init_api() {
		Parameters params = new Parameters();
		params.add("ip", "www.juhe.cn");
		params.add("dtype", "xml");
		/**
		 * 请求的方法 参数: 第一个参数 接口id 第二个参数 接口请求的url 第三个参数 接口请求的方式 第四个参数
		 * 接口请求的参数,键值对com.thinkland.sdk.android.Parameters类型; 第五个参数
		 * 请求的回调方法,com.thinkland.sdk.android.DataCallBack;
		 * 
		 */
		JuheData.executeWithAPI(1, "http://apis.juhe.cn/ip/ip2addr",
				JuheData.GET, params, new DataCallBack() {

					/**
					 * @param err
					 *            错误码,0为成功
					 * @param reason
					 *            原因
					 * @param result
					 *            数据
					 */
					@Override
					public void resultLoaded(int err, String reason,
							String result) {
						// TODO Auto-generated method stub
						if (err == 0) {
							tv.setText(result);
						} else {
							Toast.makeText(getApplicationContext(), reason,
									Toast.LENGTH_SHORT).show();
						}
					}
				});

	}

}
