package anonimous.Prueba2M.repository

import anonimous.Prueba2M.Model.Character
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface CharacterRepository : JpaRepository<Character, Long?> {
    fun findById(id: Long?):Character?
    

}