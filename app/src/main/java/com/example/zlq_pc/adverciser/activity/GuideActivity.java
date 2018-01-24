package com.example.zlq_pc.adverciser.activity;
import android.os.Bundle;
import com.example.zlq_pc.adverciser.R;
import com.example.zlq_pc.adverciser.utils.KEYS;
import com.ht.baselib.utils.ActivityUtils;
import com.ht.baselib.utils.SharedPreferencesUtils;
import com.ht.uilib.base.BaseGuideActivity;
import java.util.List;

public class GuideActivity extends BaseGuideActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferencesUtils.put(KEYS.KEY_ISFIRST,"false");
    }

    @Override
    protected void addResIds(List<Integer> resList) {
        resList.add(R.mipmap.guide_01);
        resList.add(R.mipmap.guide_02);
        resList.add(R.mipmap.guide_03);
    }

    @Override
    protected int getIconResId() {
        return R.drawable.guide_dot_selector;
    }

    @Override
    public void onClick() {
        ActivityUtils.startActivityAndFinish(this,HomeActivity.class);
    }
}
