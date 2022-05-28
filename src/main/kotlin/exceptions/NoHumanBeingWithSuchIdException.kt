package exceptions

class NoHumanBeingWithSuchIdException(id: Long): RuntimeException("No HumanBeing with ID=$id in collection") {

}