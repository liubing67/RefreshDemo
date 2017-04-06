package com.abing.refreshdemo.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abing.refreshdemo.MainActivity;
import com.abing.refreshdemo.R;
import com.abing.refreshdemo.base.BaseFragment;
import com.abing.refreshdemo.model.RefreshModel;
import com.abing.refreshdemo.util.ThreadUtil;
import com.abing.refreshdemo.util.ToastUtil;

import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildLongClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemLongClickListener;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 项目名称：RefreshDemo
 * 类描述：
 * 创建人：liubing
 * 创建时间：2017/4/6 13:42
 * 修改人：Administrator
 * 修改时间：2017/4/6 13:42
 * 修改备注：
 */
public class RefreshSwipeRecyclerViewFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    private BGARefreshLayout mRefreshLayout;
    private int mNewPageNumber = 0;
    private int mMorePageNumber = 0;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_recyclerview_refresh);
        mRefreshLayout = getViewById(R.id.rl_recyclerview_refresh);
    }

    @Override
    protected void setListener() {

        mRefreshLayout.setDelegate(this);

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(mApp, true);
        moocStyleRefreshViewHolder.setOriginalImage(R.mipmap.bga_refresh_moooc);
        moocStyleRefreshViewHolder.setUltimateColor(R.color.imoocstyle);
        mRefreshLayout.setRefreshViewHolder(moocStyleRefreshViewHolder);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {


        ThreadUtil.runInUIThread(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endRefreshing();
                ToastUtil.show("刷新");
            }
        }, MainActivity.LOADING_DURATION);

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {

        ThreadUtil.runInUIThread(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endLoadingMore();
                ToastUtil.show("加载");
            }
        }, MainActivity.LOADING_DURATION);
        return true;
    }
}
