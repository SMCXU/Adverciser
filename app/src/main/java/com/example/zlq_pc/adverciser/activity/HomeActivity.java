package com.example.zlq_pc.adverciser;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.example.zlq_pc.adverciser.fragments.AdverciserFragment;
import com.example.zlq_pc.adverciser.fragments.MemberCenterFragment;
import com.example.zlq_pc.adverciser.fragments.RequestFragment;
import com.ht.uilib.base.BaseHomeActivity;

import java.util.List;
import java.util.Map;

public class HomeActivity extends BaseHomeActivity {

    @Override
    protected void addHomeFragment(List<Fragment> fragmentList) {
        fragmentList.add(new AdverciserFragment());
        fragmentList.add(new RequestFragment());
        fragmentList.add(new MemberCenterFragment());
    }

    @Override
    protected void addHomeTabInfo(Map<String, Integer> homeTabInfoMap) {
        homeTabInfoMap.put("顾问",R.drawable.home_tab0_icon_selector);
        homeTabInfoMap.put("发需求",R.drawable.home_tab1_icon_selector);
        homeTabInfoMap.put("我的",R.drawable.home_tab2_icon_selector);
    }
}
