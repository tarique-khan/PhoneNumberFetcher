package com.example.phonenumberfetcher;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class Util {

    private static final String TAG = "Util";

    public static void fetchNumbers(Context context) {
        String main_data[] = {"data1", "is_primary", "data3", "data2", "data1", "is_primary", "photo_uri", "mimetype"};
        Object object = context.getContentResolver().query(Uri.withAppendedPath(android.provider.ContactsContract.Profile.CONTENT_URI, "data"),
                main_data, "mimetype=?",
                new String[]{"vnd.android.cursor.item/phone_v2"},
                "is_primary DESC");
        if (object != null) {
            do {
                if (!((Cursor) (object)).moveToNext())
                    break;
                // This is the phoneNumber
                String s1 = ((Cursor) (object)).getString(4);
                Log.d(TAG, "fetchNumbers: " + s1);
            } while (true);
            ((Cursor) (object)).close();
        }
    }
}
