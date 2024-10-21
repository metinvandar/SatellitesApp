package com.metinvandar.satellitesapp.domain.mapper

import com.metinvandar.satellitesapp.common.extensions.safeGet
import com.metinvandar.satellitesapp.data.service.model.SatelliteData
import com.metinvandar.satellitesapp.domain.model.Satellite
import com.metinvandar.satellitesapp.domain.model.SatelliteStatus

class SatelliteMapper: Mapper<List<SatelliteData>, List<Satellite>>  {

    override fun mapFrom(data: List<SatelliteData>): List<Satellite> {
        return data.mapIndexed { index, item ->
            Satellite(
                id = item.id.safeGet(),
                status = SatelliteStatus.fromValue(item.active.safeGet()),
                name = item.name.safeGet(),
                hasDivider = index != data.size
            )
        }
    }
}
