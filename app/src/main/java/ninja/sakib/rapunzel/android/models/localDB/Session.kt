package ninja.sakib.rapunzel.android.models.localDB

import ninja.sakib.pultusorm.annotations.PrimaryKey

/**
 * := Coded with love by Sakib Sami on 2/26/18.
 * := sakib.sami@pathao.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

class Session {
    @PrimaryKey
    var id: Int = 0
    var accessToken: String = ""
}
