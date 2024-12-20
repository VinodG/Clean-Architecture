package com.clean.data.mapper

import com.clean.domain.util.Mapper
import com.clean.domain.model.Meaning
import com.clean.network.model.MeaningDto
import javax.inject.Inject

class MeaningDtoMapper @Inject constructor(private val definitionMapper: DefinitionDtoMapper) :
    Mapper<MeaningDto, Meaning> {
    override fun map(dto: MeaningDto): Meaning {
        return Meaning(
            partOfSpeech = dto.partOfSpeech.orEmpty(),
            definitions = dto.definitions?.map { definitionMapper.map(it) }.orEmpty()
        )
    }
}