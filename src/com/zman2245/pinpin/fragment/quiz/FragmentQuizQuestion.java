package com.zman2245.pinpin.fragment.quiz;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.zman2245.pinpin.AppPinPin;
import com.zman2245.pinpin.R;
import com.zman2245.pinpin.adapter.grid.AdapterGridQuizItem;
import com.zman2245.pinpin.data.DataItemQuiz;
import com.zman2245.pinpin.fragment.event.Event;
import com.zman2245.pinpin.fragment.event.FragmentEventListener;
import com.zman2245.pinpin.model.ModelQuizQuestion;
import com.zman2245.pinpin.util.audio.UtilAudioPlayer;
import com.zman2245.pinpin.view.listitem.ViewQuizChoice;

/**
 * A fragment for a single quiz question
 *
 * @author zack
 */
public class FragmentQuizQuestion extends Fragment
	implements OnItemClickListener
{
	private static final String KEY_DATA = "data";

	private GridView mGrid;
	private AdapterGridQuizItem mAdapter;
	private DataItemQuiz mData;
	private ModelQuizQuestion mModel;
	private int mNumSubQuestions;

	/**
	 * FragmentQuizItem construction
	 *
	 * @param data
	 *            The data this fragment will present
	 * @return A new instance of FragmentLearnFlowItem
	 */
	public static FragmentQuizQuestion newInstance(DataItemQuiz data)
	{
		FragmentQuizQuestion frag = new FragmentQuizQuestion();
		Bundle args = new Bundle();

		args.putSerializable(KEY_DATA, data);
		frag.setArguments(args);

		return frag;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		mData 				= (DataItemQuiz)getArguments().get(KEY_DATA);
		mModel 				= new ModelQuizQuestion(mData.answers);
		mNumSubQuestions 	= mData.answers.length;

		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_quiz_item, container, false);

		mGrid	 = (GridView)rootView.findViewById(R.id.grid_choice);
		mAdapter = new AdapterGridQuizItem(mData.choices, inflater);

		mGrid.setNumColumns(mNumSubQuestions);
		mGrid.setAdapter(mAdapter);
		mGrid.setOnItemClickListener(this);

		View answerView = rootView.findViewById(R.id.answer);
		answerView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				playAnswer();
			}
		});
		playAnswer();

		return rootView;
	}

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
    	ViewQuizChoice choiceView 	= (ViewQuizChoice)view;
        String word 				= (String)mAdapter.getItem(position);
        int subQuestion 			= getSubQuestionNum(position);

        boolean isCorrect = mModel.answer(word, subQuestion);
        if (isCorrect)
        {
        	choiceView.setCorrect();
        	mAdapter.setColumnEnabled(subQuestion, false);
        }
        else
        {
        	choiceView.setIncorrect();
        }
    }

    private int getSubQuestionNum(int position)
    {
    	return position % mNumSubQuestions;
    }

    private void playAnswer()
    {
		for (String answer : mData.answers)
		{
	        int resId = AppPinPin.getAudioMapper().getResourceForString(answer);

	        UtilAudioPlayer.playSound(resId);
		}
    }

    /**
     * Send an event to this fragment's parent (could be a parent fragment or an activity)
     *
     * @param event The event to send
     */
    private void sendEvent(Event event)
    {
    	Fragment frag = getParentFragment();
    	if (frag != null && frag instanceof FragmentEventListener)
    	{
    		((FragmentEventListener)frag).handleEvent(event);
    		return;
    	}

    	Activity activity = getActivity();
    	if (activity != null && activity instanceof FragmentEventListener)
    	{
    		((FragmentEventListener)activity).handleEvent(event);
    	}
    }
}
