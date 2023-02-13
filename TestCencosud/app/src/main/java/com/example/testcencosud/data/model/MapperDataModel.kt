package com.example.testcencosud.data.model

import com.example.testcencosud.domain.model.Movie
import com.example.testcencosud.utils.Mapper

object MapperDataModel : Mapper<Item, Movie> {
    override fun DataModelToDomainModel(entity: Item): Movie = Movie(
        id = entity.id,
        title = entity.title,
        dateRelease = entity.releaseState,
        urlImage = entity.image
    )

    fun fromDataModelList(initial: List<Item> ) : List<Movie>  =
        initial.map { DataModelToDomainModel(it) }

}