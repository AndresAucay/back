package anonimous.Prueba2M.service

import anonimous.Prueba2M.Model.Character
import anonimous.Prueba2M.repository.CharacterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CharacterService {
    @Autowired
    lateinit var characterRepository: CharacterRepository

    fun list(): List<Character> {
        return characterRepository.findAll()
    }

    fun save(character: Character): Character {
        try {
            return characterRepository.save(character)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        }
    }

    fun update(character: Character): Character {
        try {
            characterRepository.findById(character.id)
                ?: throw Exception("ID no existe")

            return characterRepository.save(character)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(character: Character): Character {
        try {
            val response = characterRepository.findById(character.id)
                ?: throw Exception("ID no existe")
            response.apply {
                nameCharacter = character.nameCharacter
            }
            return characterRepository.save(response)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun listById(id: Long?): Character? {
        return characterRepository.findById(id)
    }

    fun delete(id: Long?): Boolean? {
        try {
            val response = characterRepository.findById(id)
                ?: throw Exception("ID no existe")
            characterRepository.deleteById(id!!)
            return true
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }
}
