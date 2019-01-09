package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import org.junit.Test;
import org.junit.runner.RunWith;



@RunWith(AndroidJUnit4.class)
@LargeTest
public class EndPointTest extends AndroidTestCase {

    @Test
    public void testDoInBackground() throws Exception {

        String result = null;
        Context context = getContext();
        DataQueryAsyncTask endpointsAsyncTask = new DataQueryAsyncTask();
        endpointsAsyncTask.execute(context);
        try {
            result = endpointsAsyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Thread.sleep(4000);
        assertNotNull(result);
    }
}
