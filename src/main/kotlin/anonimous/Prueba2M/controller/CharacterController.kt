package anonimous.Prueba2M.controller

import anonimous.Prueba2M.Model.Character
import anonimous.Prueba2M.service.CharacterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/characters")   // Endpoint
class CharacterController {
    @Autowired
    lateinit var characterService: CharacterService

    @GetMapping
    fun list(): List<Character> {
        return characterService.list()
    }

    @PostMapping
    fun save(@RequestBody character: Character): ResponseEntity<Character> {
        return ResponseEntity(characterService.save(character), HttpStatus.CREATED)
    }

    @PutMapping
    fun update(@RequestBody character: Character): ResponseEntity<Character> {
        return ResponseEntity(characterService.update(character), HttpStatus.OK)
    }

    @PatchMapping("/{id}")
    fun updateName(@PathVariable id: Long, @RequestBody name: String): ResponseEntity<Character> {
        val character = characterService.listById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found")
        character.nameCharacter = name
        return ResponseEntity(characterService.update(character), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById(@PathVariable("id") id: Long): ResponseEntity<Character> {
        val character = characterService.listById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found")
        return ResponseEntity(character, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Void> {
        return if (characterService.delete(id) == true) {
            ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity<Void>(HttpStatus.NOT_FOUND)
        }
    }
}
