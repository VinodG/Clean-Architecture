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
                addOnNonEmptyString(word.word, Line.Type.Word, list)
                addOnNonEmptyString(word.phonetic, Line.Type.Phonetic, list)
                word.meanings.forEach { meaning ->
                    addOnNonEmptyString(meaning.partOfSpeech, Line.Type.PartsOfSpeech, list)
                    meaning.definitions.forEach { definition ->
                        addOnNonEmptyString(definition.definition, Line.Type.Definition, list)
                        addOnNonEmptyString(definition.example, Line.Type.Example, list)
                    }
                }
            }
            list
        }
    }

    private fun addOnNonEmptyString(str: String, type: Line.Type, list: MutableList<Line>) {
        if (str.isNotEmpty()) {
            list.add(Line(str, type))
        }
    }
}

