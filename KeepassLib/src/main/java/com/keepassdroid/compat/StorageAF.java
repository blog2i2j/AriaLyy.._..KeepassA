/*
 * Copyright 2016-2018 Brian Pellin.
 *
 * This file is part of KeePassDroid.
 *
 *  KeePassDroid is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  KeePassDroid is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with KeePassDroid.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.keepassdroid.compat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import java.lang.reflect.Field;
import keepass2android.kp2akeytransform.R;

/**
 * Created by bpellin on 3/10/16.
 */
public class StorageAF {

  public static String ACTION_OPEN_DOCUMENT;

  static {
    try {
      Field openDocument = Intent.class.getField("ACTION_OPEN_DOCUMENT");
      ACTION_OPEN_DOCUMENT = (String) openDocument.get(null);
    } catch (Exception e) {
      ACTION_OPEN_DOCUMENT = "android.intent.action.OPEN_DOCUMENT";
    }
  }

  public static boolean supportsStorageFramework() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
  }

  public static boolean useStorageFramework(Context ctx) {
    if (!supportsStorageFramework()) {
      return false;
    }

    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
    return prefs.getBoolean(ctx.getString(R.string.saf_key),
        ctx.getResources().getBoolean(R.bool.saf_default));
  }
}
