package yorozuyastudios.pro.com.meme_inator;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class ChooseActivity extends Activity {

    private static final int TAKE_PHOTO_REQUEST_CODE = 1;
    private static final String APP_PICTURE_DIRECTORY = "/Memeify";
    private static final String MIME_TYPE_IMAGE = "image/";
    private static final String FILE_SUFFIX_JPG = ".jpg";
    private Boolean pictureTaken = false;
    private static final String IMAGE_URI_KEY = "IMAGE_URI";
    private static final String BITMAP_WIDTH = "BITMAP_WIDTH";
    private static final String BITMAP_HEIGHT = "BITMAP_HEIGHT";

    int column_index;
    Intent intent = null;
    // Declare our Views, so we can access them later
    String logo, imagePath, Logo;
    Cursor cursor;
    String selectedImagePath;
    //ADDED
    String filemanagerstring;

    private int SELECT_IMAGE = 1;
    public int flag=0;

    private Uri selectedPhotoPath;

    private ImageView galleryImageView;
    private Button nextScreenButton;
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
            nextScreenIntent.putExtra(BITMAP_WIDTH, galleryImageView.getWidth());
            nextScreenIntent.putExtra(BITMAP_HEIGHT, galleryImageView.getHeight());
            nextScreenIntent.putExtra("flag",flag);

            startActivity(nextScreenIntent);
        } else {
            Toast.makeText(this, "PLEASE SELECT AN OPTION", Toast.LENGTH_SHORT).show();
        }
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
