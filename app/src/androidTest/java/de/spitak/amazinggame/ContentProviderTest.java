package de.spitak.amazinggame;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by rschlett on 12/30/16.
 */

@RunWith(AndroidJUnit4.class)
public class ContentProviderTest {
    @Test
    public void getGamesMultipleRowsType() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = Uri.parse("content://de.spitak.amazinggame.SomethingSomethingProvider/games");
        assertEquals("vnd.android.cursor.dir/vnd.amazinggame.games", contentResolver.getType(uri));
    }
}
