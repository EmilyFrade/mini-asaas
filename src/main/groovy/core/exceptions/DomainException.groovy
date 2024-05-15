package core.exceptions

import core.enums.AlertType

class DomainException extends RuntimeException {

    final AlertType alertType

    DomainException(String message) {
        super(message)
        this.alertType = AlertType.ERROR

    }

    DomainException(String message, AlertType alertType) {
        super(message)
        this.alertType = alertType
    }
}
