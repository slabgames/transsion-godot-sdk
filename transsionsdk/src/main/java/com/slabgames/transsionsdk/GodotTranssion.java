package com.slabgames.transsionsdk;


import android.app.Activity;
import android.util.ArraySet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.SignalInfo;
import org.godotengine.godot.plugin.UsedByGodot;

import com.transsion.gamead.AdHelper;
import com.transsion.gamead.AdInitializer;
import com.transsion.gamead.GameAdBannerListener;
import com.transsion.gamead.GameAdLoadListener;
import com.transsion.gamead.GameAdRewardShowListener;
import com.transsion.gamead.GameAdShowListener;
import com.transsion.gamead.GameRewardItem;
import com.transsion.gamead.InitListener;
import com.transsion.gamead.OnOpenAppLoadListener;
import com.transsion.gamead.OnOpenAppShowListener;
import com.transsion.gamead.constant.InitState;
import com.transsion.gamead.impl.TGBannerView;

import java.util.Objects;
import java.util.Set;

public class GodotTranssion extends GodotPlugin  {
    private static final String TAG = "GODOT-TRANSSION";
    private TGBannerView mTGBannerView;

    private Activity mActivity;
    private GameAdLoadListener _interstitialListener;
    private GameAdRewardShowListener _rewardedListener;
    private GameAdLoadListener _rewardedLoadedListener;

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
        mActivity = getActivity();
        init();

