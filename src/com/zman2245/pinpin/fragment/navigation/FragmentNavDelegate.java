package com.zman2245.pinpin.fragment.navigation;


/**
 *
 * @author Zack
 *
 * @param <T> A Data object
 */
public interface FragmentNavDelegate <T>
{
    public T next();

    public T previous();
}
