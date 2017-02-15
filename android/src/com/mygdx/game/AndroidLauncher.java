package com.mygdx.game;
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

public class AndroidLauncher extends AndroidApplication implements AdHandler {

	private AdView adView;

	private final int SHOW_AD=1;
	private final int HIDE_AD=0;


	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
				case SHOW_AD:
					adView.setVisibility(View.VISIBLE);
					break;
				case HIDE_AD:
					adView.setVisibility(View.GONE);
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

		layout.addView(gameView);
		AdRequest.Builder builder = new AdRequest.Builder();
		builder.addTestDevice("9BE9B1868EB225F94EB07CAA4CAE81F0");
		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		adParams.addRule(RelativeLayout.CENTER_IN_PARENT);

		layout.addView(adView,adParams);
		adView.loadAd(builder.build());
		setContentView(layout);

  }

	@Override
	public void showAds(boolean show) {
		handler.sendEmptyMessage(show ? SHOW_AD:HIDE_AD);
	}
}
