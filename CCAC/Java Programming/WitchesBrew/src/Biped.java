
import org.lgna.story.resources.BipedResource;
import org.lgna.story.*;
import org.lgna.common.EachInTogetherRunnable;
import static org.lgna.common.ThreadUtilities.doTogether;
import static org.lgna.common.ThreadUtilities.eachInTogether;

public class Biped extends SBiped {

    public Biped(final BipedResource resource) {
        super(resource);
    }
}
