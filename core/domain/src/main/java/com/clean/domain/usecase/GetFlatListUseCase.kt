package com.clean.domain.usecase

import com.clean.domain.model.Line
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFlatListUseCase @Inject constructor(private val getWordUseCase: GetWordUseCase) {
    suspend operator fun invoke(word: String): Flow<List<Line>> {
        return getWordUseCase(word).map { words ->
            val list = mutableListOf<Line>()
            words.forEach { word ->
                addOnNonEmpty(word.word, Line.Type.Word, list)
                addOnNonEmpty(word.phonetic, Line.Type.Phonetic, list)
                word.meanings.forEach { meaning ->
                    addOnNonEmpty(meaning.partOfSpeech, Line.Type.PartsOfSpeech, list)
                    meaning.definitions.forEach { definition ->
                        addOnNonEmpty(definition.definition, Line.Type.Definition, list)
                        addOnNonEmpty(definition.example, Line.Type.Example, list)
                    }
                }
            }
            list
        }
    }

    private fun addOnNonEmpty(str: String, type: Line.Type, list: MutableList<Line>) {
        if (str.isNotEmpty()) {
            list.add(Line(str, type))
        }
    }
}

