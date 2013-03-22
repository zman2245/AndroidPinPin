package com.zman2245.pinpin;

import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.zman2245.pinpin.adapter.list.AdapterListQuiz;
import com.zman2245.pinpin.data.DataItemQuiz;
import com.zman2245.pinpin.fragment.event.Event;
import com.zman2245.pinpin.fragment.event.EventData;
import com.zman2245.pinpin.fragment.event.FragmentEventListener;
import com.zman2245.pinpin.fragment.modelwrapper.FragmentModelWrapper;
import com.zman2245.pinpin.fragment.quiz.FragmentQuizEnd;
import com.zman2245.pinpin.fragment.quiz.FragmentQuizQuestion;
import com.zman2245.pinpin.model.ModelQuiz;
import com.zman2245.pinpin.model.ModelQuizQuestion;

/**
 * Quiz Activity
 *
 * @author zack
 */
public class ActivityQuiz extends SherlockFragmentActivity
	implements FragmentEventListener
{
	private FragmentModelWrapper<ModelQuiz> mFragModel;
	private ModelQuiz mModelQuiz;

    // FragmentEventListener impl

	@Override
	public void handleEvent(Event event)
	{
		switch (event.type)
		{
		case QUIZ_CONTINUE:
			HashMap<String, Object> map = event.data;
			mModelQuiz.updateWithQuestionModel((ModelQuizQuestion)map.get(EventData.DATA_KEY_MODEL));

			moveToNextQuizItem();
			break;

		case QUIZ_END:
			endQuiz();
			break;

		default:
		}
	}

	// Fragment hooks

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

        initModel();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
            	if (getSupportFragmentManager().findFragmentByTag("quiz_flow") != null)
            		cancelQuiz();
            	return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
	public void onBackPressed()
    {
    	// TODO: should handle nav within a quiz
    	super.onBackPressed();
    }

    // private helpers

    @SuppressWarnings("unchecked")
	private void initModel()
    {
    	FragmentManager mgr = getSupportFragmentManager();
    	mFragModel = (FragmentModelWrapper<ModelQuiz>)mgr.findFragmentByTag("model");
    	if (mFragModel == null)
    	{
    		mFragModel = FragmentModelWrapper.<ModelQuiz>newInstance(null);
    	}
    	else
    	{
    		mModelQuiz = mFragModel.getModel();
    		// TODO: do we need to restore ui state?
    	}
    }

    private void navigateToQuizSection(int index)
    {
    	DataItemQuiz[] datas 	= AppPinPin.sQuizGenerator.getQuizQuestions(index);
    	mModelQuiz 				= new ModelQuiz(datas);
    	mFragModel.setModel(mModelQuiz);

        moveToNextQuizItem();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void moveToNextQuizItem()
    {
    	Fragment frag;
    	DataItemQuiz data = mModelQuiz.next();

    	if (data == null)
    	{
    		// End of quiz
    		frag = FragmentQuizEnd.newInstance(mModelQuiz.getQuizEndData());
    	}
    	else
    	{
    		frag = FragmentQuizQuestion.newInstance(data);
    	}

        FragmentManager fm      = getSupportFragmentManager();
        FragmentTransaction ft  = fm.beginTransaction();

        ft.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left);

        if (fm.findFragmentByTag("quiz_flow") == null)
        	ft.add(R.id.container, frag, "quiz_flow");
        else
        	ft.replace(R.id.container, frag, "quiz_flow");

        ft.commit();
    }

    // TODO: save state? Right now "cancel" and "end" are the same, but should they be?
    private void endQuiz()
    {
    	FragmentManager fm      = getSupportFragmentManager();
        FragmentTransaction ft  = fm.beginTransaction();
        Fragment frag 			= fm.findFragmentByTag("quiz_flow");

        ft.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left);

        if (frag != null)
        {
        	ft.remove(frag);
        }

        ft.commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    private void cancelQuiz()
    {
    	endQuiz();
    }
}
