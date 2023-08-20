package com.koltech.rozmiarbutw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


public class MainActivity extends AppCompatActivity {

    public String jednostka = "EUROPA";


    Animation animbuton;

    Button PJednostka;

    Boolean Autozamykanieklawiatury;
    Boolean ObslugaPosrenichRozmiarow;

    private SharedPreferences preferences;






    EditText Wejscie;

    Button Menszczyzna;
    Button Kobieta;
    Button Dziecko;


    Boolean Menskie = true;
    Boolean Damskie = false;
    Boolean Dzieciece = false;

    TextView TexEuropa;
    TextView TexUs;
    TextView TexChiny;
    TextView TexUk;
    TextView TexRosja;
    TextView TexCentymetry;
    TextView TexMondopoint;
    TextView TexJaponia;
    TextView TexKorea;


    Switch rozmiaryposrednie;
    Switch klawiatura;

    // 33.0f,34.0f,35.0f,36.0f,37.0f,38.0f
    // 0.0f,0.0f,0.0f,0.0f,0.0f,0.0f
    ///////////////////////////////////////////////////////////////////////////Menskie
    float[] menskieMondopoint =    {};
    float[] menskiecm =            {25.0f,25.4f,25.7f,26.0f,26.4f,26.7f,27.0f,27.4f,27.7f,28.0f,28.4f,28.7f,29.0f,29.4f,29.7f,30.0f,30.4f,30.7f,31.0f,31.4f,31.7f,32.0f,32.4f};
    float[] menskieEu =            {39.0f,39.5f,40.0f,40.5f,41.0f,41.5f,42.0f,42.5f,43.0f,43.5f,44.0f,44.5f,45.0f,45.5f,46.0f,46.5f,47.0f,47.5f,48.0f,48.5f,49.0f,49.5f,50.0f};
    float[] menskieUK =            { 5.5f, 6.0f, 6.5f, 7.0f, 7.5f, 7.5f, 8.0f, 8.5f, 9.0f, 9.5f, 9.5f,10.0f,10.5f,11.0f,11.0f,11.5f,12.0f,12.5f,13.0f,13.0f,14.0f,14.5f,15.0f};
    float[] menskieUS =            { 6.0f, 6.5f, 7.0f, 7.5f, 8.0f, 8.0f, 8.5f, 9.0f, 9.5f, 9.5f,10.0f,10.5f,11.0f,11.5f,11.5f,12.0f,12.5f,13.0f,13.5f,14.0f,14.5f,15.0f,15.5f};
    float[] menskieJaponia =       {24.5f,25.0f,25.0f,25.5f,26.0f,26.0f,26.5f,27.0f,27.5f,28.0f,28.5f,29.0f,29.5f,29.5f,23.0f};
    float[] menskieRosja =         { 0.0f, 0.0f,39.0f,39.5f,40.0f,40.0f,40.5f,41.0f,41.5f,41.5f,42.0f,42.5f,43.0f,43.5f,43.5f,44.5f,44.5f,45.0f,45.0f,45.0f,    0,    0,   0};
    float[] menskieChiny =         {40.0f,41.0f,41.0f,42.0f,43.0f,43.5f,44.0f,45.0f,46.0f,47.0f,47.5f,48.0f,48.0f};
                            //        1     2     3    4      5     6     7     8     9     10   11    12     13    14    15   16    17    18    19    20
    float[] menskieKorea =       {245.0f,250.0f,250.0f,255.0f,260.0f,260.0f,265.0f,270.0f,275.0f,280.0f,285.0f,290.0f,295.0f,295.0f,300.0f,305.0f,305.0f,310.0f,315.0f,315.0f};
                                   ///1      2     3       4     5     6      7       8     9     10    11        12    13     14    15     16      17    18     19



