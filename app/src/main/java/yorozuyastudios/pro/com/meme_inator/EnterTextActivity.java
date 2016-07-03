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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EnterTextActivity extends Activity implements View.OnClickListener {

    private static final String APP_PICTURE_DIRECTORY = "/Memeify";
    private static final String FILE_SUFFIX_JPG = ".jpg";
    private static final String HELVETICA_FONT = "Helvetica";
    private static final String IMAGE_URI_KEY = "IMAGE_URI";
    private static final String BITMAP_WIDTH = "BITMAP_WIDTH";
    private static final String BITMAP_HEIGHT = "BITMAP_HEIGHT";


    private Bitmap viewBitmap;
    private Uri pictureUri;
    private boolean originalImage = false;
    private String path;
    private ImageView selectedPicture;
    private Button writeTextToImageButton;
    private Button shareButton;
    private Button saveImageButton;
    private EditText topTextEditText;
    private EditText bottomTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertext);

        selectedPicture = (ImageView) findViewById(R.id.selected_picture_imageview);

        writeTextToImageButton = (Button) findViewById(R.id.write_text_to_image_button);
        writeTextToImageButton.setOnClickListener(this);
        shareButton=(Button) findViewById(R.id.share_btn);
        shareButton.setVisibility(View.GONE);

        saveImageButton = (Button) findViewById(R.id.save_image_button);
        saveImageButton.setOnClickListener(this);

        topTextEditText = (EditText) findViewById(R.id.top_text_edittext);
        bottomTextEditText = (EditText) findViewById(R.id.bottom_text_edittext);
        topTextEditText.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        bottomTextEditText.setFilters(new InputFilter[] {new InputFilter.AllCaps()});



        originalImage = true;
        Intent i=getIntent();
        path=i.getStringExtra("path");
        //pictureUri = getIntent().getParcelableExtra(IMAGE_URI_KEY);

       int bitmapWidth = getIntent().getIntExtra(BITMAP_WIDTH, 100);
       int bitmapHeight = getIntent().getIntExtra(BITMAP_HEIGHT, 100);

        //Bitmap selectedImageBitmap = BitmapResizer.ShrinkBitmap(path.toString(), bitmapWidth, bitmapHeight);
       // selectedPicture.setImageBitmap(BitmapFactory.decodeFile(path));
        Bitmap selectedImageBitmap=ChooseActivity.scaleDown(BitmapFactory.decodeFile(path),700,true);
        selectedPicture.setImageBitmap(selectedImageBitmap);
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

        if (!originalImage) {
            Bitmap bm = BitmapResizer.ShrinkBitmap(pictureUri.toString(), selectedPicture.getWidth(), selectedPicture.getHeight());
            selectedPicture.setImageBitmap(bm);
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
        writeTextToImageButton.setVisibility(View.GONE);
        shareButton.setVisibility(View.VISIBLE);
    }
    public void share(View v) throws FileNotFoundException {

        String whatsAppMessage = "ENTER YOUR MESSAGE";

        //You can read the image from external drive too
        Uri uri = Uri.parse(path);



        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, whatsAppMessage);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_STREAM,uri);
        intent.setType("image/jpeg");
        intent.setPackage("com.whatsapp");
        startActivity(intent);

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
        textPaintOutline.setStyle(Paint.Style.STROKE);
        textPaintOutline.setTextAlign(Paint.Align.CENTER);
        textPaintOutline.setStrokeWidth(8);

        float xPos = bitmapWidth / 2;
        float yPos = bitmapHeight / 7;

        pictureCanvas.drawText(topText, xPos, yPos, textPaintOutline);
        pictureCanvas.drawText(topText, xPos, yPos, textPaint);

        yPos = bitmapHeight - bitmapHeight / 14;

        pictureCanvas.drawText(bottomText, xPos, yPos, textPaintOutline);
        pictureCanvas.drawText(bottomText, xPos, yPos, textPaint);
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
                Toast.makeText(this, "SAVE IMAGE FAILED", Toast.LENGTH_SHORT).show();
            }
            // Create intent to request newly created file to be scanned, pass picture uri and broadcast intent
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            mediaScanIntent.setData(Uri.fromFile(imageFile));
            sendBroadcast(mediaScanIntent);


            Toast.makeText(this, "SUCCESSFULLY SAVED THE MEME",Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "WHY YOU NO GENERATE MEME?!!", Toast.LENGTH_SHORT).show();
        }
    }
}

