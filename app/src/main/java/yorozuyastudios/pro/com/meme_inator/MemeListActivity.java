package yorozuyastudios.pro.com.meme_inator;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MemeListActivity extends AppCompatActivity {
     MemeAdapter memeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_memelist);

        // Create an ArrayList of Meme objects
        ArrayList<Meme> memeList = new ArrayList<Meme>();
        memeList.add(new Meme("aaaand it's gone",R.drawable.aaaaand_its_gone));
        memeList.add(new Meme("am i the only who",R.drawable.am_i_the));
        memeList.add(new Meme("angry indian father",R.drawable.amrish_puri));
        memeList.add(new Meme("all things",R.drawable.all_things));
        memeList.add(new Meme("ancient aliens",R.drawable.ancient_aliens));
        memeList.add(new Meme("angry baby",R.drawable.angry_baby));
        memeList.add(new Meme("angry doge",R.drawable.angry_doge));
        memeList.add(new Meme("arnab thug life", R.drawable.arnab_thug_life));
        memeList.add(new Meme("awkward seal",R.drawable.conf_seal));
        memeList.add(new Meme("condescending husky",R.drawable.angry_husky));
        memeList.add(new Meme("baby godfather",R.drawable.baby_godfather));
        memeList.add(new Meme("bad luck brian",R.drawable.bad_luck_brian));
        memeList.add(new Meme("batman slaps robin",R.drawable.batman_robin));
        memeList.add(new Meme("be like bill",R.drawable.be_like_bill));
        memeList.add(new Meme("bear grylls",R.drawable.bear_grylls));
        memeList.add(new Meme("brace yourselves",R.drawable.brace_yourselves));
        memeList.add(new Meme("buddy jesus",R.drawable.buddy_christ));
        memeList.add(new Meme("business cat",R.drawable.business_cat));
        memeList.add(new Meme("butthurt dweller",R.drawable.butthurt_dweller));
        memeList.add(new Meme("calm lemur",R.drawable.calm_lemur));
        memeList.add(new Meme("cereal guy",R.drawable.cereal_guy));
        memeList.add(new Meme("chemistry cat",R.drawable.chem_cat));
        memeList.add(new Meme("chuck norris",R.drawable.chuck_norris));
        memeList.add(new Meme("CID ",R.drawable.cid));
        memeList.add(new Meme("confused baby",R.drawable.conf_baby));
        memeList.add(new Meme("confession bear",R.drawable.confession_bear));
        memeList.add(new Meme("correction guy",R.drawable.correction_guy));
        memeList.add(new Meme("dekh bhai",R.drawable.dekh_bhai));
        memeList.add(new Meme("disaster girl",R.drawable.disaster_girl));
        memeList.add(new Meme("depressed dog",R.drawable.depressed));
        memeList.add(new Meme("doesn't matter rock",R.drawable.doesntmatter));
        memeList.add(new Meme("doge",R.drawable.doge));
        memeList.add(new Meme("doge alternate",R.drawable.doge2));
        memeList.add(new Meme("drunk baby",R.drawable.drunk_baby2));
        memeList.add(new Meme("drunk obama",R.drawable.drunk_obama));
        memeList.add(new Meme("dumbstruck koala",R.drawable.dumbstruck_koala));
        memeList.add(new Meme("dwight schrute",R.drawable.dwight_schrute));
        memeList.add(new Meme("engineering professor ",R.drawable.engg_prof));
        memeList.add(new Meme("everywhere toy story ",R.drawable.everywhere));
        memeList.add(new Meme("evil raccoon plotting",R.drawable.evil_racc));
        memeList.add(new Meme("evil toddler",R.drawable.evil_toddler));
        memeList.add(new Meme("facepalm picard ",R.drawable.facepalm));
        memeList.add(new Meme("finally over",R.drawable.finally_over));
        memeList.add(new Meme("first day on the internet kid",R.drawable.first_day_on_the_internet_kid));
        memeList.add(new Meme("first world problems",R.drawable.first_world_problems));
        memeList.add(new Meme("futurama fry",R.drawable.futurama_fry));
        memeList.add(new Meme("gay seal",R.drawable.gay_seal));
        memeList.add(new Meme("gollum",R.drawable.gollum));
        memeList.add(new Meme("gordon ramsay",R.drawable.gordon_ramsay));
        memeList.add(new Meme("grumpy cat",R.drawable.grumpy_cat));
        memeList.add(new Meme("grumpy cat 2",R.drawable.grumpy_cat1));
        memeList.add(new Meme("heavy breathing cat",R.drawable.heavy_breathing_cat));
        memeList.add(new Meme("high guy",R.drawable.high_guy));
        memeList.add(new Meme("happy cat",R.drawable.cat_smile));
        memeList.add(new Meme("i'll have you know",R.drawable.ill_have_you_know));
        memeList.add(new Meme("impossibru",R.drawable.impossibru));
        memeList.add(new Meme("if you know what i mean",R.drawable.iykwim));
        memeList.add(new Meme("jackie chan",R.drawable.jackie));
        memeList.add(new Meme("kejriwal happy", R.drawable.kejri));
        memeList.add(new Meme("kejriwal angry", R.drawable.kejru_angry));
        memeList.add(new Meme("kejriwal2", R.drawable.kejri2));
        memeList.add(new Meme("kermit frog(none of my business)",R.drawable.none_business));
        memeList.add(new Meme("kim jong un sad",R.drawable.kim_jong_un_sad));
        memeList.add(new Meme("the joker",R.drawable.joker));
        memeList.add(new Meme("lame pun eel",R.drawable.lame_pun_eel));
        memeList.add(new Meme("laughing obama",R.drawable.laughing_obama));
        memeList.add(new Meme("lazy college senior",R.drawable.lazy_college_senior));
        memeList.add(new Meme("great gatsby leo",R.drawable.leo_cheers));
        memeList.add(new Meme("liam neeson",R.drawable.liam_neeson));
        memeList.add(new Meme("lion king",R.drawable.lk));
        memeList.add(new Meme("look at all these..",R.drawable.look_at_all));
        memeList.add(new Meme("matrix morpheus",R.drawable.matrix_morpheus));
        memeList.add(new Meme("mj popcorn",R.drawable.mj_popcorn));
        memeList.add(new Meme("mr.bean",R.drawable.mr_bean));
        memeList.add(new Meme("naacho bc",R.drawable.naacho_bc));
        memeList.add(new Meme("NaMo checkout", R.drawable.namo_china));
        memeList.add(new Meme("Namo ", R.drawable.namo_s));
        memeList.add(new Meme("nana angry", R.drawable.nana_angry));
        memeList.add(new Meme("naughty baby",R.drawable.baby_naughty));
        memeList.add(new Meme("nobody cares",R.drawable.nobody_cares));
        memeList.add(new Meme("one does not simply..",R.drawable.boromir1));
        memeList.add(new Meme("osama bomb people",R.drawable.boromir1));
        memeList.add(new Meme("not bad obama",R.drawable.not_bad_obama));
        memeList.add(new Meme("pissed obama",R.drawable.obama_pissed));
        memeList.add(new Meme("weird expression obama",R.drawable.obama2));
        memeList.add(new Meme("patrick star",R.drawable.patrick_star));
        memeList.add(new Meme("rare pepe",R.drawable.pepe));
        memeList.add(new Meme("bad luck sad pepe",R.drawable.pepe_sad));
        memeList.add(new Meme("philosoraptor",R.drawable.philosoraptor));
        memeList.add(new Meme("plotting raccoon and human",R.drawable.plotting_racc));
        memeList.add(new Meme("professor oak",R.drawable.prof_oak));
        memeList.add(new Meme("good guy putin",R.drawable.putin_good));
        memeList.add(new Meme("rajanikanth mind it!",R.drawable.rajani_mindit));
        memeList.add(new Meme("rich men laughing",R.drawable.rich_men_lol));
        memeList.add(new Meme("sad polar bear",R.drawable.sad_polar));
        memeList.add(new Meme("sarcastic nicholas cage",R.drawable.sarcastic_nicholas_cage));
        memeList.add(new Meme("sansakari alok nath",R.drawable.aloknath1));
        memeList.add(new Meme("scared baby", R.drawable.scared_baby));
        memeList.add(new Meme("scumbag steve", R.drawable.scared_baby));
        memeList.add(new Meme("shut up and take my money", R.drawable.shut_up_money));
        memeList.add(new Meme("severus snape", R.drawable.snape));
        memeList.add(new Meme("so much win picard", R.drawable.so_much_win));
        memeList.add(new Meme("spiderman hospital", R.drawable.spiderman_hospital));
        memeList.add(new Meme("spiderman table", R.drawable.spiderman_jerk));
        memeList.add(new Meme("spiderman peter parker", R.drawable.spiderman_peter_parker));
        memeList.add(new Meme("spidey approves", R.drawable.spidey_approves));
        memeList.add(new Meme("spidey butt", R.drawable.spidey_butt));
        memeList.add(new Meme("spidey high", R.drawable.spidey_high));
        memeList.add(new Meme("spidey hot", R.drawable.spidey_hot));
        memeList.add(new Meme("spidey whisper", R.drawable.spidey_whisper));
        memeList.add(new Meme("spongebob and obama", R.drawable.sponge_obaa));
        memeList.add(new Meme("spongebob rainbow", R.drawable.spongebob_nocare));
        memeList.add(new Meme("squidward hard work", R.drawable.squid_hard_work));
        memeList.add(new Meme("squidward realisation", R.drawable.squidward));
        memeList.add(new Meme("sudden realisation", R.drawable.sudden_realisation));
        memeList.add(new Meme("surprised vegeta", R.drawable.surprized_vegeta));
        memeList.add(new Meme("surprised nigga",R.drawable.nigga));
        memeList.add(new Meme("swag cat",R.drawable.cool_cat));
        memeList.add(new Meme("sweet brown", R.drawable.sweet_brown));
        memeList.add(new Meme("that escalaed quickly", R.drawable.that_escalate));
        memeList.add(new Meme("that would be great", R.drawable.that_would_be_great));
        memeList.add(new Meme("the most interesting man", R.drawable.the_most_interesting_man));
        memeList.add(new Meme("third world sceptical kid", R.drawable.third_world_skeptical_kid));
        memeList.add(new Meme("third world dancing kids", R.drawable.third_world_success_kid));
        memeList.add(new Meme("to do list", R.drawable.to_do_list));
        memeList.add(new Meme("tony stark", R.drawable.tony));
        memeList.add(new Meme("too damn high", R.drawable.too_high));
        memeList.add(new Meme("troll cry face", R.drawable.troll_cry));
        memeList.add(new Meme("troll face", R.drawable.troll_face));
        memeList.add(new Meme("troll forever alone", R.drawable.troll_foreverlone));
        memeList.add(new Meme("like a boss", R.drawable.troll_lab));
        memeList.add(new Meme("troll angry no", R.drawable.troll_no));
        memeList.add(new Meme("troll shocked", R.drawable.troll_shock));
        memeList.add(new Meme("trump", R.drawable.trump));
        memeList.add(new Meme("proud trump",R.drawable.proud_trump));
        memeList.add(new Meme("unhelpful high school teacher",R.drawable.unhelpful_high_school_teacher));
        memeList.add(new Meme("Victory baby",R.drawable.celeb_baby));
        memeList.add(new Meme("waiting..",R.drawable.wait));
        memeList.add(new Meme("what?",R.drawable.what2));
        memeList.add(new Meme("troll why?",R.drawable.why));
        memeList.add(new Meme("Balotelli why always me?",R.drawable.why_always_me));
        memeList.add(new Meme("Willy wonka",R.drawable.willy_wonka));
        memeList.add(new Meme("wtf jackie",R.drawable.wtf_jackie));
        memeList.add(new Meme("yo dawg",R.drawable.yo_dawg));
        memeList.add(new Meme("yo goose",R.drawable.yo_goose));
        memeList.add(new Meme("y you no..",R.drawable.yuno));

        // Create an {@link AndroidFlavorAdapter}, whose data source is a list of
        // {@link AndroidFlavor}s. The adapter knows how to create list item views for each item
        // in the list.
        memeAdapter = new MemeAdapter(this, memeList);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.listview_meme);
        listView.setAdapter(memeAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                // When clicked, show a toast with the TextView text
                TextView text = (TextView) findViewById(R.id.meme_name);
                Meme obj = memeAdapter.getItem(position);
                String str = obj.getMemeName();
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                int Id = obj.getImgResId();
                Intent intent = new Intent(MemeListActivity.this, EnterTextActivity.class);
                intent.putExtra("flag",1);
                intent.putExtra("res-id", Id);
                startActivity(intent);
            }

        });
    }

}