    ///////////////////////////////////////////////////////////////////////////Dziecience
    float[] DzieciMondopoint ={};
    float[] Dziecicm =        { 9.7f,10.4f,11.0f,11.7f,12.4f,13.0f,13.7f,13.7f,14.4f,15.0f,15.7f,16.0f,16.2f,16.4f,17.0f,17.7f,18.4f,19.0f,19.7f};
    float[] DzieciEu =        {16.0f,17.0f,18.0f,19.0f,20.0f,21.0f,22.0f,22.0f,23.0f,24.0f,25.0f,25.5f,25.8f,26.0f,27.0f,28.0f,29.0f,30.0f,31.0f};
    float[] DzieciUK =        { 0.5f, 1.5f, 2.0f, 3.0f, 3.5f, 4.5f,5.5f , 5.5f, 6.0f, 7.0f, 7.5f, 8.0f, 8.5f, 8.5f, 9.0f,10.0f,11.0f,11.5f,12.5f};
    float[] DzieciUS =        { 1.0f, 2.0f, 2.5f, 3.5f, 4.5f, 5.0f,5.0f , 6.0f, 6.5f, 7.5f, 8.0f, 8.5f, 9.0f, 9.5f,10.5f,11.0f,12.0f,12.5f,13.0f};
    float[] DzieciChiny = {
            21.0f, 21.5f, 22.0f, 22.5f, 23.0f, 23.5f, 24.0f, 24.5f, 25.0f, 25.5f,
            26.0f, 26.5f, 27.0f, 27.5f, 28.0f, 28.5f, 29.0f, 29.5f, 30.0f, 30.5f,
            31.0f, 31.5f, 32.0f, 32.5f, 33.0f, 33.5f, 34.0f, 34.5f, 35.0f, 35.5f,
            36.0f, 36.5f, 37.0f, 37.5f, 38.0f, 38.5f, 39.0f, 39.5f, 40.0f, 40.5f
    };
    float[] DzieciRosja =     {16.0f,17.0f,18.0f,18.5f,19.0f,20.0f,21.0f,22.0f,22.5f,23.0f,24.0f,25.0f,26.0f,26.5f,27.0f,28.0f,29.0f,30.0f,31.0f};
    float[] DzieciJaponia = {
            14.0f, 14.5f, 15.0f, 15.5f, 16.0f, 16.5f, 17.0f, 17.5f, 18.0f, 18.5f,
            19.0f, 19.5f, 20.0f, 20.5f, 21.0f, 21.5f, 22.0f, 22.5f, 23.0f, 23.5f,
            24.0f, 24.5f, 25.0f, 25.5f, 26.0f, 26.5f, 27.0f, 27.5f, 28.0f, 28.5f,
            29.0f, 29.5f, 30.0f, 30.5f, 31.0f, 31.5f, 32.0f, 32.5f, 33.0f, 33.5f
    };

    ///////////////////////////////////////////////////////////////////////////DziecienceStarsze
    float[] DzieciStarszeMondopoint = {
            20.0f, 20.5f, 21.0f, 21.5f, 22.0f, 22.5f, 23.0f, 23.5f, 24.0f, 24.5f
    };
    float[] DzieciStarszecm =        { 20.4f,21.0f,21.7f,22.4f,23.0f,23.7f,24.4f,25.0f,25.7f,26.4f};
    float[] DzieciStarszeEu =        { 32.0f,33.0f,34.0f,35.0f,36.0f,37.0f,38.0f,39.0f,40.0f,41.0f};
    float[] DzieciStarszeUK =        { 13.0f, 1.0f, 2.0f, 2.5f, 3.5f, 4.0f, 5.0f, 6.0f, 6.5f, 7.0f};
    float[] DzieciStarszeUS =        {  1.5f, 2.0f, 3.0f, 3.5f, 4.5f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f};
    float[] DzieciStarszeChiny =     {};
    float[] DzieciStarszeRosja =     {32.0f,33.0f, 34.0f,   0,     0,    0,    0,    0,    0,    0};
    float[] DzieciStarszeJaponia =   {};


    //////////////////////////////////////////////////////////////////////Kobiety
    float[] KoMondopoint ={};
    float[] Kocm =        { 22.4f,22.7f,23.0f,23.4f,23.7f,24.0f,24.4f,24.7f,25.0f,25.4f,25.7f,26.0f,26.4f,26.7f,26.0f,27.4f,27.7f,28.0f,28.4f};
    float[] KoEu =        { 35.0f,35.5f,36.0f,36.5f,37.0f,37.5f,38.0f,38.5f,39.0f,39.5f,40.0f,40.5f,41.0f,41.5f,42.0f,42.5f,43.0f,43.5f,44.0f};
    float[] KoUK =        {  2.5f, 3.0f, 3.5f, 4.0f, 4.0f, 4.5f, 5.0f, 5.5f, 5.5f, 6.0f, 6.5f, 7.0f, 7.5f, 7.5f, 8.0f, 8.5f, 9.0f, 9.5f,10.0f};
    float[] KoUS =        {  4.0f, 4.5f, 5.0f, 5.5f, 5.5f, 6.0f, 6.5f, 7.0f, 7.5f, 7.5f, 8.0f, 8.5f, 9.0f, 9.5f, 9.5f,10.0f,10.5f,11.0f,12.0f};
    float[] KoChiny =     { 35.5f,36.0f,37.0f,38.0f,38.0f,39.0f,40.0f,40.0f,40.5f,40.5f,41.5f,42.0f,43.0f,43.5f,44.0f,44.5f,45.0f,45.0f,46.0f,47.0f,49.0f};
    float[] KoJaponia =   { 22.5f,23.0f,23.5f,24.0f,24.0f,24.5f,25.0f,25.0f,25.0f,25.0f,25.5f,26.0f,26.5f,26.5f,27.0f,27.0f,27.5f,27.5f,28.0f,28.5f,29.0f};
    float[] KoKorea=      {220.0f,230.0f,235.0f,240.0f,340.0f,245.0f,250.0f,250.0f,250.0f,255.0f,260.0f,265.0f,265.0f,270.0f};
    float[] KoRosja =     {  0.0f, 0.0f, 0.0f, 0.0f, 0.0f,35.0f,35.5f,36.0f,36.5f,36.5f,37.0f,37.5f,38.0f,38.5f,38.5f,39.5f,39.5f,40.0f,41.0f};