        return super.onMainCreate(activity);
    }

    @NonNull
    @Override
    public Set<SignalInfo> getPluginSignals() {
        Set<SignalInfo> signals = new ArraySet<>();

        // General
        signals.add(new SignalInfo("on_plugin_error", String.class));

        signals.add(new SignalInfo("on_init_completed"));
        signals.add(new SignalInfo("on_init_failed", String.class));


        // Rewarded
        signals.add(new SignalInfo("on_rewarded_showed"));
        signals.add(new SignalInfo("on_rewarded_loaded"));
        signals.add(new SignalInfo("on_rewarded_load_failed", String.class));
        signals.add(new SignalInfo("on_rewarded_closed"));
        signals.add(new SignalInfo("on_rewarded_clicked"));
        signals.add(new SignalInfo("on_rewarded"));

        // Interstitial
        signals.add(new SignalInfo("on_interstitial_loaded"));
        signals.add(new SignalInfo("on_interstitial_load_failed", String.class));
        signals.add(new SignalInfo("on_interstitial_showed"));
        signals.add(new SignalInfo("on_interstitial_closed"));
        signals.add(new SignalInfo("on_interstitial_clicked"));


        // Banner
        signals.add(new SignalInfo("on_banner_loaded"));
        signals.add(new SignalInfo("on_banner_showed"));
        signals.add(new SignalInfo("on_banner_closed"));

        // App Open
        signals.add(new SignalInfo("on_appopen_loaded"));
        signals.add(new SignalInfo("on_appopen_load_failed", String.class));
        signals.add(new SignalInfo("on_appopen_showed"));
        signals.add(new SignalInfo("on_appopen_closed"));
        signals.add(new SignalInfo("on_appopen_clicked"));


        return signals;
    }

    @UsedByGodot
    public void init(){
        mActivity.runOnUiThread(() -> {
            AdInitializer.init(
                    new AdInitializer.Builder(Objects.requireNonNull(mActivity).getApplication())
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
                        emitSignal("on_init_completed",message);
                    } else if (state == InitState.INIT_STATE_ERROR) {
                        //Most initialization failures are caused by incorrect configuration file locations.
                        Log.d(TAG, "Initialization failed. Cause:" + message);
                        emitSignal("on_init_failed",message);
                    }
                }
            });
        });

    }

    @UsedByGodot
    public boolean isInitialized()
    {
        return AdInitializer.isInitialized();
    }
    
    @UsedByGodot
    public void initBanner()
    {
        Objects.requireNonNull(mActivity).runOnUiThread(() -> {
            if(mTGBannerView==null) {
                //TGBannerView only needs to be created once. This object can be used to perform various operations, such as displaying and closing ads.
                mTGBannerView = AdHelper.newInstanceTGBannerView(mActivity);
            }
            //Set the ad listener.
            mTGBannerView.setListener(new GameAdBannerListener() {
                @Override
                public void onAdFailedToLoad(int code, String message) {
                    Log.i(TAG, "Banner ad loading failed. Error code:" + code + "; error message:" + message);
                    emitSignal("on_plugin_error",message);
                }

                @Override
                public void onAdOpened() {
                    Log.i(TAG, "Banner onAdOpened");
                    emitSignal("on_banner_showed");
                }

                @Override
                public void onAdImpression() {
                    Log.i(TAG, "Banner onAdImpression");
                }

                @Override
                public void onAdLoaded() {
                    Log.i(TAG, "Banner onAdLoaded");
                    emitSignal("on_banner_loaded");

                }

                @Override
                public void onAdClosed() {
                    Log.i(TAG, "Banner onAdClosed");
                    //This callback exists for some ads.
                    emitSignal("on_banner_closed");
                }
            });
            //Load the ad. (This method will automatically add the ad to the layout by default and display it in full at the bottom.)
            mTGBannerView.load(mActivity);
        });
    }

    @UsedByGodot
    public void hideBanner(){
        Objects.requireNonNull(mActivity).runOnUiThread(() -> {
            //Close the banner ad.
            mTGBannerView.close(mActivity);
            //If you want to load the ad again after it is closed, you can use the mTGBannerView object and do not need to call the AdHelper.newInstanceTGBannerView API again.
            mTGBannerView.load(mActivity);
        });
    }

    @UsedByGodot
    public void destroyBanner()
    {
        mActivity.runOnUiThread(() -> {
            //If you exit the game or the current page no longer needs to display the banner ad, call the API below to destroy the ad.
            mTGBannerView.destroy(mActivity);
            mTGBannerView=null;
        });
    }

    @UsedByGodot
    public void setBannerLocation(final String location)
    {
        //Developers can pass in the layout parameters when loading the ad.
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTGBannerView.load(mActivity,true,layoutParams);

        //If developers want to adjust the layout, they can obtain the layout configuration in TGBannerView and set the layout again.
        FrameLayout.LayoutParams configLayoutParamsInfo = mTGBannerView.getConfigLayoutParamsInfo();
        //Display the banner ad at the top.
        if (Objects.equals(location, "TOP"))
            configLayoutParamsInfo.gravity = Gravity.TOP;
        else if (Objects.equals(location, "BOTTOM")) {
            configLayoutParamsInfo.gravity = Gravity.BOTTOM;
        }
        //The banner width has been set to 400px. Note: Ad display may fail if the width value is too small. It is recommended that you check the effect in a formal environment.
        //Note: The unit is px, and you may need to convert between dp and px depending on the device with different pixel densities.
        configLayoutParamsInfo.width = 400;
        mTGBannerView.load(mActivity);

        //Developers can call the method below to add TGBannerView to their own containers.
        //When this method is called, the ad will not be automatically added to the root layout of the interface. Developers need to perform other configurations to display the ad.
        //mTGBannerView.load(this,false);
    }

    @UsedByGodot
    public void loadInterstitial()
    {
        mActivity.runOnUiThread(() -> {
            _interstitialListener =  new GameAdLoadListener() {
                @Override
                public void onAdLoaded() {
                    Log.d(TAG, "onAdLoaded: The interstitial ad has been preloaded and is ready for display.");
                    emitSignal("on_interstitial_loaded");
                    //Do not directly call the display API in the callback of successful preloading. The preloading logic should be separated from the display logic.
                }

                @Override
                public void onAdFailedToLoad(int i, String s) {
                    //Interstitial ad preloading can generally be retried twice at an interval of 10 seconds. Preloading failure will be called back after multiple retries.
                    //Do not initiate retry requests directly using this API as it will generate many useless requests and may lead to high app latency.
                    //If a retry is required, retry at the appropriate time or limit the number of retries.
                    Log.d(TAG, "onAdFailedToLoad: Failed to load the interstitial ad. Error code:" + i + ". Cause:" + s);
                    emitSignal("on_interstitial_load_failed",s);
                }
            };

            //It is recommended that the ad be preloaded after a successful initialization.
            AdHelper.loadInterstitial(mActivity, _interstitialListener);
        });

    }

    @UsedByGodot
    public void showInterstitial()
    {
        mActivity.runOnUiThread(() -> {
            //Checks whether the interstitial ad is ready when it needs to be displayed. If it is not ready, it can be preloaded again here.
            if (AdHelper.isInterstitialReady()) {
                AdHelper.showInterstitial(mActivity, new GameAdShowListener() {
                    @Override
                    public void onShow() {
                        Log.d(TAG, "The interstitial ad is displayed.");
                        emitSignal("on_interstitial_showed");
                    }

                    @Override
                    public void onShowFailed(int i, String s) {
                        Log.d(TAG, "Failed to display the interstitial ad. Error code:"+i+". Error message="+s);
                        emitSignal("on_plugin_error",s);
                    }

                    @Override
                    public void onClose() {
                        Log.d(TAG, "The interstitial ad is closed.");
                        emitSignal("on_interstitial_closed");
                    }

                    @Override
                    public void onClick() {
                        Log.d(TAG, "The interstitial ad has been clicked.");
                        emitSignal("on_interstitial_clicked");
                    }

                    @Override
                    public void onAdImpression() {
                        Log.d(TAG, "The interstitial ad has gained impressions.");
                    }
                });
            }else{
                Log.i(TAG, "Interstitial ad is not ready!");
                //If the ad is not fully preloaded, or the preloading fails, developers can preload the ad here.
                AdHelper.loadInterstitial(mActivity,_interstitialListener);
            }
        });

    }

    @UsedByGodot
    public void destroyInterstitial()
    {
        mActivity.runOnUiThread(AdHelper::destroyInterstitial);
    }

    @UsedByGodot
        public void loadRewarded()
    {
        _rewardedListener = new GameAdRewardShowListener() {
            @Override
            public void onUserEarnedReward(GameRewardItem rewardItem) {
                Log.i(TAG, "Reward onUserEarnedReward " + rewardItem.getType() + " " + rewardItem.getAmount());
                emitSignal("on_rewarded");
            }

            @Override
            public void onShow() {
                Log.i(TAG, "The rewarded ad is displayed.");
                emitSignal("on_rewarded_showed");
            }

            @Override
            public void onShowFailed(int code, String message) {
                Log.i(TAG, "Failed to display the rewarded ad. Error code:"+code+". Error message:"+message);
                emitSignal("on_plugin_error",message);
            }

            @Override
            public void onClose() {
                Log.d(TAG, "The rewarded ad is closed.");
                emitSignal("on_rewarded_closed");
            }

            @Override
            public void onClick() {
                Log.i(TAG, "The rewarded ad has been clicked.");
                emitSignal("on_rewarded_clicked");
            }

            @Override
            public void onAdImpression() {
                Log.i(TAG, "The rewarded ad has gained impressions.");
            }

        };

        _rewardedLoadedListener = new GameAdLoadListener() {
            @Override
            public void onAdLoaded() {
                Log.d(TAG, "onAdLoaded: The rewarded ad has been preloaded successfully and is ready for display.");
                //Do not directly call the display API in the callback of successful preloading. The preloading logic should be separated from the display logic.
                emitSignal("on_rewarded_loaded");
            }

            @Override
            public void onAdFailedToLoad(int i, String s) {
                //Rewarded ad preloading can generally be retried twice at an interval of 10 seconds. Preloading failure will be called back after multiple retries.
                //Do not initiate retry requests directly using this API as it will generate many useless requests and may lead to high app latency.
                //If a retry is required, retry at the appropriate time or limit the number of retries.
                Log.d(TAG, "onAdFailedToLoad: Failed to preload the rewarded ad. Error code:" + i + ". Cause:" + s);
                emitSignal("on_rewarded_load_failed",s);
            }
        };
        //It is recommended that the ad be preloaded after a successful initialization.
        AdHelper.loadReward(mActivity, _rewardedLoadedListener);
    }

    @UsedByGodot
    public void showRewarded()
    {
        mActivity.runOnUiThread(() -> {
            //When the ad needs to be displayed, check whether it is ready. If it is not ready, preload it here.

            if(AdHelper.isRewardReady())
            {
                AdHelper.showReward(mActivity,_rewardedListener);
            }else{
                Log.i(TAG, "Reward ad is not ready!");
                //If the ad is not fully preloaded, or the preloading fails, developers can preload the ad here.
                AdHelper.loadReward(mActivity,_rewardedLoadedListener);
            }
        });
    }

    @UsedByGodot
    public void destroyRewarded()
    {
        mActivity.runOnUiThread(AdHelper::destroyReward);
    }

    @UsedByGodot
    public void loadAppOpen()
    {
        mActivity.runOnUiThread(() -> {
            AdHelper.loadAppOpenAd(mActivity,new OnOpenAppLoadListener() {
                @Override
                public void onAdError(int errorCode, String errorMsg) {
                    Log.e(TAG, "Loading of open-screen ads failed: error code = "+errorCode+", error message = "+errorMsg);
                    emitSignal("on_appopen_load_failed",errorMsg);
                }

                @Override
                public void onAdLoaded() {
                    Log.d(TAG, "Open screen advertisement loading completed");
                    emitSignal("on_appopen_loaded");
                }
            });
        });

    }

    @UsedByGodot
    public void showAppOpen()
    {
        mActivity.runOnUiThread(() -> {
            if(AdHelper.isOpenAppAdReady()){
                AdHelper.showAppOpenAd(mActivity, new OnOpenAppShowListener() {
                    @Override
                    public void onAdError(int errorCode, String errorMsg) {
                        Log.e(TAG, "Open screen ad display error: error code = "+errorCode+", error message = "+errorMsg);
                        emitSignal("on_plugin_error",errorMsg);
                    }

                    @Override
                    public void onAdShowed() {
                        Log.d("GAD_Open","Open screen ad displayed successfully");
                        emitSignal("on_appopen_showed");
                    }

                    @Override
                    public void onAdDismissed() {
                        Log.d("GAD_Open","Open screen advertising closed");
                        emitSignal("on_appopen_closed");
                    }

                    @Override
                    public void onAdClicked() {
                        Log.d("GAD_Open","Open screen ad click");
                        emitSignal("on_appopen_clicked");
                    }
                });
            }else{
                Log.d("GAD_Open","The ad has not been loaded successfully, it is recommended that you wait for the ad to load successfully or initiate loading of the ad");
            }
        });
    }


    @UsedByGodot
    public void destroyAppOpen()
    {
        mActivity.runOnUiThread(AdHelper::destroyAppOpenAd);
    }

    @Override
    public void onMainDestroy() {
        destroyBanner();
        destroyInterstitial();
        destroyRewarded();
        destroyAppOpen();

        super.onMainDestroy();
    }
}
