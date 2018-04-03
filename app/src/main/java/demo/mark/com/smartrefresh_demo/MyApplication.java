package demo.mark.com.smartrefresh_demo;

import android.app.Application;

import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;

/**
 * 作者：mark on 2017/8/12 10:05
 * <p>
 * 邮箱：2285581945@qq.com
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        /*友盟统计*/
        MobclickAgent.setScenarioType(getApplicationContext(), MobclickAgent.EScenarioType. E_UM_NORMAL);
        /** 设置是否对日志信息进行加密, 默认false(不加密). */
        MobclickAgent.enableEncrypt(true);//6.0.0版本及以后
        MobclickAgent.setDebugMode( true );
    }
}
