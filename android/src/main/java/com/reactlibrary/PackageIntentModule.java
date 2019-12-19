package com.reactlibrary;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.Collections;
import java.util.List;

public class PackageIntentModule extends ReactContextBaseJavaModule {

    public PackageIntentModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @ReactMethod
    public Boolean startIntent(String packageName) {
        Intent intent = getIntent(packageName);
        if (intent != null) {
            getReactApplicationContext().startActivity(intent);
            return true;
        }
        return false;
    }

    @ReactMethod
    public void canStartIntent(String packageName, Callback callback) {
        Intent intent = getIntent(packageName);
        callback.invoke(intent != null);
    }

    @ReactMethod
    public List getAppList() {
        PackageManager pm = getReactApplicationContext().getPackageManager();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> appList = pm.queryIntentActivities(mainIntent, 0);
        Collections.sort(appList, new ResolveInfo.DisplayNameComparator(pm));

        for (ResolveInfo rInfo : appList) {
            Log.d("Launcher", "package:" + rInfo.activityInfo.packageName +
                    "; activity: " + rInfo.activityInfo.name);
        }

        return appList;
    }

    private Intent getIntent(String packageName) {
        PackageManager pm = getReactApplicationContext().getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(packageName);
        return intent;
    }

    @Override
    public String getName() {
        return "PackageIntentAndroid";
    }
}
