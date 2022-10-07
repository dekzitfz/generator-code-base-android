package <%= package %>.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import <%= package %>.di.scopes.ApplicationContext
import javax.inject.Inject
import android.content.SharedPreferences
import javax.inject.Singleton


@Singleton
class PreferencesHelper @Inject
constructor(@ApplicationContext context: Context) {

    private val mPref: SharedPreferences

    init {
        mPref = context.getSharedPreferences("PREF", MODE_PRIVATE)
    }

    fun getBoolean(key: String): Boolean {
        return mPref.getBoolean(key, false)
    }

    fun putString(key: String, value: String) {
        val editor = mPref.edit()
        editor.putString(key, value)
        editor.apply()

    }

    fun getString(key: String): String? {
        return mPref.getString(key, "")
    }

    fun getString(key: String, defaultValue: String): String? {
        return mPref.getString(key, defaultValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        val editor = mPref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun putInt(key: String, value: Int) {
        val editor = mPref.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String): Int {
        return mPref.getInt(key, 0)
    }

    fun clear() {
        mPref.edit().clear().apply()
    }

}