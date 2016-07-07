package yorozuyastudios.pro.com.meme_inator;

//Class for the custom array adapter with a textview and an image view
public class Meme {

    private String memeName;

    // Drawable resource ID
    private int ImgResId;
//public constructor
    public Meme(String name, int imageResourceId)
    {
        memeName=name;
        ImgResId=imageResourceId;
    }
//getters
    public String getMemeName(){return memeName;}


    public int getImgResId(){ return  ImgResId;}

}
