package ninja.sakib.rapunzel.android.services

import android.content.Context
import ninja.sakib.rapunzel.android.GetGlobalAppContext

/**
 * := Coded with love by Sakib Sami on 2/26/18.
 * := sakib.sami@pathao.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

val appPreferenceName = "rapunzel-blog"
val prefKeyAccessToken = "access_token"

fun saveSession(token: String): Boolean {
    val preference = GetGlobalAppContext()!!.getSharedPreferences(appPreferenceName, Context.MODE_PRIVATE)
    return preference!!.edit().putString(prefKeyAccessToken, token).commit()
}

fun hasSession(): Boolean {
    val preference = GetGlobalAppContext()!!.getSharedPreferences(appPreferenceName, Context.MODE_PRIVATE)
    return preference!!.getString(prefKeyAccessToken, "").isNotEmpty()
}
