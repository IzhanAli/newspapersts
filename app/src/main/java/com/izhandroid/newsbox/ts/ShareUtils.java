package com.izhandroid.newsbox.ts;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.izhandroid.newsbox.ts.telugu.Sakshi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;

public class ShareUtils{

    public static Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }


    /*  Create Directory where screenshot will save for sharing screenshot  */

    public static File getMainDirectoryName() {
        //Here we will use getExternalFilesDir and inside that we will make our Demo folder
        //benefit of getExternalFilesDir is that whenever the app uninstalls the images will get deleted automatically.

            File mainDir = new File(Environment.getExternalStorageDirectory(), "Saved News");

            //If File is not present create directory
            if (!mainDir.exists()) {
                if (mainDir.mkdir())
                    Log.e("Create Directory", "Main Directory Created : " + mainDir);
            }
            return mainDir;



    }

   /* public static void shareSS(File file, Context context){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, context.getString(R.string.news_sharecontent));
        //intent.putExtra(Intent.EXTRA_STREAM, uri);//pass uri here
        Uri apkURI = FileProvider.getUriForFile(
                context,
                context.getApplicationContext()
                        .getPackageName() + ".provider", file);
        intent.putExtra(Intent.EXTRA_STREAM, apkURI);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.share_title)));
    }*/


    public static void showSSDialog(final Bitmap bitmap, Context context,  File file, FirebaseAnalytics firebaseAnalytics){
        final Context context1 = context;
        final File file1 = file;
        final FirebaseAnalytics firebaseAnalytics1 = firebaseAnalytics;

        LayoutInflater inflater = LayoutInflater.from(context);
        final View v = inflater.inflate(R.layout.share_lay, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(v);

        ImageButton close = v.findViewById(R.id.share_dialog_close);
        Button share = v.findViewById(R.id.sharebtn);
        Button save  = v.findViewById(R.id.savbtn);
        ImageView imageView = v.findViewById(R.id.img_share_dialog);

        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setGravity(Gravity.CENTER);
        alertDialog.show();


        //TODO Add view elements

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                file1.delete();


            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, context1.getString(R.string.news_sharecontent));
                //intent.putExtra(Intent.EXTRA_STREAM, uri);//pass uri here
                Uri apkURI = FileProvider.getUriForFile(
                        context1,
                        context1.getApplicationContext()
                                .getPackageName() + ".provider", file1);
                intent.putExtra(Intent.EXTRA_STREAM, apkURI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                context1.startActivity(Intent.createChooser(intent, "Share article"));
                Bundle params = new Bundle();
                params.putString("msg", "ss shared");
                params.putString("title", "Share");
                firebaseAnalytics1.logEvent("SharingLog", params);
                alertDialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MediaScannerConnection.scanFile(context1, new String[]{file1.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String s, Uri uri) {
                        alertDialog.dismiss();
                        Toast.makeText(context1, "Saved Successfully!", Toast.LENGTH_SHORT).show();
                        Bundle params = new Bundle();
                        params.putString("msg", "ss saved");
                        params.putString("title", "Saved");
                        firebaseAnalytics1.logEvent("SharingLog", params);
                    }
                });

            }
        });

        imageView.setImageBitmap(bitmap);


        //
    }



    /*  Store taken screenshot into above created path  */
    public static File store(Bitmap bm, String fileName, File saveFilePath){
        File dir = new File(saveFilePath.getAbsolutePath());
        if (!dir.exists())
            dir.mkdirs();
        File file = new File(saveFilePath.getAbsolutePath(), fileName);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


    public static void chkntake(Context context, AppBarLayout barLayout,LinearLayout root, RelativeLayout relativeLayout,  MaterialCardView cardView, FloatingActionButton fab, String name, FirebaseAnalytics analytics){
        Bitmap b = null;

        if ((ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)&&ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {


            TedPermission.with(context)
                    .setPermissionListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted() {


                        }

                        @Override
                        public void onPermissionDenied(List<String> deniedPermissions) {


                        }
                    })
                    .setRationaleMessage("We need to storage permission for Saving/Sharing News")
                    .setRationaleConfirmText("OK")
                    .setDeniedMessage("If you deny permission, you can not use this feature\n\nPlease turn on permissions from 'App Settings'")
                    .setDeniedCloseButtonText("Dismiss")
                    .setDeniedTitle("Storage Permission Denied!")
                    .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)


                    .check();

        }else{
            barLayout.setVisibility(View.INVISIBLE);
            fab.setVisibility(View.INVISIBLE);
            cardView.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.VISIBLE);
            b = getScreenShot(root);
            barLayout.setVisibility(View.VISIBLE);
            fab.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.INVISIBLE);
            relativeLayout.setVisibility(View.INVISIBLE);

            if (b != null) {


                // showScreenShotImage(b);//show bitmap over imageview

                File saveFile = getMainDirectoryName();//get the path to save screenshot
                File file = store(b, name + Calendar.getInstance().getTime().toString() + ".jpg", saveFile);//save the screenshot to selected path
                //shareScreenshot(file, Sakshi.this);//finally share screenshot
                showSSDialog(b, context, file, analytics);
            } else {
                //If bitmap is null show toast message
                Toast.makeText(context, "failed to share news", Toast.LENGTH_SHORT).show();
                Bundle params = new Bundle();
                params.putString("msg", "ss did not took");
                params.putString("title", "bitmap was null");
                analytics.logEvent("ErrorinSS", params);
            }
        }
        }

}
