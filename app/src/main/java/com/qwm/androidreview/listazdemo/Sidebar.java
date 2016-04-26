/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qwm.androidreview.listazdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.qwm.androidreview.R;

/**
 * @author qiwenming
 * @date 2016/4/21 15:03
 * @ClassName: Sidebar
 * @Description:  A-Z搜索的搜索条
 */
public class Sidebar extends View{
	private Paint paint;
	private TextView header;
	private float height;
	private ListView mListView;
	private Context context;
	
	public void setListView(ListView listView){
		mListView = listView;
	}
	

	public Sidebar(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	private String[] sections; 

	private void init(){
	  //  String st = context.getString(R.string.search_new);
        sections= new String[]{"A","B","C","D","E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.DKGRAY);
		paint.setTextAlign(Align.CENTER);
		paint.setTextSize(DensityUtil.sp2px(context, 16));
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		float center = getWidth() / 2;
		height = getHeight() / sections.length;
		for (int i = sections.length - 1; i > -1; i--) {
			canvas.drawText(sections[i], center, height * (i+1), paint);
		}
	}
	
	private int sectionForPoint(float y) {
		int index = (int) (y / height);
		if(index < 0) {
			index = 0;
		}
		if(index > sections.length - 1){
			index = sections.length - 1;
		}
		return index;
	}
	
	private void setHeaderTextAndscroll(MotionEvent event){
		 if (mListView == null) {
		        //check the mListView to avoid NPE. but the mListView shouldn't be null
		        //need to check the call stack later
		        return;
		    }
		String headerString = sections[sectionForPoint(event.getY())];
		header.setText(headerString);
		AZAdapter adapter = (AZAdapter)mListView.getAdapter();
		int postion = adapter.getLocation(headerString);
		if(postion>=0){
			mListView.setSelection(postion);
		}

////		CarBrankListAdapter adapter = (CarBrankListAdapter) mListView.getAdapter();
////		String[] adapterSections = (String[]) adapter.getSections();
//		try {
////			for (int i = adapterSections.length - 1; i > -1; i--) {
////				if(adapterSections[i].equals(headerString)){
////					mListView.setSelection(adapter.getPositionForSection(i));
////					break;
////				}
////			}
//		} catch (Exception e) {
//			Log.e("setHeaderTextAndscroll", e.getMessage());
//		}
		
	}

	/**
	 * 解释：为什么都要返回true,
	 * 事件分发中，只有上一个动作为ture,才会往下分发，如果为false,就不会再外下了，也就是说，事件分发是
	 * 我们都知道如果给一个控件注册了touch事件，每次点击它的时候都会触发一系列的ACTION_DOWN，ACTION_MOVE，ACTION_UP等事件。
	 * 这里需要注意，如果你在执行ACTION_DOWN的时候返回了false，后面一系列其它的action就不会再得到执行了。简单的说，
	 * 就是当dispatchTouchEvent在进行事件分发的时候，只有前一个action返回true，才会触发后一个action。
	 *
	 * @param event
	 * @return
     */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:{
			if(header == null){
				header = (TextView) ((View)getParent()).findViewById(R.id.floating_header);
			}
			setHeaderTextAndscroll(event);
			header.setVisibility(View.VISIBLE);
			setBackgroundResource(R.drawable.sidebar_background_pressed);
			return true;
		}
		case MotionEvent.ACTION_MOVE:{
			setHeaderTextAndscroll(event);
			return true;
		}
		case MotionEvent.ACTION_UP:
			header.setVisibility(View.INVISIBLE);
			setBackgroundColor(Color.TRANSPARENT);
			return true;
		case MotionEvent.ACTION_CANCEL:
			header.setVisibility(View.INVISIBLE);
			setBackgroundColor(Color.TRANSPARENT);
			return true;
		}
		return super.onTouchEvent(event);
	}

}
