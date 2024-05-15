package core.exceptions

class EntityWithSamePropertyAlreadyExistsException extends DomainException {
    public EntityWithSamePropertyAlreadyExistsException(String message) {
        super(message)
    }
}
