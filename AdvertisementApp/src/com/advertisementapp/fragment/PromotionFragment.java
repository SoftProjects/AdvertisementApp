package com.advertisementapp.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.advertisementapp.activity.PromotionDetailActivity;
import com.advertisementapp.adapter.PromotionListAdapter;
import com.advertisementapp.model.PromotionInfo;
import com.example.advertisementapp.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.os.AsyncTask;
import android.text.format.DateUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PromotionFragment {
	
	private PromotionListAdapter mAdapter;
	private List<PromotionInfo> mData;
	
	private View rootView;
	private Activity activity;
	private PullToRefreshListView mPullRefreshListView;
	
	public PromotionFragment(View rootView , final Activity activity){
	    this.rootView = rootView;
	    this.activity = activity;
	    initView();
	    loadData();
	}
	
	private void initView(){	
		mPullRefreshListView = (PullToRefreshListView)rootView.findViewById(R.id.pull_refresh_list);
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				 new GetDataTask().execute();
			}
		});
		
		
		mPullRefreshListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(activity,PromotionDetailActivity.class);
				activity.startActivity(intent);
				
			}
		});
	
	}
	
	private void loadData(){
		    mData = getTestData();
		    mAdapter = new PromotionListAdapter(activity.getApplicationContext(),mData); 
	        ListView actualListView = mPullRefreshListView.getRefreshableView();  
	        actualListView.setAdapter(mAdapter);  
	}
	
	 private class GetDataTask extends AsyncTask<Void, Void, String> {  
		  
	        //��̨������  
	        @Override  
	        protected String doInBackground(Void... params) {  
	            // Simulates a background job.  
	            try {  
	                Thread.sleep(1000);  
	            } catch (InterruptedException e) {  
	            }  
	            String str="Added after refresh...I add";  
	            return str;  
	        }  
	  
	        //�����Ƕ�ˢ�µ���Ӧ����������addFirst������addLast()�������¼ӵ����ݼӵ�LISTView��  
	        //����AsyncTask��ԭ��onPostExecute���result��ֵ����doInBackground()�ķ���ֵ  
	        @Override  
	        protected void onPostExecute(String result) {  
	            //��ͷ��������������  
	              
	            //֪ͨ�������ݼ��Ѿ��ı䣬�������֪ͨ����ô������ˢ��mListItems�ļ���  
	            mAdapter.notifyDataSetChanged();  
	            // Call onRefreshComplete when the list has been refreshed.  
	            mPullRefreshListView.onRefreshComplete();  
	  
	            super.onPostExecute(result);  
	        }  
	    }  	

	public List<PromotionInfo> getTestData(){
		
		List<PromotionInfo> data = new ArrayList<PromotionInfo>();
		
		PromotionInfo data_one = new PromotionInfo();
		data_one.setActivityTitle("�ϵ»��������");
		data_one.setNeededScore(3000);
		data_one.setRestNum(30);
		data_one.setRestDay(10);
		data_one.setLoaction("����");
		data.add(data_one);
		
		PromotionInfo data_two = new PromotionInfo();
		data_two.setActivityTitle("���Ͷ������");
		data_two.setNeededScore(1000);
		data_two.setRestNum(20);
		data_two.setRestDay(10);
		data_two.setLoaction("����");
		data.add(data_two);
		return data;
	}
}
