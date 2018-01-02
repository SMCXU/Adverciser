package com.ht.baselib.manager;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Author:  Tom
 * Date:    2015/3/9.
 */
public class AppManager {

    private static AppManager instance = null;
    private static List<Activity> mActivities = new LinkedList<Activity>();

    private AppManager() {

    }

    public static AppManager getInstance() {
        if (instance == null){
            synchronized (AppManager.class){
                if (instance == null) {
                    instance = new AppManager();
                }
            }
        }
        return instance;
    }

    public int size() {
        return mActivities.size();
    }

    public synchronized Activity getForwardActivity() {
        return size() > 0 ? mActivities.get(size() - 1) : null;
    }

    public synchronized void addActivity(Activity activity) {
        mActivities.add(activity);
    }

    public synchronized void removeActivity(Activity activity) {
        if (mActivities.contains(activity)) {
            mActivities.remove(activity);
        }
    }

    public synchronized void clear() {
        for (int i = mActivities.size() - 1; i > -1; i--) {
            Activity activity = mActivities.get(i);
            removeActivity(activity);
            activity.finish();
            i = mActivities.size();
        }
    }

    public synchronized void clearToTop() {
        for (int i = mActivities.size() - 2; i > -1; i--) {
            Activity activity = mActivities.get(i);
            removeActivity(activity);
            activity.finish();
            i = mActivities.size() - 1;
        }
    }
}
