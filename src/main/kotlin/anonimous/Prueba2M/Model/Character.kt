package anonimous.Prueba2M.Model

import jakarta.persistence.*

@Entity
@Table(name = "characters")
class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    var id: Long? = null

    @Column(name = "name_character", nullable = false)
    var nameCharacter: String? = null

    @Column(name = "cost")
    var cost: Int? = null

    @Column(name = "actor")
    var actor: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scene_id", nullable = false)
    var scene: Scenes? = null
}
