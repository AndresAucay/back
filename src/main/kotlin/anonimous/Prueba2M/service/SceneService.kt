package anonimous.Prueba2M.service

import anonimous.Prueba2M.Model.Scenes
import anonimous.Prueba2M.repository.MoviesRepository
import anonimous.Prueba2M.repository.ScenesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SceneService {
    @Autowired
    lateinit var scenesRepository: ScenesRepository
    @Autowired

    lateinit var moviesRepository: MoviesRepository


    fun list(): List<Scenes> {
        return scenesRepository.findAll()
    }


    fun save(scenes: Scenes): Scenes {
        try {
            // Fetch the movie to validate
            val movie = moviesRepository.findById(scenes.movieId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found")

            // Get the total minutes of the movie
            val totalMinutes = movie.timeMovies?.toInt() ?: 0

            // Get all scenes related to the movie
            val existingScenes = scenesRepository.findAllByMovieId(scenes.movieId)

            // Calculate the total minutes of existing scenes plus the new scene
            val totalSceneMinutes = existingScenes.sumOf { it.minutes ?: 0 } + (scenes.minutes ?: 0)

            // Check if the total scene minutes exceed the movie's total minutes
            if (totalSceneMinutes > totalMinutes) {
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Total minutes of scenes exceed the total movie time.")
            }

            // Save the new scene
            return scenesRepository.save(scenes)
        } catch (ex: Exception) {
            // Handle exceptions and return a BAD_REQUEST status with the error message
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        }
    }



    fun update(scenes: Scenes): Scenes {
        try {
            scenesRepository.findById(scenes.id)
                ?: throw Exception("ID no existe")

            return scenesRepository.save(scenes)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(scenes: Scenes): Scenes {
        try {
            val response = scenesRepository.findById(scenes.id)
                ?: throw Exception("ID no existe")
            response.apply {
                nameScene = scenes.nameScene
            }
            return scenesRepository.save(response)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun listById(id: Long?): Scenes? {
        return scenesRepository.findById(id)
    }

    fun delete(id: Long?): Boolean? {
        try {
            val response = scenesRepository.findById(id)
                ?: throw Exception("ID no existe")
            scenesRepository.deleteById(id!!)
            return true
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }
}