    float temp;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        preferences = getSharedPreferences("RozmiarButuwKOL", Context.MODE_PRIVATE);
        restoreData();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        setContentView(R.layout.activity_main);
        SetUP();





    }


    public void SetUP(){


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        Wejscie = (EditText) findViewById(R.id.wejscie);


        Menszczyzna =(Button) findViewById(R.id.BMenskie);
        Kobieta = (Button) findViewById(R.id.BDamskie);
        Dziecko= (Button) findViewById(R.id.BDziecience);


        PJednostka = (Button) findViewById(R.id.Bjednostka);
        TexEuropa= (TextView) findViewById(R.id.TexEuropa);
        TexUs = (TextView) findViewById(R.id.TexUs);
        TexChiny = (TextView) findViewById(R.id.TexChiny);
        TexUk = (TextView) findViewById(R.id.TexUk);
        TexRosja = (TextView) findViewById(R.id.TexRosja);
        TexCentymetry = (TextView) findViewById(R.id.TexCentymetry);
        TexMondopoint = (TextView) findViewById(R.id.TexMondopoint);
        TexJaponia = (TextView) findViewById(R.id.TexJaponia);
        TexKorea = (TextView) findViewById(R.id.TexKorea);


        animbuton = AnimationUtils.loadAnimation(this,R.anim.anim);



        PJednostka.setText(getString(R.string.europa));
        TexEuropa.setText("0");
        TexUs.setText("0");
        TexChiny.setText("0");
        TexUk.setText("0");
        TexRosja.setText("0");
        TexCentymetry.setText("0");
        TexMondopoint.setText("0");
        TexJaponia.setText("0");
        TexKorea.setText("0");










        if(ObslugaPosrenichRozmiarow==false){
            Wejscie.setInputType(InputType.TYPE_CLASS_NUMBER);
           // Powiadomienie("false");
        }
        if(ObslugaPosrenichRozmiarow==true){
            Wejscie.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
          //  Powiadomienie("true");
        }
    }



    public void Ustawienia(View v){
        setContentView(R.layout.ustawienia);
        try {
            rozmiaryposrednie = (Switch) findViewById(R.id.switch1);
            klawiatura = (Switch) findViewById(R.id.switch2);
        rozmiaryposrednie.setChecked(ObslugaPosrenichRozmiarow);
        klawiatura.setChecked(Autozamykanieklawiatury);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void UstawieniaOK(View v){

        ObslugaPosrenichRozmiarow = rozmiaryposrednie.isChecked();
        Autozamykanieklawiatury   = klawiatura.isChecked();
        setContentView(R.layout.activity_main);
        SetUP();
        saveData();

    }

    private void saveData() {
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        //preferencesEditor.putString(PREFERENCES_TEXT_FIELD, editTextData);

       // Switch rozmiaryposrednie = findViewById(R.id.switch1);
       // Switch klawiatura = findViewById(R.id.switch2);
      try {


          preferencesEditor.putBoolean("Autozamykanieklawiatury",Autozamykanieklawiatury);
          preferencesEditor.putBoolean("RozmiaryPosrednie",ObslugaPosrenichRozmiarow);
          preferencesEditor.apply();
        } catch (Exception e) {
          Powiadomienie("Błond zapisu Preferencji");
           e.printStackTrace();
      }
    }

    private void restoreData() {
       // String textFromPreferences = preferences.getString(PREFERENCES_TEXT_FIELD, "");
       // etToSave.setText(textFromPreferences);
        try {
           // Boolean Autozamykanieklawiatury = true;
           // Boolean ObslugaPosrenichRozmiarow = false;
            Autozamykanieklawiatury = preferences.getBoolean("Autozamykanieklawiatury",true);
            ObslugaPosrenichRozmiarow=preferences.getBoolean("RozmiaryPosrednie",false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public void UstawMenskie(View v){
        Menskie = true;
        Damskie = false;
        Dzieciece = false;


       Menszczyzna.setBackground(getResources().getDrawable(R.drawable.gren_button));
       Kobieta.setBackground(getResources().getDrawable(R.drawable.blue_buton));
       Dziecko.setBackground(getResources().getDrawable(R.drawable.blue_buton));
   }
   public void UstawDamskie(View v){
        Menskie = false;
        Damskie = true;
        Dzieciece = false;


        Menszczyzna.setBackground(getResources().getDrawable(R.drawable.blue_buton));
        Kobieta.setBackground(getResources().getDrawable(R.drawable.gren_button));
        Dziecko.setBackground(getResources().getDrawable(R.drawable.blue_buton));
    }
   public void UstawDziecience(View v){
        Menskie = false;
        Damskie = false;
        Dzieciece = true;


        Menszczyzna.setBackground(getResources().getDrawable(R.drawable.blue_buton));
        Kobieta.setBackground(getResources().getDrawable(R.drawable.blue_buton));
        Dziecko.setBackground(getResources().getDrawable(R.drawable.gren_button));
    }
    public void UstwJednostke(View v){


        v.startAnimation(animbuton);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.listajednostek, null);
        Button BMONDOPOINT = (Button) mView.findViewById(R.id.BMONDOPOINT);
        Button BCENTYMETRY = (Button) mView.findViewById(R.id.BCENTYMETRY);
        Button BEUROPA = (Button) mView.findViewById(R.id.BEUROPA);
        Button BUK = (Button) mView.findViewById(R.id.BUK);
        Button BUS = (Button) mView.findViewById(R.id.BUS);
        Button BCHINY = (Button) mView.findViewById(R.id.BCHINY);
        Button BROSJA = (Button) mView.findViewById(R.id.BROSJA);
        Button BJAPONIA = (Button) mView.findViewById(R.id.BJAPONIA);
        Button BKOREA = (Button) mView.findViewById(R.id.BKOREA);



        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();


        BEUROPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jednostka = "EUROPA";
                PJednostka.setText(getString(R.string.europa));
                dialog.cancel();
            }
        });
        BUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jednostka = "US";
                PJednostka.setText(getString(R.string.us));
                dialog.cancel();
            }
        });
        BCHINY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jednostka = "CHINY";
                PJednostka.setText(getString(R.string.chiny));
                dialog.cancel();
            }
        });
        BUK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jednostka = "UK";
                PJednostka.setText(getString(R.string.uk));
                dialog.cancel();
            }
        });
        BROSJA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jednostka = "ROSJA";
                PJednostka.setText(getString(R.string.rosja));
                dialog.cancel();
            }
        });
        BCENTYMETRY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jednostka = "CENTYMETRY";
                PJednostka.setText(getString(R.string.centymetry));
                dialog.cancel();
            }
        });
        BMONDOPOINT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jednostka = "MONDOPOINT";
                PJednostka.setText(getString(R.string.mondopoint));
                dialog.cancel();
            }
        });
        BJAPONIA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jednostka = "JAPONIA";
                PJednostka.setText(getString(R.string.japonia));
                dialog.cancel();
            }
        });
        BKOREA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jednostka = "KOREA";
                PJednostka.setText(getString(R.string.korea));
                dialog.cancel();
            }
        });
    }






    public void Przelicz(View v){
        v.startAnimation(animbuton);
        Przeliczex();
        if(Autozamykanieklawiatury==true){
           View view = this.getCurrentFocus();
            if (view != null) {
                 InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                 imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
             }}
    }

    public void Przeliczex(){
        try{
            temp = Float.parseFloat(Wejscie.getText().toString());

           if(Menskie){
               if (jednostka == "EUROPA") {
                   try {


                       if (temp >= 39 && temp <= 50) {
                           PrzeliczObliczenieMenskie();
                       }
                       if (temp < 39) {
                           PrzeliczObliczenieStarszeDziecience();
                       }
                       if (temp < 32) {
                           Powiadomienie(getString(R.string.rozmiar_poza_tablica));
                       }
                       if (temp > 50) {
                           Powiadomienie(getString(R.string.rozmiar_poza_tablica));
                       }
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
               if(jednostka == "US"){
                   try {
                   if(temp >= 6 && temp <15.5f ){PrzeliczObliczenieMenskie();}
                   if(temp < 6) {PrzeliczObliczenieStarszeDziecience();}
                   if(temp > 15.5f){Powiadomienie(getString(R.string.rozmiar_poza_tablica));}
               } catch (Exception e) {
                   e.printStackTrace();
               }
               }
               if(jednostka == "UK"){
                   try {
                   if(temp >= 5.5 && temp <15f ){PrzeliczObliczenieMenskie();}
                   if(temp < 5.5) {PrzeliczObliczenieStarszeDziecience();}
                   if(temp > 15f){Powiadomienie(getString(R.string.rozmiar_poza_tablica));}
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
               if(jednostka == "CHINY"){if(temp>=40.0f){
                   PrzeliczObliczenieMenskie(); }
               }
               if(jednostka == "JAPONIA"){
                   PrzeliczObliczenieMenskie();
               }
               if(jednostka == "KOREA"){
                   if(temp >= 245){ PrzeliczObliczenieMenskie();}
                   else {Powiadomienie(getString(R.string.rozmiar_poza_tablica));}
               }

               if(jednostka == "CENTYMETRY"){
                   if(temp >= 25){PrzeliczObliczenieMenskie();}
                   if(temp < 25){PrzeliczObliczenieStarszeDziecience();}
                   if(temp < 20.4){Powiadomienie(getString(R.string.rozmiar_poza_tablica));}
               }
               if(jednostka == "ROSJA"){
                   if(temp >= 39&&temp <= 45){PrzeliczObliczenieMenskie();}
                   //if(temp < 25){PrzeliczObliczenieStarszeDziecience();}
                   if(temp < 39){Powiadomienie(getString(R.string.rozmiar_poza_tablica)+". "+getString(R.string.to_najmiejszy_rosji));}
                   if(temp > 45){Powiadomienie(getString(R.string.rozmiar_poza_tablica)+". 45 "+getString(R.string.to_najwiekszy_rosji));}
               }

           }
           if(Damskie){
               if (jednostka == "EUROPA") {
               if(temp <  35){ PrzeliczObliczenieStarszeDziecience();}
               if(temp >= 35&& temp < 44){ PrzeliczObliczenieDamskie();}
               if(temp > 44 && temp < 50 ){ PrzeliczObliczenieMenskie();}
               if(temp > 50){ Powiadomienie(getString(R.string.rozmiar_poza_tablica));}
               }
               if(jednostka == "US"){
                   if(temp >= 4){ PrzeliczObliczenieDamskie();}
               }
               if(jednostka == "UK"){
                   if(temp >= 2.5){ PrzeliczObliczenieDamskie();}
               }
               if(jednostka == "CENTYMETRY"){
                   if(temp >= 22.4){PrzeliczObliczenieDamskie();}
                   if(temp < 27.7){PrzeliczObliczenieMenskie();}
                   if(temp < 27.7){Powiadomienie(getString(R.string.rozmiar_poza_tablica));}
               }
               if(jednostka == "ROSJA"){
                   if(temp >=35){PrzeliczObliczenieDamskie();}
                   if(temp > 41){Powiadomienie(getString(R.string.rozmiar_poza_tablica)+" 41 "+getString(R.string.to_najwiekszy_rosji));}
               }
               if(jednostka == "CHINY"){if(temp>=40.0f){
                   PrzeliczObliczenieDamskie(); }
               }
               if(jednostka == "JAPONIA"){
                   PrzeliczObliczenieDamskie();
               }
               if(jednostka == "KOREA"){
                   if (temp >= KoKorea[0] && temp<=270) {
                       PrzeliczObliczenieDamskie();
                   }
                   else {Powiadomienie(getString(R.string.rozmiar_poza_tablica));}
               }
           }

           if(Dzieciece) {
               if (jednostka == "EUROPA") {
                   if (temp < 32) {
                       PrzeliczObliczenieDziecience();
                   }
                   if (temp >= 32 && temp < 42) {
                       PrzeliczObliczenieStarszeDziecience();
                   }
                   if (temp > 41) {
                       Powiadomienie(getString(R.string.rozmiar_poza_tablica));
                   }
                   if (temp < 16) {
                       Powiadomienie(getString(R.string.rozmiar_poza_tablica));
                   }
               }
               if (jednostka == "US") {
                   if (temp <= 13) {
                       PrzeliczObliczenieDziecience();
                   }
                   if (temp > 13) {
                       Powiadomienie(getString(R.string.rozmiar_poza_tablica));
                   }
               }
               if (jednostka == "UK") {
                   if (temp <= 13) {
                       PrzeliczObliczenieDziecience();
                   }
                   if (temp > 13) {
                       Powiadomienie(getString(R.string.rozmiar_poza_tablica));
                   }
               }
               if (jednostka == "CENTYMETRY") {
                   if (temp >= 9.7) {
                       PrzeliczObliczenieDziecience();
                   }
                   if (temp <= 20.4) {
                       PrzeliczObliczenieStarszeDziecience();
                   }
                   if (temp < 26.4) {
                       Powiadomienie(getString(R.string.rozmiar_poza_tablica));
                   }
               }
               if (jednostka == "ROSJA") {
                   if (temp >= 16) {
                       PrzeliczObliczenieDziecience();
                   }
                   if (temp >= 32) {
                       PrzeliczObliczenieStarszeDziecience();
                   }
                   if (temp > 34) {
                       Powiadomienie(getString(R.string.rozmiar_poza_tablica));
                   }
               }
               if (jednostka == "CHINY") {
                   if (temp >= 40.0f) {
                       Powiadomienie(getString(R.string.funkcja_niedostempna));
                   }
               }
               if (jednostka == "JAPONIA") {
                   Powiadomienie(getString(R.string.funkcja_niedostempna));
               }

               if (jednostka == "KOREA") {
                   Powiadomienie(getString(R.string.funkcja_niedostempna));
               }
           }





        } catch (NumberFormatException e) {
        e.printStackTrace();
        Powiadomienie(getString(R.string.rozmiar_nie_moze_byc_pusty));
    }

    }




    public void PrzeliczObliczenieMenskie(){

          //  if(sexx = true) {
        try {




                if (jednostka == "EUROPA") {
                   // float temp = Float.parseFloat(Wejscie.getText().toString());
                    for (int i = 0; i <= menskieEu.length; i++) {
                        if (temp == menskieEu[i]) {
                           // Powiadomienie(String.valueOf(i));
                            UstawMenskie(i);
                        }

                    }
                }
                if (jednostka == "US") {
                    for (int i = 0; i < menskieEu.length; i++) {
                        if (temp == menskieUS[i]) {
                            UstawMenskie(i);
                        }
                    }
                }
                if (jednostka == "CHINY") {
                    for (int i = 0; i < menskieEu.length; i++) {
                        if (temp == menskieChiny[i]) {
                            UstawMenskie(i);
                        }
                    }

                }
                if (jednostka == "ROSJA") {
                    for (int i = 0; i < menskieEu.length; i++) {
                        if (temp == menskieRosja[i]) {
                            UstawMenskie(i);
                        }
                    }

                }



                if (jednostka == "CENTYMETRY") {
                    for (int i = 0; i < menskieEu.length; i++) {
                        if (temp == menskiecm[i]) {
                            UstawMenskie(i);
                        }
                    }
                }

                if (jednostka == "MONDOPOINT") {
                    for (int i = 0; i < menskieEu.length; i++) {
                        if (temp == menskieMondopoint[i]) {
                            UstawMenskie(i);
                        }
                    }
                }

                if (jednostka == "JAPONIA") {
                    for (int i = 0; i < menskieJaponia.length; i++) {
                        if (temp == menskieEu[i]) {
                            UstawMenskie(i);

                        }
                    }
                }

               if (jednostka == "KOREA") {
                for (int i = 0; i < menskieKorea.length; i++) {
                    if (temp == menskieKorea[i]) {
                        UstawMenskie(i);

                    }
                }
            }
           } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }



    void UstawMenskie(int i){
        {
           // Powiadomienie(String.valueOf(i));
            try {
                TexMondopoint.setText(String.valueOf(menskieMondopoint[i]));
            } catch (Exception e) {
                TexMondopoint.setText("error");
                e.printStackTrace();
            }
            try {
            TexCentymetry.setText(String.valueOf(menskiecm[i]));} catch (Exception e)
            {
                TexCentymetry.setText("error");
                e.printStackTrace();
            }
            try {

                TexEuropa.setText(String.valueOf(menskieEu[i]));
            } catch (Exception e) {
                TexEuropa.setText("error");
                    e.printStackTrace();
            }
            try {
            TexUk.setText(String.valueOf(menskieUK[i]));
            } catch (Exception e) {
                TexUk.setText("error");
                e.printStackTrace();
            }
            try {
            TexUs.setText(String.valueOf(menskieUS[i]));
            } catch (Exception e) {
                TexUs.setText("error");
                e.printStackTrace();
            }
            try {
            TexChiny.setText(String.valueOf(menskieChiny[i]));
            } catch (Exception e) {
                TexChiny.setText("error");
                e.printStackTrace();
            }
            try {
            TexRosja.setText(String.valueOf(menskieRosja[i]));
            } catch (Exception e) {
                TexRosja.setText("error");
                e.printStackTrace();
            }
            try {
            TexJaponia.setText(String.valueOf(menskieJaponia[i]));
            } catch (Exception e) {
                TexJaponia.setText("error");
                e.printStackTrace();
            }
            try {
                TexKorea.setText(String.valueOf(menskieKorea[i]));
            } catch (Exception e) {
                TexKorea.setText("error");
                e.printStackTrace();
            }

           // return;
        }
    }


    public void PrzeliczObliczenieDamskie(){

        //  if(sexx = true) {
        try {




            if (jednostka == "EUROPA") {
                for (int i = 0; i < KoEu.length; i++) {
                    if (temp == KoEu[i]) {
                        UstawDamskie(i);
                    }
                }
            }
            if (jednostka == "US") {
                for (int i = 0; i < KoUS.length; i++) {
                    if (temp == KoUS[i]) {
                        UstawDamskie(i);
                    }
                }
            }
            if (jednostka == "CHINY") {
                for (int i = 0; i < KoChiny.length; i++) {
                    if (temp == KoChiny[i]) {
                        UstawDamskie(i);
                    }
                }

            }
            if (jednostka == "ROSJA") {
                for (int i = 0; i < KoRosja.length; i++) {
                    if (temp == KoRosja[i]) {
                        UstawDamskie(i);
                    }
                }

            }



            if (jednostka == "CENTYMETRY") {
                for (int i = 0; i < Kocm.length; i++) {
                    if (temp == Kocm[i]) {
                        UstawDamskie(i);
                    }
                }
            }

            if (jednostka == "MONDOPOINT") {
                for (int i = 0; i < KoMondopoint.length; i++) {
                    if (temp == KoMondopoint[i]) {
                        UstawDamskie(i);
                    }
                }
            }

            if (jednostka == "JAPONIA") {
                for (int i = 0; i < KoJaponia.length; i++) {
                    if (temp == KoJaponia[i]) {
                        UstawDamskie(i);

                    }
                }
            }

            if (jednostka == "KOREA") {
                for (int i = 0; i < KoKorea.length; i++) {
                    if (temp == KoKorea[i]) {
                        UstawDamskie(i);

                    }
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }



    void UstawDamskie(int i){


            try {
                TexMondopoint.setText(String.valueOf(KoMondopoint[i]));
            } catch (Exception e) {
                TexMondopoint.setText("error");
                e.printStackTrace();
            }
            try {
                TexCentymetry.setText(String.valueOf(Kocm[i]));} catch (Exception e)
            {
                TexCentymetry.setText("error");
                e.printStackTrace();
            }
            try {

                TexEuropa.setText(String.valueOf(KoEu[i]));
            } catch (Exception e) {
                TexEuropa.setText("error");
                e.printStackTrace();
            }
            try {
                TexUk.setText(String.valueOf(KoUK[i]));
            } catch (Exception e) {
                TexUk.setText("error");
                e.printStackTrace();
            }
            try {
                TexUs.setText(String.valueOf(KoUS[i]));
            } catch (Exception e) {
                TexUs.setText("error");
                e.printStackTrace();
            }
            try {
                TexChiny.setText(String.valueOf(KoChiny[i]));
            } catch (Exception e) {
                TexChiny.setText("error");
                e.printStackTrace();
            }
            try {
                TexRosja.setText(String.valueOf(KoRosja[i]));
            } catch (Exception e) {
                TexRosja.setText("error");
                e.printStackTrace();
            }
            try {
                TexJaponia.setText(String.valueOf(KoJaponia[i]));
            } catch (Exception e) {
                TexJaponia.setText("error");
                e.printStackTrace();
            }

           try {
              TexKorea.setText(String.valueOf(KoKorea[i]));
           } catch (Exception e) {
            TexKorea.setText("error");
            e.printStackTrace();
           }

            // return;
        }

        public void PrzeliczObliczenieDziecience(){

            try {

                if (jednostka == "EUROPA") {
                    for (int i = 0; i < DzieciEu.length; i++) {
                        if (temp == DzieciEu[i]) {
                            UstawDziecience(i);
                        }
                    }
                }
                if (jednostka == "US") {
                    for (int i = 0; i < DzieciUS.length; i++) {
                        if (temp == DzieciUS[i]) {
                            UstawDziecience(i);
                        }
                    }
                }
                if (jednostka == "CHINY") {
                    for (int i = 0; i < DzieciChiny.length; i++) {
                        if (temp == DzieciChiny[i]) {
                            UstawDziecience(i);
                        }
                    }

                }
                if (jednostka == "ROSJA") {
                    for (int i = 0; i < DzieciRosja.length; i++) {
                        if (temp == DzieciRosja[i]) {
                            UstawDziecience(i);
                        }
                    }

                }



                if (jednostka == "CENTYMETRY") {
                    for (int i = 0; i < Dziecicm.length; i++) {
                        if (temp == Dziecicm[i]) {
                            UstawDziecience(i);
                        }
                    }
                }

                if (jednostka == "MONDOPOINT") {
                    for (int i = 0; i < DzieciMondopoint.length; i++) {
                        if (temp == DzieciMondopoint[i]) {
                            UstawDziecience(i);
                        }
                    }
                }

                if (jednostka == "JAPONIA") {
                    for (int i = 0; i < DzieciJaponia.length; i++) {
                        if (temp == DzieciJaponia[i]) {
                            UstawDziecience(i);

                        }
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }



        void UstawDziecience(int i){
            {

                try {
                    TexMondopoint.setText(String.valueOf(DzieciMondopoint[i]));
                } catch (Exception e) {
                    TexMondopoint.setText("error");
                    e.printStackTrace();
                }
                try {
                    TexCentymetry.setText(String.valueOf(Dziecicm[i]));} catch (Exception e)
                {
                    TexCentymetry.setText("error");
                    e.printStackTrace();
                }
                try {

                    TexEuropa.setText(String.valueOf(DzieciEu[i]));
                } catch (Exception e) {
                    TexEuropa.setText("error");
                    e.printStackTrace();
                }
                try {
                    TexUk.setText(String.valueOf(DzieciUK[i]));
                } catch (Exception e) {
                    TexUk.setText("error");
                    e.printStackTrace();
                }
                try {
                    TexUs.setText(String.valueOf(DzieciUS[i]));
                } catch (Exception e) {
                    TexUs.setText("error");
                    e.printStackTrace();
                }
                try {
                    TexChiny.setText(String.valueOf(DzieciChiny[i]));
                } catch (Exception e) {
                    TexChiny.setText("error");
                    e.printStackTrace();
                }
                try {
                    TexRosja.setText(String.valueOf(DzieciRosja[i]));
                } catch (Exception e) {
                    TexRosja.setText("error");
                    e.printStackTrace();
                }
                try {
                    TexJaponia.setText(String.valueOf(DzieciJaponia[i]));
                } catch (Exception e) {
                    TexJaponia.setText("error");
                    e.printStackTrace();
                }

                // return;
            }
        }

    public void PrzeliczObliczenieStarszeDziecience(){

        try {

            if (jednostka == "EUROPA") {
                for (int i = 0; i < DzieciStarszeEu.length; i++) {
                    if (temp == DzieciStarszeEu[i]) {
                        UstawStarszeDziecience(i);
                    }
                }
            }

            if (jednostka == "US") {
                for (int i = 0; i < DzieciStarszeUS.length; i++) {
                    if (temp == DzieciStarszeUS[i]) {
                        UstawStarszeDziecience(i);
                    }
                }
            }

            if (jednostka == "CHINY") {
                for (int i = 0; i < DzieciStarszeChiny.length; i++) {
                    if (temp == DzieciStarszeChiny[i]) {
                        UstawStarszeDziecience(i);
                    }
                }

            }

            if (jednostka == "ROSJA") {
                for (int i = 0; i < DzieciStarszeRosja.length; i++) {
                    if (temp == DzieciStarszeRosja[i]) {
                        UstawStarszeDziecience(i);
                    }
                }

            }



            if (jednostka == "CENTYMETRY") {
                for (int i = 0; i < DzieciStarszecm.length; i++) {
                    if (temp == DzieciStarszecm[i]) {
                        UstawStarszeDziecience(i);
                    }
                }
            }

            if (jednostka == "MONDOPOINT") {
                for (int i = 0; i < DzieciStarszeMondopoint.length; i++) {
                    if (temp == DzieciStarszeMondopoint[i]) {
                        UstawStarszeDziecience(i);
                    }
                }
            }

            if (jednostka == "JAPONIA") {
                for (int i = 0; i < DzieciStarszeJaponia.length; i++) {
                    if (temp == DzieciStarszeJaponia[i]) {
                        UstawStarszeDziecience(i);

                    }
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }



    void UstawStarszeDziecience(int i){
        {
            try {
                TexMondopoint.setText(String.valueOf(DzieciStarszeMondopoint[i]));
            } catch (Exception e) {
                TexMondopoint.setText("error");
                e.printStackTrace();
            }
            try {
                TexCentymetry.setText(String.valueOf(DzieciStarszecm[i]));} catch (Exception e)
            {
                TexCentymetry.setText("error");
                e.printStackTrace();
            }
            try {

                TexEuropa.setText(String.valueOf(DzieciStarszeEu[i]));
            } catch (Exception e) {
                TexEuropa.setText("error");
                e.printStackTrace();
            }
            try {
                TexUk.setText(String.valueOf(DzieciStarszeUK[i]));
            } catch (Exception e) {
                TexUk.setText("error");
                e.printStackTrace();
            }
            try {
                TexUs.setText(String.valueOf(DzieciStarszeUS[i]));
            } catch (Exception e) {
                TexUs.setText("error");
                e.printStackTrace();
            }
            try {
                TexChiny.setText(String.valueOf(DzieciStarszeChiny[i]));
            } catch (Exception e) {
                TexChiny.setText("error");
                e.printStackTrace();
            }
            try {
                TexRosja.setText(String.valueOf(DzieciStarszeRosja[i]));
            } catch (Exception e) {
                TexRosja.setText("error");
                e.printStackTrace();
            }
            try {
                TexJaponia.setText(String.valueOf(DzieciStarszeJaponia[i]));
            } catch (Exception e) {
                TexJaponia.setText("error");
                e.printStackTrace();
            }

            // return;
        }
    }

    void Powiadomienie(String tresc){

        Toast.makeText(getApplicationContext(), tresc, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        // back was pressed

        setContentView(R.layout.activity_main);
        SetUP();
    }







}
