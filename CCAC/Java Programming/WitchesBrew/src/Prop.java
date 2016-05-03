
import org.lgna.story.resources.PropResource;
import org.lgna.story.*;
import org.lgna.common.EachInTogetherRunnable;
import static org.lgna.common.ThreadUtilities.doTogether;
import static org.lgna.common.ThreadUtilities.eachInTogether;

public class Prop extends SProp {

    public Prop(final PropResource resource) {
        super(resource);
    }
}
