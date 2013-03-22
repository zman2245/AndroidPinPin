package com.zman2245.pinpin.model;

import com.zman2245.pinpin.data.DataItemQuiz;
import com.zman2245.pinpin.model.ModelQuizQuestion.QuizQuestionState;

/**
 * Quiz model
 *
 * @author Zack
 */
public class ModelQuiz
{
	private final QuizQuestionState[] mQuestionStates;
	private final DataItemQuiz[] mQuizDatas;
	private int mCurrentQuestion;

	public ModelQuiz(DataItemQuiz[] quizData)
	{
		mQuizDatas 			= quizData;
		mQuestionStates 	= ModelQuizQuestion.getNewArray(quizData.length);
		mCurrentQuestion 	= -1;
	}

	/**
	 * Increments the current question pointer and returns the
	 * quiz question data at the new pointer
	 *
	 * @return
	 */
	public DataItemQuiz next()
	{
		mCurrentQuestion++;

		if (mQuizDatas == null || mCurrentQuestion >= mQuizDatas.length)
			return null;

		return mQuizDatas[mCurrentQuestion];
	}

	/**
	 * Calculates the score
	 *
	 * "UNANSWERED" questions are not included in the calculation
	 *
	 * @return
	 */
	public float getScore()
	{
		float correct 	= 0;
		float incorrect = 0;;

		for (QuizQuestionState state : mQuestionStates)
		{
			if (state == QuizQuestionState.CORRECT)
				correct++;
			else if (state == QuizQuestionState.INCORRECT)
				incorrect++;
		}

		return (correct / (correct + incorrect));
	}
}
