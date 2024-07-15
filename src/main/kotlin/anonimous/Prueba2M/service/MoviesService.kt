package anonimous.Prueba2M.service

import anonimous.Prueba2M.Model.Movies
import anonimous.Prueba2M.repository.MoviesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class MoviesService {
    @Autowired
    lateinit var moviesRepository: MoviesRepository

    fun list(): List<Movies> {
        return moviesRepository.findAll()
    }

    fun save(movies: Movies): Movies {
        try {
            return moviesRepository.save(movies)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun update(movies: Movies): Movies {
        try {
            moviesRepository.findById(movies.id)
                ?: throw Exception("ID no existe")

            return moviesRepository.save(movies)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(movies: Movies): Movies {
        try {
            val response = moviesRepository.findById(movies.id)
                ?: throw Exception("ID no existe")
            response.apply {
                title = movies.title
            }
            return moviesRepository.save(response)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun listById(id: Long?): Movies? {
        return moviesRepository.findById(id)
    }

    fun delete(id: Long?): Boolean? {
        try {
            val response = moviesRepository.findById(id)
                ?: throw Exception("ID no existe")
            moviesRepository.deleteById(id!!)
            return true
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }
}
