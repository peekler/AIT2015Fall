package hu.bme.aut.amorg.examples.cameratextureviewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CameraTextureView extends TextureView implements
		TextureView.SurfaceTextureListener {

	private Camera camera;

	public CameraTextureView(Context context) {
		super(context);
		setSurfaceTextureListener(this);
	}

	public CameraTextureView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setSurfaceTextureListener(this);
	}

	@Override
	public void onSurfaceTextureAvailable(SurfaceTexture surface, int width,
			int height) {
		camera = Camera.open();

		try {
			camera.setPreviewTexture(surface);
		} catch (IOException t) {
		}

		Camera.Parameters params = camera.getParameters();
		params.setColorEffect(Camera.Parameters.EFFECT_NEGATIVE);
		camera.setParameters(params);

		camera.setPreviewCallback(new PreviewCallback() {
			@Override
			public void onPreviewFrame(byte[] data, Camera camera) {
				Log.d("PREVIEW", "SIZE: "+data.length);
			}
		});

		camera.startPreview();
	}

	@Override
	public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width,
			int height) {
		// Ignored, the Camera does all the work for us
	}

	@Override
	public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
		camera.setPreviewCallback(null);
		camera.stopPreview();
		camera.release();
		return true;
	}

	@Override
	public void onSurfaceTextureUpdated(SurfaceTexture surface) {
		// Called whenever a new frame is available and displayed in the
		// TextureView
	}

	public void takePhoto(PictureCallback pictureCallback) {
		new TakePictureTask(pictureCallback).execute();
	}

	private class TakePictureTask extends AsyncTask<Void, Void, Void> {
		private PictureCallback pictureCallback;
		public TakePictureTask(PictureCallback pictureCallback) {
			this.pictureCallback = pictureCallback;
		}

		@Override
		protected Void doInBackground(Void... params) {
			camera.takePicture(null, null, pictureCallback);

			// Sleep for however long, you could store this in a variable and
			// have it updated by a menu item which the user selects.
			try {
				Thread.sleep(3000); // 3 second preview
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// This returns the preview back to the live camera feed
			camera.startPreview();
		}
	}
}
