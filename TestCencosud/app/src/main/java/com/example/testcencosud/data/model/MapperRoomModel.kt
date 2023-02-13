package com.example.testcencosud.data.model

import com.example.testcencosud.utils.Mapper

object MapperRoomModel : Mapper<Item, DetailMovie> {
    override fun DataModelToDomainModel(entity: Item): DetailMovie =
        DetailMovie(
            id = entity.id,
            contentRating = entity.contentRating,
            directors = entity.directors,
            genres = entity.genres,
            imDbRating = entity.imDbRating,
            image = entity.image,
            plot = entity.plot,
            releaseState = entity.releaseState,
            runtimeMins = entity.runtimeMins,
            stars = entity.stars,
            title = entity.title,
            year = entity.year

        )

    fun fromDataModel(initial: List<Item>) : List<DetailMovie> = initial.map {
        MapperRoomModel.DataModelToDomainModel(
            it
        )
    }

}