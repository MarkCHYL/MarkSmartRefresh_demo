package demo.mark.com.smartrefresh_demo;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.umeng.analytics.MobclickAgent;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import demo.mark.com.smartrefresh_demo.utils.GlideImageLoader;

public class MainActivity extends AppCompatActivity {
    private List<String> images;
    private static Banner banner;

    private TextView textView;
    private ImageView iv_bg_header,iv_bg_floor;
    private ScrollView scrollView;

    private int mOffset = 0;
    private int mScrollY = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.translate));


   /*     Window window = this.getWindow();
        //设置透明状态栏,这样才能让 ContentView 向上
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));*/

//        或者
    /*    //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.translate), false);*/

        ViewGroup mContentView = (ViewGroup) this.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }
        RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        textView = (TextView) findViewById(R.id.tvtv);

        images = new ArrayList<>();
        images.add("http://img.zcool.cn/community/011f9e5543ec920000019ae99ff136.jpg");
        images.add("http://img.zcool.cn/community/015b3c5543ec920000019ae9fef2fa.jpg@2o.jpg");
        images.add("http://img.zcool.cn/community/01b6ff58213402a84a0e282bdb9627.jpg@900w_1l_2o_100sh.jpg");
        images.add("http://img.zcool.cn/community/015b3c5543ec920000019ae9fef2fa.jpg@2o.jpg");
        images.add("http://img.zcool.cn/community/011f9e5543ec920000019ae99ff136.jpg");
        images.add("http://img.zcool.cn/community/015b3c5543ec920000019ae9fef2fa.jpg@2o.jpg");
        images.add("http://img.zcool.cn/community/01b6ff58213402a84a0e282bdb9627.jpg@900w_1l_2o_100sh.jpg");
        banner = (Banner) findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();



        iv_bg_header = (ImageView) findViewById(R.id.iv_bg_header);
        iv_bg_floor = (ImageView) findViewById(R.id.iv_bg_floor);
        scrollView = (ScrollView) findViewById(R.id.scrollView_sv);

        //----实现上啦刷新上啦加载的背景图片的跟着拖动的效果----------------------------------------------
        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                mOffset = 3*offset / 4 ;
                iv_bg_header.setTranslationY(mOffset - mScrollY);

            }
            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                mOffset = offset /2;
                iv_bg_header.setTranslationY(mOffset - mScrollY);
            }

            @Override
            public void onFooterPulling(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {
                mOffset = 3*offset / 4 ;
                iv_bg_floor.setTranslationY(mOffset - mScrollY);
            }

            @Override
            public void onFooterReleasing(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {
                mOffset = offset /2;
                iv_bg_floor.setTranslationY(mOffset - mScrollY);
            }
        });

        //---------------------------------------------------------
        //设置 Header 为 Material风格
        refreshLayout.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));

        //设置 Footer 为 球脉冲
//        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this).setSpinnerStyle(SpinnerStyle.Translate));

        refreshLayout.autoRefresh();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                textView.setText("下拉刷新");
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
                textView.setText("上啦加载");
            }
        });
    }


    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //session的统计
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }
}
