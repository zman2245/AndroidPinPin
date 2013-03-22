package com.zman2245.pinpin;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.zman2245.pinpin.adapter.list.AdapterListQuiz;
import com.zman2245.pinpin.data.DataItemQuiz;
import com.zman2245.pinpin.fragment.event.Event;
import com.zman2245.pinpin.fragment.event.FragmentEventListener;
import com.zman2245.pinpin.fragment.modelwrapper.FragmentModelWrapper;
import com.zman2245.pinpin.fragment.quiz.FragmentQuizQuestion;
import com.zman2245.pinpin.model.ModelQuiz;

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
			moveToNextQuizItem();
			break;

		case QUIZ_END:
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
    }

    private void moveToNextQuizItem()
    {
    	FragmentQuizQuestion frag  = FragmentQuizQuestion.newInstance(mModelQuiz.next());
        FragmentManager fm      = getSupportFragmentManager();
        FragmentTransaction ft  = fm.beginTransaction();

        ft.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left);

        if (fm.findFragmentByTag("quiz_flow") == null)
        	ft.add(R.id.container, frag, "quiz_flow");
        else
        	ft.replace(R.id.container, frag, "quiz_flow");

        ft.commit();
    }
}
