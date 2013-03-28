package com.zman2245.pinpin.model;

import com.zman2245.pinpin.AppPinPin;
import com.zman2245.pinpin.R;


/**
 * Quiz end model
 *
 * @author Zack
 */
public class ModelQuizEnd
{
	private final float mCorrectPct;
	private final float mIncorrectPct;

	public ModelQuizEnd(float correctPct, float incorrectPct)
	{
		mCorrectPct 	= correctPct;
		mIncorrectPct 	= incorrectPct;
	}

	/**
	 * Tells whether or not the quiz was passed or failed
	 *
	 * @return
	 */
	public boolean didPass()
	{
		return mCorrectPct >= .6f;
	}

	public String getTitle()
	{
		return didPass() ? AppPinPin.getAppResources().getString(R.string.quiz_end_title_win) :
						   AppPinPin.getAppResources().getString(R.string.quiz_end_title_lose);
	}

	public String getSubtitle()
	{
		return didPass() ? AppPinPin.getAppResources().getString(R.string.quiz_end_subtitle_win) :
			   AppPinPin.getAppResources().getString(R.string.quiz_end_subtitle_lose);
	}

	public int getTrophyResourceId()
	{
		// TODO
		return didPass() ? R.id.img_trophy : R.id.img_trophy;
	}

	public String getCorrectPctText()
	{
		return String.format("%.01f", mCorrectPct*100.0f);
	}

	public String getIncorrectPctText()
	{
		return String.format("%.01f", mIncorrectPct*100.0f);
	}
}
