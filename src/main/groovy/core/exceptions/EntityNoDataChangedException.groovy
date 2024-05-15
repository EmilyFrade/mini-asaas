package core.exceptions

class EntityNoDataChangedException extends Exception {
    public EntityNoDataChangedException() {
        super("As informações não foram alteradas ou informadas")
    }
}
