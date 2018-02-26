package ninja.sakib.rapunzel.android.services

import android.content.Context
import ninja.sakib.rapunzel.android.GetGlobalAppContext
import ninja.sakib.rapunzel.android.proto.Session

/**
 * := Coded with love by Sakib Sami on 2/26/18.
 * := sakib.sami@pathao.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

val appPreferenceName = "rapunzel-blog"
val prefKeyAccessToken = "access_token"
val prefKeyRefreshToken = "refresh_token"

fun saveSession(session: Session): Boolean {
    val preference = GetGlobalAppContext()!!.getSharedPreferences(appPreferenceName, Context.MODE_PRIVATE)
    return preference!!.edit().putString(prefKeyAccessToken, session.accessToken).commit()
            && preference.edit().putString(prefKeyRefreshToken, session.refreshToken).commit()
}

fun hasSession(): Boolean {
    val preference = GetGlobalAppContext()!!.getSharedPreferences(appPreferenceName, Context.MODE_PRIVATE)
    return preference!!.getString(prefKeyAccessToken, "").isNotEmpty()
}
