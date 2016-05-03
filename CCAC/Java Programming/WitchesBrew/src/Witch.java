
import org.lgna.story.resources.biped.WitchResource;
import org.lgna.story.*;
import org.lgna.common.EachInTogetherRunnable;
import static org.lgna.common.ThreadUtilities.doTogether;
import static org.lgna.common.ThreadUtilities.eachInTogether;

public class Witch extends Biped {

    public Witch() {
        super(WitchResource.WITCH);
    }
}
