package hu.bme.aut.amorg.examples.widgettest;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyWidgetProvider extends AppWidgetProvider {

	private static final String LOG = "WIDGET_LOG";
	
	private static final String ACTION_CLICK = "ACTION_CLICK";

	@Override
	public void onDisabled(Context context) {
		
		Intent intent = new Intent(context.getApplicationContext(),
		        UpdateWidgetService.class);
   		context.stopService(intent);
		
		super.onDisabled(context);
	}
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		Log.w(LOG, "onUpdate method called");
	    // Get all ids
	    ComponentName thisWidget = new ComponentName(context,
	        MyWidgetProvider.class);
	    int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

	    // Build the intent to call the service
	    Intent intent = new Intent(context.getApplicationContext(),
	        UpdateWidgetService.class);
	    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);

	    // Update the widgets via the service
	    context.startService(intent);
	}
}