package core.exceptions

class EntityNotFoundException extends DomainException {
    public EntityNotFoundException(String message) {
        super(message)
    }
}
