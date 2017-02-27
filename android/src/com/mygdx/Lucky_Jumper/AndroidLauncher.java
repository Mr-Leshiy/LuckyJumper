package com.mygdx.Lucky_Jumper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class AndroidLauncher extends AndroidApplication implements AdHandler
{

	private AdView adView;
	private InterstitialAd adActivity;
	private final int SHOW_AD=1;
	private final int HIDE_AD=0;
	private final int SHOW_AD_ACTIVITY=-1;

	private Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case SHOW_AD:
					adView.setVisibility(View.VISIBLE);
					break;
				case HIDE_AD:
					adView.setVisibility(View.GONE);
					break;
				case SHOW_AD_ACTIVITY:
					adActivity.show();
					break;
			}
		}

	};


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useCompass=false;
		config.useAccelerometer=false;

		RelativeLayout layout = new RelativeLayout(this);

		View gameView =initializeForView(new GameClass(this),config);

		adView = new AdView(this);
		adView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				Log.i("Android_launcher","Ad Loading .....");
			}
		});

		adView.setAdSize(AdSize.BANNER);
		adView.setAdUnitId("ca-app-pub-2408703385236595/6943908869");
		adView.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				super.onAdClosed();
				AdRequest.Builder builder = new AdRequest.Builder();
				adActivity.loadAd(builder.build());
			}
		});

		adActivity = new InterstitialAd(this);
		adActivity.setAdUnitId("ca-app-pub-2408703385236595/6681366862");
		adActivity.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				super.onAdClosed();
				AdRequest.Builder builder = new AdRequest.Builder();
				adActivity.loadAd(builder.build());
			}
		});

		layout.addView(gameView);
		AdRequest.Builder builder = new AdRequest.Builder();
		builder.addTestDevice("9BE9B1868EB225F94EB07CAA4CAE81F0");
		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		adParams.addRule(RelativeLayout.CENTER_IN_PARENT);

		AdRequest.Builder builder2 = new AdRequest.Builder();


		layout.addView(adView,adParams);
		adView.loadAd(builder.build());
		adActivity.loadAd(builder2.build());
		setContentView(layout);

	}

	@Override
	public void showAds(boolean show) {
		handler.sendEmptyMessage(show ? SHOW_AD:HIDE_AD);
	}
	@Override
	public void showAdActivity()
	{
		handler.sendEmptyMessage(SHOW_AD_ACTIVITY);

	}
	@Override
	public void submitScore(int score)
	{
	}


}
