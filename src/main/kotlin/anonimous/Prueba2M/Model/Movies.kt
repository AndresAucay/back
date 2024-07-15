package anonimous.Prueba2M.Model

import jakarta.persistence.*

@Entity
@Table(name = "movies")
class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    var id: Long? = null

    @Column(name = "title", nullable = false)
    var title: String? = null

    @Column(name = "time_movies")
    var timeMovies: String? = null

    @Column(name = "director")
    var director: String? = null
}
