package anonimous.Prueba2M.repository

import anonimous.Prueba2M.Model.Movies
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MoviesRepository : JpaRepository<Movies, Long?> {
    fun findById(id: Long?):Movies?

}