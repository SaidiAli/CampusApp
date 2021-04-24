package com.devhub.campus.utils

interface ModelMapper<Entity, Model> {
    fun mapEntityToModel(entity: Entity): Model

    fun mapModelToEntity(model: Model): Entity
}