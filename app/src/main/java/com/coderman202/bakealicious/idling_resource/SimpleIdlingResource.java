package com.coderman202.bakealicious.idling_resource;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitor;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;


public class SimpleIdlingResource implements IdlingResource {

    @Nullable
    private volatile ResourceCallback resourceCallback;

    private AtomicBoolean isIdle = new AtomicBoolean(true);

    private final ActivityLifecycleMonitor activityLifecycleMonitor;
    private final String activityName;

    public SimpleIdlingResource(String activityName){
        activityLifecycleMonitor = ActivityLifecycleMonitorRegistry.getInstance();
        this.activityName = activityName;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return isIdle.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        resourceCallback = callback;
    }

    private boolean isActivityLaunched() {
        Collection<Activity> activitiesInStage = activityLifecycleMonitor.getActivitiesInStage(Stage.RESUMED);
        for (Activity activity : activitiesInStage) {
            if(activity.getClass().getName().equals(activityName)){
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the new idle state, if isIdleNow is true, it pings the {@link ResourceCallback}.
     * @param isIdleNow false if there are pending operations, true if idle.
     */
    public void setIdleState(boolean isIdleNow) {
        isIdle.set(isIdleNow);
        if (isIdleNow && resourceCallback != null) {
            resourceCallback.onTransitionToIdle();
        }
    }
}
