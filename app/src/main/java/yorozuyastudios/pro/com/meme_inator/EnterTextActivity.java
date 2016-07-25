package yorozuyastudios.pro.com.meme_inator;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputFilter;
import android.util.TypedValue;
import android.view.Display;
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
    private Uri imageUri;
    private EditText topTextEditText;
    private EditText bottomTextEditText;
    private EditText midTextEditText;
    private int resId;
    private int flag1;
    private Bitmap viewBitmap;
    private boolean originalImage = false;
    int ht;
    int wdt;
    int ht1;
    int wdt1;
    int screenht;
    int screenwdt;

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
        midTextEditText=(EditText) findViewById(R.id.mid_text_edittext);


        topTextEditText.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        bottomTextEditText.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        midTextEditText.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        originalImage = true;
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
         screenwdt = size.x;
         screenht = size.y;

        Intent i=getIntent();
        flag1=i.getIntExtra("flag",0);

        if(flag1==0) {
            path = i.getStringExtra("path");
             ht= i.getIntExtra(BITMAP_HEIGHT, 1280);
             wdt=i.getIntExtra(BITMAP_WIDTH, 960);
           // Toast.makeText(this,ht+" "+wdt,Toast.LENGTH_LONG).show();
           /*
            if ((ht >=1080 && ht<=1980)||(wdt<=1920 && wdt >=1080)) {
                Bitmap selectedImageBitmap = ChooseActivity.scaleDown(BitmapFactory.decodeFile(path), 500, true);
                selectedPicture.setImageBitmap(selectedImageBitmap);
            } else if((ht<=800||wdt<=800))
            {
                Bitmap selectedImageBitmap = ChooseActivity.scaleDown(BitmapFactory.decodeFile(path), 1500, true);
                selectedPicture.setImageBitmap(selectedImageBitmap);
            }
            else
                selectedPicture.setImageBitmap(BitmapFactory.decodeFile(path));*/
            if(screenht>=1920&&screenwdt>=1080) {//full HD and above
                Bitmap selectedImageBitmap = ChooseActivity.scaleDown(BitmapFactory.decodeFile(path), 1000, true);
                selectedPicture.setImageBitmap(selectedImageBitmap);

            }
            else if((screenht>=720&&screenht<=1280)&&(screenwdt>=720))//HD ready and below(average res)
            {
                Bitmap selectedImageBitmap = ChooseActivity.scaleDown(BitmapFactory.decodeFile(path), 700, true);
                selectedPicture.setImageBitmap(selectedImageBitmap);
            }
            else//low res
            {  Bitmap selectedImageBitmap = ChooseActivity.scaleDown(BitmapFactory.decodeFile(path), 500, true);
                selectedPicture.setImageBitmap(selectedImageBitmap);
            }
        }


        else if(flag1==1)
        {
            resId=i.getIntExtra("res-id",0);
            ht1= i.getIntExtra("height",550);
            wdt1=i.getIntExtra("width", 500);
            //Toast.makeText(this,wdt1+"  "+ht1,Toast.LENGTH_LONG).show();
           // Toast.makeText(this,screenwdt+" "+screenht,Toast.LENGTH_LONG).show();
            if(screenht>=1920&&screenwdt>=1080) {//full HD and above
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), resId);
                Bitmap scaled = ChooseActivity.scaleDown(bmp, 1000, true);
                imageUri = Uri.parse("android.resource://yorozuyastudios.pro.com.meme_inator/" + resId);
                // Bitmap scaled = BitmapResizer.ShrinkBitmap(imageUri.toString(), wdt1, ht1);
                //selectedPicture.setImageURI(imageUri);
                selectedPicture.setImageBitmap(scaled);
            }
            else if((screenht>=720&&screenht<=1280)&&(screenwdt>=720))//HD ready and below(average res)
            {
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), resId);
                Bitmap scaled = ChooseActivity.scaleDown(bmp, 700, true);
                imageUri = Uri.parse("android.resource://yorozuyastudios.pro.com.meme_inator/" + resId);
                // Bitmap scaled = BitmapResizer.ShrinkBitmap(imageUri.toString(), wdt1, ht1);
                //selectedPicture.setImageURI(imageUri);
                selectedPicture.setImageBitmap(scaled);
            }
            else//low res
            {
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), resId);
                Bitmap scaled = ChooseActivity.scaleDown(bmp, 500, true);
                imageUri = Uri.parse("android.resource://yorozuyastudios.pro.com.meme_inator/" + resId);
                // Bitmap scaled = BitmapResizer.ShrinkBitmap(imageUri.toString(), wdt1, ht1);
                //selectedPicture.setImageURI(imageUri);
                selectedPicture.setImageBitmap(scaled);
            }
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
        String midText = midTextEditText.getText().toString();

        if (!originalImage) {
            if (flag1 == 0) {
                if(screenht>=1920&&screenwdt>=1080) {//full HD and above
                    Bitmap selectedImageBitmap = ChooseActivity.scaleDown(BitmapFactory.decodeFile(path), 1000, true);
                    selectedPicture.setImageBitmap(selectedImageBitmap);

                }
                else if((screenht>=720&&screenht<=1280)&&(screenwdt>=720))//HD ready and below(average res)
                {
                    Bitmap selectedImageBitmap = ChooseActivity.scaleDown(BitmapFactory.decodeFile(path), 700, true);
                    selectedPicture.setImageBitmap(selectedImageBitmap);
                }
                else//low res
                {  Bitmap selectedImageBitmap = ChooseActivity.scaleDown(BitmapFactory.decodeFile(path), 500, true);
                    selectedPicture.setImageBitmap(selectedImageBitmap);
                }
            }
             else {
                if(screenht>=1920&&screenwdt>=1080) {//full HD and above
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), resId);
                    Bitmap scaled = ChooseActivity.scaleDown(bmp, 1000, true);
                    imageUri = Uri.parse("android.resource://yorozuyastudios.pro.com.meme_inator/" + resId);
                    // Bitmap scaled = BitmapResizer.ShrinkBitmap(imageUri.toString(), wdt1, ht1);
                    //selectedPicture.setImageURI(imageUri);
                    selectedPicture.setImageBitmap(scaled);
                }
                else if((screenht>=720&&screenht<=1280)&&(screenwdt>=720))//HD ready and below(average res)
                {
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), resId);
                    Bitmap scaled = ChooseActivity.scaleDown(bmp, 700, true);
                    imageUri = Uri.parse("android.resource://yorozuyastudios.pro.com.meme_inator/" + resId);
                    // Bitmap scaled = BitmapResizer.ShrinkBitmap(imageUri.toString(), wdt1, ht1);
                    //selectedPicture.setImageURI(imageUri);
                    selectedPicture.setImageBitmap(scaled);
                }
                else//low res
                {
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), resId);
                    Bitmap scaled = ChooseActivity.scaleDown(bmp, 500, true);
                    imageUri = Uri.parse("android.resource://yorozuyastudios.pro.com.meme_inator/" + resId);
                    // Bitmap scaled = BitmapResizer.ShrinkBitmap(imageUri.toString(), wdt1, ht1);
                    //selectedPicture.setImageURI(imageUri);
                    selectedPicture.setImageBitmap(scaled);

                }

            }


        }

        // get bitmap from imageView and copy to make mutable
        BitmapDrawable imageDrawable = (BitmapDrawable) selectedPicture.getDrawable();
        if (imageDrawable != null) {
            viewBitmap = imageDrawable.getBitmap();
            viewBitmap = viewBitmap.copy(viewBitmap.getConfig(), true);

            // add the text you want to your bitmap
            addTextToBitMap(viewBitmap, topText, bottomText,midText);

            // set your imageview to show your newly edited bitmap to
            selectedPicture.setImageBitmap(viewBitmap);
            originalImage = false;
        }
    }

    private void addTextToBitMap(Bitmap viewBitmap, String topText, String bottomText,String midText) {

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
        textPaint.setTypeface(Typeface.createFromAsset(getAssets(), "obelixpro.ttf"));
        //textPaint.setTypeface(Typeface.SANS_SERIF);
        textPaint.setTextAlign(Paint.Align.CENTER);

        Paint textPaintOutline = new Paint();
        textPaintOutline.setAntiAlias(true);
        textPaintOutline.setTextSize(textSize);
        textPaintOutline.setColor(Color.BLACK);
        textPaintOutline.setTypeface(tf);
        textPaintOutline.setStyle(Paint.Style.STROKE);
        textPaintOutline.setTextAlign(Paint.Align.CENTER);
        textPaintOutline.setStrokeWidth(8);

        float xPos = bitmapWidth / 2;
        float yPos = bitmapHeight / 7;

        pictureCanvas.drawText(topText, xPos, yPos, textPaintOutline);
        pictureCanvas.drawText(topText, xPos, yPos, textPaint);
        if(midText!=null)
        {
            pictureCanvas.drawText(midText, xPos, yPos+80, textPaintOutline);
            pictureCanvas.drawText(midText, xPos, yPos+80, textPaint);
        }

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
    @Override
    public void onBackPressed() {
        if(flag1==0) {
            Intent i = new Intent(this, ChooseActivity.class);
            startActivity(i);
        }
        else if (flag1==1)
        {
            Intent i = new Intent(this, MemeListActivity.class);
            startActivity(i);
        }


    }
}
