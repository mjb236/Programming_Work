
import org.lgna.story.resources.prop.CauldronResource;
import org.lgna.story.*;
import org.lgna.common.EachInTogetherRunnable;
import static org.lgna.common.ThreadUtilities.doTogether;
import static org.lgna.common.ThreadUtilities.eachInTogether;

public class Cauldron extends Prop {

    public Cauldron() {
        super(CauldronResource.CAULDRON);
    }
}
