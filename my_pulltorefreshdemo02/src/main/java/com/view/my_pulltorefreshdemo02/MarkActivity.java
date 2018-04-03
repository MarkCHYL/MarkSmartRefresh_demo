package com.view.my_pulltorefreshdemo02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

public class MarkActivity extends AppCompatActivity implements PulltorefreshView.RefreshCallBack {

    private RecyclerView mRecyHome = null;
    private PulltorefreshView reFreshView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);

        initView();
    }

    private void initView() {
        mRecyHome = (RecyclerView) findViewById(R.id.recy_home);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyHome.setLayoutManager(layoutManager);
        mRecyHome.setAdapter(new HomeAdapter());
        reFreshView = (PulltorefreshView) findViewById(R.id.refreshView);
        reFreshView.setRefreshCallBack(this);
    }

    @Override
    public void upRefresh(Scroller scroller, int y) {
        scroller.startScroll(0, y, 0, -100);
        reFreshView.invalidate();
    }

    @Override
    public void downLoad(Scroller scroller,int y) {
        scroller.startScroll(0, y, 0, 100);
        reFreshView.invalidate();
    }

    class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MarkActivity.this).inflate(R.layout.item_bear,parent,false);
            return new HomeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((HomeViewHolder) holder).tv_name.setText("美女"+position);
            ((HomeViewHolder) holder).iv_picture.setBackgroundResource(R.mipmap.ic_launcher);
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        class HomeViewHolder extends RecyclerView.ViewHolder {

            private TextView tv_name = null;
            private ImageView iv_picture = null;
            private HomeViewHolder(View itemView) {
                super(itemView);
                tv_name = (TextView) itemView.findViewById(R.id.tv_name);
                iv_picture = (ImageView) itemView.findViewById(R.id.iv_picture);
            }
        }
    }

}
