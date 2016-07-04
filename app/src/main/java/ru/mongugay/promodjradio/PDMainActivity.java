package ru.mongugay.promodjradio;

import android.annotation.TargetApi;
import android.media.AudioManager;
import android.media.MediaFormat;
import android.media.MediaMetadata;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import ru.mongugay.promodjradio.model.PDRadioModel;

public class PDMainActivity extends AppCompatActivity {

    private TextView _title;
    private MediaPlayer _mediaPlayer;
    private LinearLayout _elementList;
    private String[] _radioNameList;
    private String[] _radioUrlList;
    private PDRadioModel[] _radios;

    private MediaMetadataRetriever _mediaMetaData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdmain);

        //_title = (TextView) findViewById(R.id.helloWorld);
        _elementList = (LinearLayout) findViewById(R.id.elementList);

        _radioNameList = getResources().getStringArray(R.array.radioNameList);
        _radioUrlList  = getResources().getStringArray(R.array.radioUrlList);

        _radios = new PDRadioModel[_radioNameList.length];
        for (int i = 0; i < _radioNameList.length; ++i)
        {
            PDRadioModel model = new PDRadioModel(_radioNameList[i], _radioUrlList[i]);
            _radios[i] = model;
        }

        for (int i = 0; i < _radios.length; ++i)
        {
            Button button = new Button(this);
            button.setText(_radios[i].get_name());
            button.setTag(_radios[i]);
            button.setGravity(Gravity.CENTER);
            button.setOnClickListener(button1ClickHandler);
            _elementList.addView(button);
        }
    }
    public View.OnClickListener button1ClickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PDRadioModel model = (PDRadioModel) v.getTag();
            playStream(model.get_url());
        }
    };

    /**
     * Проигрыаем поток
     * @param url
     */
    public void playStream(String url) {

        if (_mediaPlayer == null) {
            _mediaPlayer = new MediaPlayer();
            _mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }

        if (_mediaPlayer.isPlaying()) {
            _mediaPlayer.reset();
        }

        try {
            _mediaPlayer.setDataSource(url);
            _mediaPlayer.prepare();
            _mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){

                @Override
                public void onPrepared(MediaPlayer mp) {
                    _mediaPlayer.start();
                }
            });
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
}