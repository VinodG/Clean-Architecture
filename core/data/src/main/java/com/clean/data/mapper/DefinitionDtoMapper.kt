package com.clean.data.mapper

import com.clean.domain.model.Definition
import com.clean.domain.util.Mapper
import com.clean.network.model.DefinitionDto
import javax.inject.Inject

class DefinitionDtoMapper @Inject constructor() : Mapper<DefinitionDto, Definition> {
    override fun map(dto: DefinitionDto): Definition {
        return Definition(
            definition = dto.definition.orEmpty(),
            example = dto.example.orEmpty(),
        )
    }
}