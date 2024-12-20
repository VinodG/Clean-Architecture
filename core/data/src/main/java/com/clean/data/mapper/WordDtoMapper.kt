package com.clean.data.mapper

import com.clean.domain.util.Mapper
import com.clean.domain.model.Word
import com.clean.network.model.WordDto
import javax.inject.Inject

class WordDtoMapper @Inject constructor(
    private val meaningDtoMapper: MeaningDtoMapper
) : Mapper<WordDto, Word> {
    override fun map(dto: WordDto): Word {
        return Word(
            word = dto.word.orEmpty(),
            phonetic = dto.phonetic.orEmpty(),
            meanings = dto.meanings?.map { meaningDtoMapper.map(it) }.orEmpty()
        )
    }
}