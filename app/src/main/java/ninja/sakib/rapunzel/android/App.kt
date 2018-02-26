package ninja.sakib.rapunzel.android

import android.content.Context

/**
 * := Coded with love by Sakib Sami on 2/26/18.
 * := sakib.sami@pathao.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

var context: Context? = null

fun InitApp(ctx: Context) {
    context = ctx
}

fun GetGlobalAppContext(): Context? {
    return context
}
