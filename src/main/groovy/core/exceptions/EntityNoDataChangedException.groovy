package core.exceptions

import core.enums.AlertType

class EntityNoDataChangedException extends DomainException {
    public EntityNoDataChangedException() {
        super("As informações não foram alteradas ou informadas", AlertType.WARNING)
    }
}
