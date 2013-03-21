package com.zman2245.pinpin;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.zman2245.pinpin.adapter.list.AdapterListQuiz;
import com.zman2245.pinpin.data.DataItemQuiz;
import com.zman2245.pinpin.fragment.event.Event;
import com.zman2245.pinpin.fragment.event.FragmentEventListener;
import com.zman2245.pinpin.fragment.quiz.FragmentQuizQuestion;

/**
 * Quiz Activity
 *
 * @author zack
 */
public class ActivityQuiz extends SherlockFragmentActivity
	implements FragmentEventListener
{
	private DataItemQuiz[] mCurrentQuizData;
	private int mCurrentQuestionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_main_quiz);

        ListView listView               = (ListView)findViewById(R.id.list);
        final AdapterListQuiz adapter   = new AdapterListQuiz(this, getLayoutInflater());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                navigateToQuizSection(position);
            }
        });
    }

    private void navigateToQuizSection(int index)
    {
        // TBD
    	mCurrentQuizData = AppPinPin.sQuizGenerator.getQuizQuestions(index);
        Log.d("TESTING", "quiz data: " + mCurrentQuizData[0].answers[0]);

        FragmentQuizQuestion frag  = FragmentQuizQuestion.newInstance(mCurrentQuizData[0]);
        FragmentManager fm      = getSupportFragmentManager();
        FragmentTransaction ft  = fm.beginTransaction();

        ft.add(R.id.container, frag, "quit_flow");
        ft.addToBackStack("quit_flow");
        ft.commit();
    }

    private void moveToNextQuizItem()
    {

    }

    // FragmentEventListener impl

	@Override
	public void handleEvent(Event event)
	{

	}
}
