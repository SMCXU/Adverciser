package com.example.zlq_pc.adverciser;
import com.ht.baselib.utils.ActivityUtils;
import com.ht.uilib.base.BaseGuideActivity;

import java.util.List;

public class GuideActivity extends BaseGuideActivity {


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
