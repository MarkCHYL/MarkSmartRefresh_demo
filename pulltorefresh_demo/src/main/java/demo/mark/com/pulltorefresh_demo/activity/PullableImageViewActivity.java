package demo.mark.com.pulltorefresh_demo.activity;



import android.app.Activity;
import android.os.Bundle;

import demo.mark.com.pulltorefresh_demo.MyListener;
import demo.mark.com.pulltorefresh_demo.PullToRefreshLayout;
import demo.mark.com.pulltorefresh_demo.R;

public class PullableImageViewActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imageview);
		((PullToRefreshLayout) findViewById(R.id.refresh_view))
				.setOnRefreshListener(new MyListener());
	}
}
