package com.metinvandar.satellitesapp.common.extensions

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.metinvandar.satellitesapp.R
import com.metinvandar.satellitesapp.common.Result
import java.io.IOException


fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}


inline fun <reified T> Context.getObjectFromJson(jsonFileName: String): T {
    val json = readJsonAsset(jsonFileName)
    val type = object : TypeToken<T>() {}.type
    return Gson().fromJson(json, type)
}

@Throws(IOException::class)
fun Context.readJsonAsset(fileName: String): String {
    val inputStream = assets.open(fileName)
    val size = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    inputStream.close()
    return String(buffer, Charsets.UTF_8)
}

fun Context.alert(
    title: String,
    message: String,
    @StringRes positiveBtn: Int,
    @StringRes negativeBtn: Int,
    action: (() -> Unit)? = null
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveBtn) { _, _ -> action?.invoke() }
        .setNegativeButton(negativeBtn) { dialog, _ -> dialog.dismiss() }
        .show()
}

fun Fragment.showError(errorMessage: String, callback: (() -> Unit)? = null) {
    requireContext().alert(
        title = getString(R.string.error_title),
        message = errorMessage,
        positiveBtn = android.R.string.ok,
        negativeBtn = android.R.string.cancel,
        action = callback
    )
}
