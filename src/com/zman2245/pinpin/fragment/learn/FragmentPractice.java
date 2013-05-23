package com.zman2245.pinpin.fragment.learn;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zman2245.pinpin.AppPinPin;
import com.zman2245.pinpin.R;
import com.zman2245.pinpin.data.DataItemPractice;
import com.zman2245.pinpin.util.audio.UtilAudioPlayer;

/**
 * A fragment for a practice word
 *
 * @author zack
 */
public class FragmentPractice extends Fragment implements OnClickListener
{
    private static final String KEY_DATA = "data";

    private static String mFileName = null;
    static
    {
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/audiorecordtest.3gp";
    }

    private MediaRecorder mRecorder = null;
    private MediaPlayer   mPlayer   = null;

    private String[] mTones;
    private String mParentWord;
    private String mCurrentWord;

    private TextView mTitle;

    private ImageButton mFirst;
    private ImageButton mSecond;
    private ImageButton mThird;
    private ImageButton mFourth;

    private ImageButton mMicrophone;
    private ImageButton mRecord;
    private ImageButton mPlayback;

    private View mContainer;

    /**
     * FragmentPractice construction
     *
     * @param data  The data this fragment will present
     * @return A new instance of FragmentPractice
     */
    public static FragmentPractice newInstance(DataItemPractice data)
    {
        FragmentPractice frag  = new FragmentPractice();
        Bundle args            = new Bundle();

        args.putSerializable(KEY_DATA, data);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_practice, container, false);

        mTitle = (TextView)rootView.findViewById(R.id.title);

        mFirst  = (ImageButton)rootView.findViewById(R.id.btn_tone_first);
        mSecond = (ImageButton)rootView.findViewById(R.id.btn_tone_second);
        mThird  = (ImageButton)rootView.findViewById(R.id.btn_tone_third);
        mFourth = (ImageButton)rootView.findViewById(R.id.btn_tone_fourth);
        mMicrophone = (ImageButton)rootView.findViewById(R.id.btn_microphone);
        mRecord     = (ImageButton)rootView.findViewById(R.id.btn_record);
        mPlayback   = (ImageButton)rootView.findViewById(R.id.btn_play);

        mFirst.setOnClickListener(this);
        mSecond.setOnClickListener(this);
        mThird.setOnClickListener(this);
        mFourth.setOnClickListener(this);
        mMicrophone.setOnClickListener(this);
        mRecord.setOnClickListener(this);
        mPlayback.setOnClickListener(this);

        mPlayback.setEnabled(false);

        DataItemPractice data = (DataItemPractice)getArguments().getSerializable(KEY_DATA);
        mParentWord = data.word;
        HashMap<String, Object> map = (HashMap<String, Object>)AppPinPin.sSoundMap.get(mParentWord);
        mTones = (String[])map.get("title");

        mCurrentWord = mTones[0];
        mTitle.setText(mTones[0]);
        mFirst.setSelected(true);

        playSoundForCurrentWord();

        mContainer = rootView.findViewById(R.id.outer_container);
        mContainer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                animateOut();
            }
        });

        return rootView;
    }

    @Override
    public void onPause()
    {
        super.onPause();

        if (mRecorder != null)
        {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null)
        {
            mPlayer.release();
            mPlayer = null;
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
        case R.id.btn_tone_first:
            mCurrentWord = mTones[0];
            mTitle.setText(mTones[0]);
            playSoundForCurrentWord();
            clearSelectedTone();
            mFirst.setSelected(true);
            break;

        case R.id.btn_tone_second:
            mCurrentWord = mTones[1];
            mTitle.setText(mTones[1]);
            playSoundForCurrentWord();
            clearSelectedTone();
            mSecond.setSelected(true);
            break;

        case R.id.btn_tone_third:
            mCurrentWord = mTones[2];
            mTitle.setText(mTones[2]);
            playSoundForCurrentWord();
            clearSelectedTone();
            mThird.setSelected(true);
            break;

        case R.id.btn_tone_fourth:
            mCurrentWord = mTones[3];
            mTitle.setText(mTones[3]);
            playSoundForCurrentWord();
            clearSelectedTone();
            mFourth.setSelected(true);
            break;

        case R.id.btn_microphone:
            playSoundForCurrentWord();
            break;

        case R.id.btn_record:
            if (mRecorder == null)
                startRecording();
            else
                stopRecording();
            break;

        case R.id.btn_play:
            if (mPlayer != null && mPlayer.isPlaying())
                mPlayer.pause();
            else if (mPlayer != null)
                mPlayer.start();
            else
                startPlaying();
            break;

        default:
            break;
        }
    }

    // public api

    public void animateOut()
    {
        Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out_default);
        fadeOut.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
            }
            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                getActivity().finish();
            }
        });
        mContainer.startAnimation(fadeOut);
    }

    // private helpers

    private void startRecording()
    {
        File file = new File(mFileName);
//        boolean deleted = file.delete();
//        Log.d("TESTING", "deleted file: " + deleted);
        Log.d("TESTING", "filename for recording: " + mFileName);
        mPlayer = null; // reset player
        mPlayback.setEnabled(false);
        mRecord.setImageResource(R.drawable.recorder_orange);
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mRecorder.setMaxDuration(30000);

        mRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener()
        {
            @Override
            public void onInfo(MediaRecorder mr, int what, int extra)
            {
                if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED)
                {
                    stopRecording();
                }
            }
        });

        try
        {
            mRecorder.prepare();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        mRecorder.start();
    }

    private void stopRecording()
    {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        mRecord.setImageResource(R.drawable.recorder_light);
        mPlayback.setEnabled(true);
    }

    private void startPlaying()
    {
        mPlayer = new MediaPlayer();
        try
        {
            mPlayer.setDataSource(mFileName);

            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
            {
                @Override
                public void onCompletion(MediaPlayer mp)
                {
                    // TODO: might use this to set play/pause icon on button
                }
            });

            mPlayer.prepare();
            mPlayer.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void stopPlaying()
    {
        mPlayer.release();
        mPlayer = null;
    }

    private void playSoundForCurrentWord()
    {
        int resId = AppPinPin.getAudioMapper().getResourceForString(mCurrentWord);
        UtilAudioPlayer.playSound(resId);
    }

    private void clearSelectedTone()
    {
        mFirst.setSelected(false);
        mSecond.setSelected(false);
        mThird.setSelected(false);
        mFourth.setSelected(false);
    }
}
