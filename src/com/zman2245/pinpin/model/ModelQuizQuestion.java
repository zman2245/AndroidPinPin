package com.zman2245.pinpin.model;


/**
 * Quiz question model
 *
 * @author Zack
 */
public class ModelQuizQuestion
{
	public enum QuizQuestionState
	{
		UNANSWERED, CORRECT, INCORRECT
	}

	private final QuizQuestionState[] mStates;
	private final String[] mAnswers;

	public ModelQuizQuestion(String[] answers)
	{
		mAnswers 	= answers;
		mStates 	= new QuizQuestionState[mAnswers.length];

		for (int i = 0; i < mStates.length; i++)
		{
			mStates[i] = QuizQuestionState.UNANSWERED;
		}
	}

	public QuizQuestionState[] getStates()
	{
		return mStates;
	}

	public QuizQuestionState getStateAt(int index)
	{
		return mStates[index];
	}

	/**
	 * Check the validity of a guess
	 *
	 * @param guess
	 * @return true if the guess given is correct; otherwise false
	 */
	public boolean answer(String guess, int index)
	{
		boolean isCorrect = mAnswers[index].equals(guess);

		if (mStates[index] == QuizQuestionState.UNANSWERED)
		{
			mStates[index] = isCorrect ? QuizQuestionState.CORRECT : QuizQuestionState.INCORRECT;
		}

		return isCorrect;
	}
}
