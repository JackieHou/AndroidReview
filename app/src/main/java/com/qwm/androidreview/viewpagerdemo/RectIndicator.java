package com.qwm.androidreview.viewpagerdemo;

import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qwm.androidreview.R;

/**
 * @author qiwenming
 * 矩形指示器
 */
public class RectIndicator extends LinearLayout {

	private Paint mPaint; // 画指示符的paint

	private int mTop; // 指示符的top
	private int mLeft; // 指示符的left
	private int mWidth; // 指示符的width
	private int mItemWidth; // 指示符的width
	private int mHeight; // 指示符的高度
	private int mColor; // 指示符的颜色
	private int mTabVisibleCount; // 子item的个数，用于计算指示符的宽度
	private int titlesCount = 0; // 传递过来的标题的个数  主要跟用于计算指针的 位置（小于mTabVisibleCount时）
	private int COUNT_DEFAULT_TAB = 5; // 子item的个数，用于计算指示符的宽度
	private int indicator = 0;
	
	
	//与之绑定的ViewPager
	public ViewPager mViewPager;

	private List<String> mTabTitles;

	private int mTitleColor;

	private int mTitleSize;
	private int initpos = 0;
	private int initCount = 0;

	public RectIndicator(Context context) {
		super(context);
	}

	public RectIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);

		// 获得自定义属性，tab的数量
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RectIndicator);
		mTabVisibleCount = a.getInt(R.styleable.RectIndicator_item_count1,COUNT_DEFAULT_TAB);
		if (mTabVisibleCount < 0)// item个数
			mTabVisibleCount = COUNT_DEFAULT_TAB;
		// 获取自定义属性 指示符的颜色
		mColor = a.getColor(R.styleable.RectIndicator_indicator_color, 0X0000FF);
		//标题文字颜色
		mTitleColor = a.getColor(R.styleable.RectIndicator_indicator_title_color, 0X0000FF);
		int bgColor = a.getColor(R.styleable.RectIndicator_indicator_bgcolor, 0Xff00FF);
		mWidth = a.getDimensionPixelSize(R.styleable.RectIndicator_indicator_width, 5);
		mTitleSize = a.getDimensionPixelSize(R.styleable.RectIndicator_title_size, 16);
		mHeight = a.getDimensionPixelSize(R.styleable.RectIndicator_indicator_height, 5);
		a.recycle();

		 setBackgroundColor(bgColor);  // 必须设置背景，否则onDraw不执行  
		// 初始化paint
		mPaint = new Paint();
		mPaint.setColor(mColor);
		mPaint.setAntiAlias(true);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int height = getMeasuredHeight(); // 测量的高度即指示符的顶部位置
		int width = getMeasuredWidth(); // 获取测量的总宽度
	    mTop = height - mHeight; // 重新定义一下测量的高度
	    //如果标题的个数大于0 并且小于我们设置的标题的个数，那么我们的指针的宽度 按标题的个数计算
	    mItemWidth = width / titlesCount; // 指示符的宽度为总宽度/item的个数
	    if(initpos>0){
	    	mLeft = mItemWidth*initpos+(mItemWidth-mWidth)/2;
	    	if(initCount>0)
	    	    initpos = -1;
	    	initCount++;
	    }else{
	    	mLeft = (mItemWidth-mWidth)/2;
	    }
		setMeasuredDimension(width, height);
	}

	
	
	@Override  
	protected void onDraw(Canvas canvas) {  
	    // 圈出一个矩形  
	    Rect rect = new Rect(mLeft, mTop, mLeft + mWidth, mTop + mHeight);
		//  canvas.drawRect(rect, mPaint); // 绘制该矩形

		//  Rect rect = new Rect(0, 0,300, 3000);
		//  mPaint.setColor(Color.RED);
		canvas.drawRect(rect, mPaint); // 绘制该矩形
	    super.onDraw(canvas);  
	}  
	
	
	/**
	 * 设置布局中view的一些必要属性；如果设置了setTabTitles，布局中view则无效
	 */
	@Override
	protected void onFinishInflate() {
		Log.e("TAG", "onFinishInflate");
		super.onFinishInflate();

		int cCount = getChildCount();

		if (cCount == 0)
			return;

		for (int i = 0; i < cCount; i++) {
			View view = getChildAt(i);
			LayoutParams lp = (LayoutParams) view
					.getLayoutParams();
			lp.weight = 0;
			lp.width = getScreenWidth() / mTabVisibleCount;
			view.setLayoutParams(lp);
		}
		// 设置点击事件
		setItemClickEvent();

	}

	
	
	/**
	 * 设置tab的标题内容 可选，可以自己在布局文件中写死
	 * 
	 * @param datas
	 */
	public void setTabItemTitles(List<String> datas)
	{
		// 如果传入的list有值，则移除布局文件中设置的view
		if (datas != null && datas.size() > 0)
		{
			this.titlesCount = datas.size() ;
			titlesCount = titlesCount>0 && titlesCount<mTabVisibleCount ? titlesCount : mTabVisibleCount;
			this.removeAllViews();
			this.mTabTitles = datas;

			for (String title : mTabTitles)
			{
				// 添加view
				addView(generateTextView(title));
			}
			// 设置item的click事件
			setItemClickEvent();
		}

	}
	
	
	/**
	 * 根据标题生成我们的TextView
	 * 
	 * @param text
	 * @return
	 */
	private TextView generateTextView(String text)
	{
		TextView tv = new TextView(getContext());
		LayoutParams lp = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		lp.width = getScreenWidth() / titlesCount;
		tv.setGravity(Gravity.CENTER);
		tv.setTextColor(mTitleColor);
		tv.setText(text);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTitleSize);
	//	tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		tv.setLayoutParams(lp);
		return tv;
	}
	
	/**
	 * 对外的ViewPager的回调接口
	 * 
	 * @author zhy
	 * 
	 */
	public interface PageChangeListener
	{
		public void onPageScrolled(int position, float positionOffset,
								   int positionOffsetPixels);

		public void onPageSelected(int position);

		public void onPageScrollStateChanged(int state);
	}

	
	// 对外的ViewPager的回调接口
	private PageChangeListener onPageChangeListener;


	// 对外的ViewPager的回调接口的设置
	public void setOnPageChangeListener(PageChangeListener pageChangeListener)
	{
		this.onPageChangeListener = pageChangeListener;
	}
	

	// 设置关联的ViewPager
	public void setViewPager(ViewPager mViewPager, int pos)
	{
		this.mViewPager = mViewPager;

		mViewPager.setOnPageChangeListener(new OnPageChangeListener()
		{
			@Override
			public void onPageSelected(int position)
			{

				// 回调
				if (onPageChangeListener != null)
				{
					onPageChangeListener.onPageSelected(position);
				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,int positionOffsetPixels)
			{
				Log.i("onPageScrolled", "position:"+position+"\t  positionOffset:"+positionOffset+"\t positionOffsetPixels:"+positionOffsetPixels);
				// 滚动
				scroll(position, positionOffset);

				// 回调
				if (onPageChangeListener != null)
				{
					onPageChangeListener.onPageScrolled(position,
							positionOffset, positionOffsetPixels);
				}

			}

			@Override
			public void onPageScrollStateChanged(int state)
			{
				// 回调
				if (onPageChangeListener != null)
				{
					onPageChangeListener.onPageScrollStateChanged(state);
				}

			}
		});
		// 设置当前页
		mViewPager.setCurrentItem(pos);
		initpos = pos;
		invalidate();
	}
	
	
	
	/**
	 * 指示器跟随手指滚动，以及容器滚动
	 * 
	 * @param position
	 * @param offset
	 */
	public void scroll(int position, float offset)
	{
		/**
		 *  0 - 1:position=0 ;1 - 0:postion=0;
		 *  代表前一页 ： 向左滑 前面一页  ，向右滑动 我们点击下去的那一页   ，直到状态改变 才会改变为当前页
		 *
		 *  如果是 0--->1 那么postion 代表的是前面一个 就是 0    offset从 0.0----1.0变化  0.01111  0.55555,0.99999
		 *
		 *  如果是 1--->0 postion代表的也是 0         offset从 1.0----0.0变化   0.99999，0.55555，0.01111
		 *
		 */
		// 不断改变偏移量，invalidate
		mLeft = (mItemWidth-mWidth)/2;//在内部距离左边的距离
		mLeft = (int) ((position + offset) * mItemWidth+mLeft); //总的距离左边的距离

		int tabWidth = getScreenWidth() / mTabVisibleCount;
		int moreIndex = 1;//这一个用于判断和设置，当条目超过的时候，我们的 指针应该指示在哪个位置，1 代表倒数第一个，2代表倒数第二个


		// 容器滚动，当移动到倒数最后一个的时候，开始滚动
		if (offset > 0 && position >= (mTabVisibleCount-moreIndex)&& getChildCount() > mTabVisibleCount) {
			Log.i("scroll", "scroll: ");
			if (mTabVisibleCount != 1) {
				this.scrollTo((position - (mTabVisibleCount-moreIndex)) * tabWidth+ (int) (tabWidth * offset), 0);
				Log.i("scroll", "scroll x: "+( (position - (mTabVisibleCount-moreIndex)) * tabWidth+ (int) (tabWidth * offset)) );
			} else {// 可以看机的个数为count为1时 的特殊处理
				this.scrollTo(position * tabWidth + (int) (tabWidth * offset), 0);
				Log.i("scroll", "scroll x: "+( position * tabWidth + (int) (tabWidth * offset)) );
			}
		}
		invalidate();
	}
//	
//	/**
//	 * 指示符滚动
//	 * 
//	 * @param position
//	 *            现在的位置
//	 * @param offset
//	 *            偏移量 0 ~ 1
//	 */
//	public void scroll(int position, float offset) {
//		mLeft = (mItemWidth-mWidth)/2;
//		mLeft = (int) ((position + offset) * mItemWidth+mLeft);
//		invalidate();
//	}
	
	
	/**
	 * 设置点击事件
	 */
	public void setItemClickEvent()
	{
		int cCount = getChildCount();
		for (int i = 0; i < cCount; i++)
		{
			final int j = i;
			View view = getChildAt(i);
			view.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					mViewPager.setCurrentItem(j);
				}
			});
		}
	}
	
	/**
	 * 设置指示器的宽度
	 */
	public void setIndiWidth(int width){
		mWidth = width;
	}
	
	/**
	 * 设置指示器的宽度
	 */
	public void setIndiHeight(int height){
		mHeight = height;
	}
	
	/**
	 * 获得屏幕的宽度
	 * 
	 * @return
	 */
	public int getScreenWidth()
	{
		WindowManager wm = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}
}
