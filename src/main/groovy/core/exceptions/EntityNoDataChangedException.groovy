package core.exceptions

class EntityNoDataChangedException extends Exception {
    public EntityNoDataChangedException() {
        super("Nenhum dado foi alterado!")
    }
}
