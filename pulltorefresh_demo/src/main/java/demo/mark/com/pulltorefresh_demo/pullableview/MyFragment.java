package demo.mark.com.pulltorefresh_demo.pullableview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * 作者：mark on 2017/7/26 18:13
 * <p>
 * 邮箱：2285581945@qq.com
 */
public class MyFragment extends FrameLayout implements Pullable
{
    public MyFragment(Context context) {
        super(context);
    }

    public MyFragment(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFragment(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean canPullDown() {
        if (getScrollY() == 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean canPullUp() {

            return true;
    }
}

