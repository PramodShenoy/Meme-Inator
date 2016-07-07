package yorozuyastudios.pro.com.meme_inator;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputFilter;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class EnterTextActivity extends Activity implements View.OnClickListener {

    private static final String APP_PICTURE_DIRECTORY = "/Memeify";
    private static final String FILE_SUFFIX_JPG = ".jpg";
    private static final String HELVETICA_FONT = "Helvetica";
    private static final String BITMAP_WIDTH = "BITMAP_WIDTH";
    private static final String BITMAP_HEIGHT = "BITMAP_HEIGHT";
    private String path;
    private EditText topTextEditText;
    private EditText bottomTextEditText;
    private int resId;
    private int flag1;
    private Bitmap viewBitmap;
    private boolean originalImage = false;

    private ImageView selectedPicture;
    private Button writeTextToImageButton;
    private Button saveImageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertext);

        selectedPicture = (ImageView) findViewById(R.id.selected_picture_imageview);

        writeTextToImageButton = (Button) findViewById(R.id.write_text_to_image_button);
        writeTextToImageButton.setOnClickListener(this);

        saveImageButton = (Button) findViewById(R.id.save_image_button);
        saveImageButton.setOnClickListener(this);

        topTextEditText = (EditText) findViewById(R.id.top_text_edittext);
        bottomTextEditText = (EditText) findViewById(R.id.bottom_text_edittext);

        topTextEditText.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        bottomTextEditText.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        originalImage = true;

        Intent i=getIntent();
        flag1=i.getIntExtra("flag",0);
        if(flag1==0) {
            path = i.getStringExtra("path");
            Bitmap selectedImageBitmap = ChooseActivity.scaleDown(BitmapFactory.decodeFile(path), 1000, true);
            selectedPicture.setImageBitmap(selectedImageBitmap);
        }
        else if(flag1==1)
        {
            resId=i.getIntExtra("res-id",0);
            Bitmap bmp=BitmapFactory.decodeResource(getResources(),resId);
            Bitmap scaled=ChooseActivity.scaleDown(bmp,1000,true);
            selectedPicture.setImageBitmap(scaled);

        }


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.write_text_to_image_button:
                createMeme();
                break;

            case R.id.save_image_button:
                saveImageToGallery(viewBitmap);
                break;
        }
    }

    private void createMeme() {

        // Get strings to place into image
        String topText = topTextEditText.getText().toString();
        String bottomText = bottomTextEditText.getText().toString();

        if (!originalImage && flag1==0) {
            Bitmap bm = BitmapResizer.ShrinkBitmap(path.toString(), selectedPicture.getWidth(), selectedPicture.getHeight());
            selectedPicture.setImageBitmap(bm);
        }
        else if(!originalImage &&flag1==1)
        {
            Bitmap bmp=BitmapFactory.decodeResource(getResources(),resId);
            Bitmap scaled=ChooseActivity.scaleDown(bmp,1000,true);
            selectedPicture.setImageBitmap(scaled);
        }

        // get bitmap from imageView and copy to make mutable
        BitmapDrawable imageDrawable = (BitmapDrawable) selectedPicture.getDrawable();
        viewBitmap = imageDrawable.getBitmap();
        viewBitmap = viewBitmap.copy(viewBitmap.getConfig(), true);

        // add the text you want to your bitmap
        addTextToBitMap(viewBitmap, topText, bottomText);

        // set your imageview to show your newly edited bitmap to
        selectedPicture.setImageBitmap(viewBitmap);
        originalImage = false;
    }

    private void addTextToBitMap(Bitmap viewBitmap, String topText, String bottomText) {

        // get dimensions of image
        int bitmapWidth = viewBitmap.getWidth();
        int bitmapHeight = viewBitmap.getHeight();

        // create a canvas that uses the bitmap as its base
        Canvas pictureCanvas = new Canvas(viewBitmap);

        // create paint object with font parameters

        Typeface tf = Typeface.create(HELVETICA_FONT, Typeface.BOLD);

        int textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                18,
                getResources().getDisplayMetrics());

        Paint textPaint = new Paint();
        textPaint.setTextSize(textSize);
        textPaint.setColor(Color.WHITE);
        textPaint.setTypeface(tf);
        textPaint.setTextAlign(Paint.Align.CENTER);

        Paint textPaintOutline = new Paint();
        textPaintOutline.setAntiAlias(true);
        textPaintOutline.setTextSize(textSize);
        textPaintOutline.setColor(Color.BLACK);
        textPaintOutline.setTypeface(tf);
        textPaintOutline.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaintOutline.setTextAlign(Paint.Align.CENTER);
        textPaintOutline.setStrokeWidth(8);

        float xPos = bitmapWidth / 2;
        float yPos = bitmapHeight / 7;

        pictureCanvas.drawText(topText, xPos, yPos, textPaintOutline);
        pictureCanvas.drawText(topText, xPos, yPos, textPaint);

        yPos = bitmapHeight - bitmapHeight / 14;

        pictureCanvas.drawText(bottomText, xPos, yPos, textPaintOutline);
        pictureCanvas.drawText(bottomText, xPos, yPos, textPaint);
        pictureCanvas.save();
    }

    private void saveImageToGallery(Bitmap memeBitmap) {

        if (!originalImage) {

            // save bitmap to file
           // File imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + APP_PICTURE_DIRECTORY), memeBitmap + FILE_SUFFIX_JPG);
            File imageFile=new File(Environment.getExternalStorageDirectory(),memeBitmap + FILE_SUFFIX_JPG);
            try {
                // create outputstream, compress image and write to file, flush and close outputstream
                FileOutputStream fos = new FileOutputStream(imageFile);
                memeBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                Toast.makeText(this,"SAVE IMAGE FAILED", Toast.LENGTH_SHORT).show();
            }
            // Create intent to request newly created file to be scanned, pass picture uri and broadcast intent
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            mediaScanIntent.setData(Uri.fromFile(imageFile));
            sendBroadcast(mediaScanIntent);


            Toast.makeText(this, "SUCCESSFULLY SAVED THE MEME", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "WHY YOU NO GENERATE MEME?!!", Toast.LENGTH_SHORT).show();
        }
    }
}
