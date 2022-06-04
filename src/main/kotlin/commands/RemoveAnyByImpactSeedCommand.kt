package commands

import storage.HumanCollectionInterface
import utils.PrintTypesEnum
import utils.CommandAnnotation

@kotlinx.serialization.Serializable
@CommandAnnotation("remove_lower_by_impact_seed", "Removes elements lower than given", "rlbis")
class RemoveAnyByImpactSeedCommand(private val impactSeed: Long) : SealedCommand {

    override fun execute(collection: HumanCollectionInterface): List<Pair<PrintTypesEnum, String>> {
        collection.removeAnyByImpactSeed(impactSeed)
        return listOf(Pair(PrintTypesEnum.INFO, "Elements removed"))
    }
}