package xion.csonline.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import xion.csonline.R;
import xion.csonline.activity.ChatActivity;
import xion.csonline.activity.MainActivity;
import xion.csonline.adapter.CSEListAdapter;
import xion.csonline.entity.CSEInfo;

/**
 * Created by Administrator on 2016/12/6.
 */

public class CSEFragment extends Fragment {
    private List<CSEInfo> cseList;
    private ListView lv_cse;
    private CSEListAdapter adapter;
    private SwipeRefreshLayout srl_frag_cse;
    private String str_cseName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View CSE_View = inflater.inflate(R.layout.fragment_cse, null);
        lv_cse = (ListView) CSE_View.findViewById(R.id.lv_cse);
        srl_frag_cse = (SwipeRefreshLayout) CSE_View.findViewById(R.id.srl_frag_cse);
        cseList = new ArrayList<>();
        initData();
        setListView();
        setSwipeRefresh();
        return CSE_View;
    }

private void setListView(){
    lv_cse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            CSEInfo cseInfo = cseList.get(position);
            str_cseName = cseInfo.getName();
            Intent intent = new Intent(getContext(), ChatActivity.class);
            intent.putExtra("nickname",str_cseName);
            startActivity(intent);
        }
    });
}
    private void initData() {
        for (int i = 0; i < 6; i++) {
            CSEInfo cseInfo = new CSEInfo();
            cseInfo.setName("客服" + i);
            cseInfo.setTime("刚刚");
            cseInfo.setRecentMessage("你咨询的问题已处理" + i);
            cseInfo.setPortraitId(R.drawable.sherlock);
            cseList.add(cseInfo);
        }
        adapter = new CSEListAdapter(cseList);
        lv_cse.setAdapter(adapter);
    }
    private void setSwipeRefresh() {
        srl_frag_cse.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新操作
                CSEInfo cseInfo = new CSEInfo();
                cseInfo.setName("新客服");
                cseInfo.setTime("2016-10-21");
                cseInfo.setRecentMessage("很高兴为你服务");
                cseInfo.setPortraitId(R.drawable.sherlock);
                cseList.add(cseInfo);

                //让刷新具有一定延迟
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        srl_frag_cse.setRefreshing(false);//停止动画
                        Toast.makeText(getContext(),"刷新完成",Toast.LENGTH_SHORT).show();
                    }
                },3000);
            }
        });
        srl_frag_cse.setProgressViewOffset(false,50,100);//分别为是否进行 缩放，
    }

}
