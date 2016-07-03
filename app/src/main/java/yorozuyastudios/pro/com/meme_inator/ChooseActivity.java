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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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


    private Uri selectedPhotoPath;

    private ImageView galleryImageView;
    private TextView lookingGoodTextView;
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

        lookingGoodTextView = (TextView) findViewById(R.id.looking_good_textview);

        nextScreenButton = (Button) findViewById(R.id.enter_text_button);
        nextScreenButton.setOnClickListener(openCG_OnClickListener);
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


                    break;

                case R.id.enter_text_button:
                    moveToNextScreen();
                    break;

                default:
                    break;
            }
        }
    };


   /* private void takePictureWithCamera() {

        // create intent to capture image from camera
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File photoFile = createImageFile();
        selectedPhotoPath = Uri.parse(photoFile.getAbsolutePath());

        captureIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
        startActivityForResult(captureIntent, TAKE_PHOTO_REQUEST_CODE);
        onActivityResult(TAKE_PHOTO_REQUEST_CODE, 1, captureIntent, takePictureImageView);
    }*/


   /* private void setImageViewWithImage() {
        Bitmap pictureBitmap = BitmapResizer.ShrinkBitmap(selectedPhotoPath.toString(),
                galleryImageView.getWidth(),
                galleryImageView.getHeight());
        galleryImageView.setImageBitmap(pictureBitmap);
        lookingGoodTextView.setVisibility(View.VISIBLE);
        pictureTaken = true;
    }*/

    //  private File createImageFile() {

    // Create an image file name
       /*String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES + APP_PICTURE_DIRECTORY);
        storageDir.mkdirs();

        File imageFile = null;

        try {
            imageFile = File.createTempFile(
                    imageFileName,  /* prefix */
    //FILE_SUFFIX_JPG,         /* suffix */
    // storageDir      /* directory */
    //           );
    //       } catch (IOException e) {
    //          e.printStackTrace();
    //    }

    //    return imageFile;
    //  }

    private Uri getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return Uri.parse(result);
    }

    private void moveToNextScreen() {

        if (pictureTaken) {
            Intent nextScreenIntent = new Intent(this, EnterTextActivity.class);
             nextScreenIntent.putExtra("path",picturePath);
            nextScreenIntent.putExtra(BITMAP_WIDTH, galleryImageView.getWidth());
            nextScreenIntent.putExtra(BITMAP_HEIGHT, galleryImageView.getHeight());

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

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        column_index = cursor
                .getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        imagePath = cursor.getString(column_index);

        return cursor.getString(column_index);
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
        listImageView.setVisibility(View.GONE);
        galleryImageView.requestLayout();
        galleryImageView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        galleryImageView.getLayoutParams().width= ViewGroup.LayoutParams.MATCH_PARENT;
        //galleryImageView.getLayoutParams().height=800;
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
            TextView text = (TextView) findViewById(R.id.stage_one_instructions_textview);
            text.setText(picturePath);
            galleryImageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            pictureTaken=true;
            lookingGoodTextView.setVisibility(View.VISIBLE);
        }


    }
}
