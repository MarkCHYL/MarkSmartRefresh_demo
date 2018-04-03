package demo.mark.com.pulltorefresh_demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import demo.mark.com.pulltorefresh_demo.MyListener;
import demo.mark.com.pulltorefresh_demo.PullToRefreshLayout;
import demo.mark.com.pulltorefresh_demo.R;

public class PullableWebViewActivity extends Activity
{
	WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		((PullToRefreshLayout) findViewById(R.id.refresh_view))
				.setOnRefreshListener(new MyListener());
		webView = (WebView) findViewById(R.id.content_view);
		webView.loadUrl("http://blog.csdn.net/zhongkejingwang");
	}
}
