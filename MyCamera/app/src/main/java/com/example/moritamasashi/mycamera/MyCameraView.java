package com.example.moritamasashi.mycamera;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.TextureView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.sql.Date;

/**
 * Created by moritamasashi on 16/02/20.
 */
public class MyCameraView extends TextureView implements TextureView.SurfaceTextureListener, Camera.PictureCallback {
    private Camera camera;

    public MyCameraView(Context context){
        super(context);

        setSurfaceTextureListener(this);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture texture,int width,int height){
        try{
            camera = Camera.open();
            if(camera == null) camera = Camera.open(0);
            camera.setPreviewTexture(texture);
            camera.startPreview();
        }catch(Exception e){

        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture texture,int width,int height){

    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture textrue){

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture texture){
        camera.setPreviewCallback(null);
        camera.stopPreview();
        camera.release();
        camera = null;
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            camera.takePicture(null,null,this);
        }
        return true;
    }

    public void onPictureTaken(byte[] data,Camera cameara){
        try {
            SimpleDateFormat format = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss'.jpg'", Locale.getDefault());
            String fileName = format.format(new Date(System.currentTimeMillis()));

            String path = Environment.getExternalStorageDirectory()+"/" + fileName;
            saveToSD(data,path);


            MediaScannerConnection.scanFile(this.getContext(), new String[]{path}, new String[]{"image/jpeg"}, null);
            toast("Complete taking Picture");
        }catch(Exception e){
            toast(e.toString());
        }

        camera.startPreview();
    }


    private void saveToSD(byte[] data,String path) throws Exception{
        FileOutputStream out = null;
        try{
            out = new FileOutputStream(path);
            out.write(data);
            out.close();
        }catch(Exception e) {
            if (out != null) out.close();
            throw e;
        }
    }

    private void toast(String text){
        if(text == null) text = "";
        Toast.makeText(getContext(),text,Toast.LENGTH_LONG).show();
    }
}
