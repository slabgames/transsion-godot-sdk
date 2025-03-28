package com.slabgames.transsionsdk;


import android.app.Activity;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.SignalInfo;
import org.godotengine.godot.plugin.UsedByGodot;

import com.transsion.gamead.AdInitializer;
import com.transsion.gamead.InitListener;
import com.transsion.gamead.constant.InitState;

import java.util.Objects;

public class GodotTranssion extends GodotPlugin  {
    private static final String TAG = "GODOT-TRANSSION";

    public GodotTranssion(Godot godot) {
        super(godot);
    }

    @NonNull
    @Override
    public String getPluginName() {
        return "GodotTranssion";
    }

    @Nullable
    @Override
    public View onMainCreate(Activity activity) {
        init();

        return super.onMainCreate(activity);
    }

    @UsedByGodot
    public void init(){
        AdInitializer.init(
                new AdInitializer.Builder(Objects.requireNonNull(getActivity()).getApplication())
                        //Set setDebuggable to true, which indicates that logging is enabled. Set it to false when the app is realesed in a formal environment.
                        //Even if it is set to false, you can still use the following adb command to enable logging on your debugging device to facilitate debugging and verification.
                        //adb shell setprop log.tag.GameAdLog DEBUG

                        .setDebuggable(false)
                        /*Set the running environment, test indicates a test environment. Set the value to release for an online environment.*/
                        .setEnv("release")
                        //Set test to display the testing server advertisement of AdMob.
                        //If you want to debug real online advertisements, set setEnv to release, enter the device ID, and call setTestDeviceIds.
                        //Refer to FAQs for obtaining the testing device ID. Please delete this line in an official online environment.
                        //.setTestDeviceIds(Collections.singletonList("7FC2C0BE39C47406C984C08C16418C5C"))
                        //Advertising switch. It is enabled by default if not specified.
                        .setTotalSwitch(true)
        );

        AdInitializer.setInitListener(new InitListener() {
            @Override
            public void onStateChange(int state, String message) {
                if (state == InitState.INIT_STATE_COMPLETE) {
                    Log.d(TAG, "Initialization successful. It is recommended to preload interstitial and rewarded ads here.");
                } else if (state == InitState.INIT_STATE_ERROR) {
                    //Most initialization failures are caused by incorrect configuration file locations.
                    Log.d(TAG, "Initialization failed. Cause:" + message);
                }
            }
        });
    }

    @UsedByGodot
    public boolean isInitialized()
    {
        return AdInitializer.isInitialized();
    }
}
