package com.metinvandar.satellitesapp.data.exception

import com.metinvandar.satellitesapp.R

class SatellitesNotFoundException: BaseException() {

    override fun getErrorMessageResId() = R.string.error_satellites_not_found_message
}
