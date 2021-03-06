/***************************************************************************************
 * Copyright (c) 2015 Timothy Rae <perceptualchaos2@gmail.com>                          *
 *                                                                                      *
 * This program is free software; you can redistribute it and/or modify it under        *
 * the terms of the GNU General Public License as published by the Free Software        *
 * Foundation; either version 3 of the License, or (at your option) any later           *
 * version.                                                                             *
 *                                                                                      *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                      *
 * You should have received a copy of the GNU General Public License along with         *
 * this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 ****************************************************************************************/

package com.ichi2.compat;


import android.os.Build;
import android.view.KeyCharacterMap;

public class CompatHelper {
    private static CompatHelper sInstance;
    private Compat mCompat;


    private CompatHelper() {

        if (getSdkVersion() >= 28) {
            mCompat = new CompatV28();
        } else if (getSdkVersion() >= 26) {
            mCompat = new CompatV26();
        } else if (getSdkVersion() >= 24) {
            mCompat = new CompatV24();
        } else if (getSdkVersion() >= 23) {
            mCompat = new CompatV23();
        } else if (getSdkVersion() >= 21) {
            mCompat = new CompatV21();
        } else if (getSdkVersion() >= 19) {
            mCompat = new CompatV19();
        } else if (getSdkVersion() >= 18) {
            mCompat = new CompatV18();
        } else if (getSdkVersion() >= 17) {
            mCompat = new CompatV17();
        } else {
            mCompat = new CompatV16();
        }
    }

    /** Get the current Android API level. */
    public static int getSdkVersion() {
        return Build.VERSION.SDK_INT;
    }

    /** Determine if the device is running API level 21 or higher. */
    public static boolean isLollipop() {
        return getSdkVersion() >= Build.VERSION_CODES.LOLLIPOP;
    }
    /** Determine if the device is running API level 23 or higher. */
    public static boolean isMarshmallow() {
        return getSdkVersion() >= Build.VERSION_CODES.M;
    }

    /**
     * Main public method to get the compatibility class
     */
    public static Compat getCompat() {
        return getInstance().mCompat;
    }

    public static synchronized CompatHelper getInstance() {
        if (sInstance == null) {
            sInstance = new CompatHelper();
        }
        return sInstance;
    }

    public static boolean isChromebook() {
        return "chromium".equalsIgnoreCase(Build.BRAND) || "chromium".equalsIgnoreCase(Build.MANUFACTURER)
                || "novato_cheets".equalsIgnoreCase(Build.DEVICE);
    }

    public static boolean isKindle() {
        return "amazon".equalsIgnoreCase(Build.BRAND) || "amazon".equalsIgnoreCase(Build.MANUFACTURER);
    }

    public static boolean hasKanaAndEmojiKeys() {
        return KeyCharacterMap.deviceHasKey(94) && KeyCharacterMap.deviceHasKey(95);
    }

    public static boolean hasScrollKeys() {
        return KeyCharacterMap.deviceHasKey(92) || KeyCharacterMap.deviceHasKey(93);
    }
}
