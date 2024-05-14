package core.entities

import java.time.LocalDateTime

/**
 * <p>Classe abstrata que representa uma entidade base.</p>
 * <p>Essa classe contém os atributos comuns a todas as entidades.</p>
 */
abstract class BaseEntity {

    LocalDateTime dateCreated

    LocalDateTime lastUpdated

    Boolean deleted = false

    static mapping = {
        tablePerHierarchy false
    }
}
