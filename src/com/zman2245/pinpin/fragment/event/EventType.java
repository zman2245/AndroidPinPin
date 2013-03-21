package com.zman2245.pinpin.fragment.event;

/**
 * An enumeration of possible fragment events
 *
 * @author Zack
 */
public enum EventType
{
	QUIZ_LISTITEM_CLICK("quiz-listitem-click"),
	QUIZ_END("quiz-end"),
	QUIZ_NEXT("quiz-next");

	public String name;

	private EventType(String name)
	{
		this.name = name;
	}
}
