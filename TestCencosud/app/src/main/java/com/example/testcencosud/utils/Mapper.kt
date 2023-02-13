package com.example.testcencosud.utils



interface Mapper<Entity, DomainModel> {

    fun DataModelToDomainModel(entity: Entity) : DomainModel
}