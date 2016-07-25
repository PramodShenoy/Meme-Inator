package yorozuyastudios.pro.com.meme_inator;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class ChooseActivity extends Activity {

    private Boolean pictureTaken = false;
    private static final String BITMAP_WIDTH = "BITMAP_WIDTH";
    private static final String BITMAP_HEIGHT = "BITMAP_HEIGHT";

    private int SELECT_IMAGE = 1;
    public int flag=0;
    public static ImageView galleryImageView;
    private ImageView listImageView;
    private String picturePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        galleryImageView = (ImageView) findViewById(R.id.picture_imageview);
        galleryImageView.setOnClickListener(openCG_OnClickListener);

        listImageView = (ImageView) findViewById(R.id.list_imageview);
        listImageView.setOnClickListener(openCG_OnClickListener);


    }


    final View.OnClickListener openCG_OnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.picture_imageview:
                    //Inform the user the button1 has been clicked
                    startGallery();


                    break;
                case R.id.list_imageview:
                    //Inform the user the button2 has been clicked
                    flag=1;
                    Intent i=new Intent(ChooseActivity.this,MemeListActivity.class);
                    startActivity(i);
                    break;

                default:
                    break;
            }
        }
    };




    private void moveToNextScreen() {

        if (pictureTaken) {
            Intent nextScreenIntent = new Intent(this, EnterTextActivity.class);
             nextScreenIntent.putExtra("path",picturePath);
            nextScreenIntent.putExtra(BITMAP_WIDTH, BitmapFactory.decodeFile(picturePath).getWidth());
            nextScreenIntent.putExtra(BITMAP_HEIGHT, BitmapFactory.decodeFile(picturePath).getHeight());
            nextScreenIntent.putExtra("flag",flag);

            startActivity(nextScreenIntent);
        } else {
            Toast.makeText(this, "PLEASE SELECT AN OPTION", Toast.LENGTH_SHORT).show();
        }
    }
   /* private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }*/
    @Override
           public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }

    public void startGallery() {

        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, SELECT_IMAGE);
    }



    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();


            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            //TextView text = (TextView) findViewById(R.id.stage_one_instructions_textview);
            //text.setText(picturePath);
            //galleryImageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            pictureTaken=true;
            moveToNextScreen();
        }


    }
}
