package commands

import storage.AbstractHumanCollection
import utils.PrintTypesEnum
import utils.CommandAnnotation


@CommandAnnotation("remove_lower", "Removes elements lower than given")
class RemoveAnyByImpactSeedCommand(private val impactSeed: Long) : SealedCommand {

    override fun execute(collection: AbstractHumanCollection): List<Pair<PrintTypesEnum, String>> {
        collection.removeAnyByImpactSeed(impactSeed)
        return listOf(Pair(PrintTypesEnum.INFO, "Elements removed"))
    }
}