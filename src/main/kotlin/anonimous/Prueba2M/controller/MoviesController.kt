package anonimous.Prueba2M.controller

import anonimous.Prueba2M.Model.Movies
import anonimous.Prueba2M.service.MoviesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/peliculas")   //endpoint
class MoviesController {
    @Autowired
    lateinit var moviesService: MoviesService

    @GetMapping
    fun list ():List <Movies>{
        return moviesService.list()
    }
    @PostMapping
    fun save (@RequestBody movies: Movies):ResponseEntity<Movies>{
        return ResponseEntity(moviesService.save(movies), HttpStatus.OK)
    }
    @PutMapping
    fun update (@RequestBody movies: Movies):ResponseEntity<Movies>{
        return ResponseEntity(moviesService.update(movies), HttpStatus.OK)
    }
    @PatchMapping
    fun updateName (@RequestBody movies: Movies):ResponseEntity<Movies>{
        return ResponseEntity(moviesService.updateName(movies), HttpStatus.OK)
    }
    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*>{
        return ResponseEntity(moviesService.listById (id), HttpStatus.OK)

    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return moviesService.delete(id)
    }
}