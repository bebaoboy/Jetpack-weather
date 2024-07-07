package com.bebaoboy.jetweatherapp.domain.mappers

interface ApiMapper<Domain, Entity> {
    fun mapToDomain(entity: Entity): Domain
}