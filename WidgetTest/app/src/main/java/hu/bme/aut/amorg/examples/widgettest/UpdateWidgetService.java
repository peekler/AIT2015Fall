package hu.bme.aut.amorg.examples.widgettest;

import java.util.Random;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class UpdateWidgetService extends Service {
	private static final String LOG = "WIDGET_LOG";

	private class MyWidgetRefresher extends Thread {

		private int[] widgetIDs;

		public MyWidgetRefresher(int[] aWidgetIDs) {
			widgetIDs = aWidgetIDs;
		}

		public void run() {
			
			while (enabled) {

				for (int widgetId : widgetIDs) {
					int number = (new Random().nextInt(100));

					RemoteViews remoteViews = new RemoteViews(
							UpdateWidgetService.this.getApplicationContext()
									.getPackageName(),
							R.layout.my_widget_layout);

					remoteViews.setTextViewText(R.id.update, "Random: "
							+ String.valueOf(number));

					AppWidgetManager appWidgetManager = AppWidgetManager
							.getInstance(UpdateWidgetService.this
									.getApplicationContext());
					appWidgetManager.updateAppWidget(widgetId, remoteViews);
				}

				try {
					sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private boolean enabled = false;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (!enabled) {
			enabled = true;
			int[] allWidgetIds = intent.getIntArrayExtra(
					AppWidgetManager.EXTRA_APPWIDGET_IDS);
			new MyWidgetRefresher(allWidgetIds).start();
		}

		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		enabled = false;
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
