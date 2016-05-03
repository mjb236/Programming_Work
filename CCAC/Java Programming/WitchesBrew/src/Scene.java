
import org.lgna.story.event.SceneActivationEvent;
import org.lgna.story.event.SceneActivationListener;
import org.lgna.story.*;
import org.lgna.common.EachInTogetherRunnable;
import static org.lgna.common.ThreadUtilities.doTogether;
import static org.lgna.common.ThreadUtilities.eachInTogether;

public class Scene extends SScene {

    private SGround ground = new SGround();
    private SCamera camera = new SCamera();
    private Witch witch = new Witch();
    private Cauldron cauldron = new Cauldron();

    public Scene() {
        super();
    }

    private void performCustomSetup() {
    }

    private void performGeneratedSetUp() {
        this.setAtmosphereColor(new Color(0.588, 0.886, 0.988));
        this.setFromAboveLightColor(Color.WHITE);
        this.setFromBelowLightColor(Color.BLACK);
        this.setFogDensity(0.0);
        this.setName("myScene");
        this.ground.setPaint(SGround.SurfaceAppearance.GRASS);
        this.ground.setOpacity(1.0);
        this.ground.setName("ground");
        this.ground.setVehicle(this);
        this.camera.setName("camera");
        this.camera.setVehicle(this);
        this.camera.setOrientationRelativeToVehicle(new Orientation(-0.00661143, 0.993199, 0.0718775, 0.0913563));
        this.camera.setPositionRelativeToVehicle(new Position(1.48, 1.94, -7.48));
        this.witch.setPaint(Color.WHITE);
        this.witch.setOpacity(1.0);
        this.witch.setName("witch");
        this.witch.setVehicle(this);
        this.witch.setOrientationRelativeToVehicle(new Orientation(0.0, -0.0859294, 0.0, 0.996301));
        this.witch.setPositionRelativeToVehicle(new Position(1.79, -0.00496, 1.32));
        this.witch.setSize(new Size(1.37, 1.49, 1.25));
        this.cauldron.setPaint(Color.WHITE);
        this.cauldron.setOpacity(1.0);
        this.cauldron.setName("cauldron");
        this.cauldron.setVehicle(this);
        this.cauldron.setOrientationRelativeToVehicle(new Orientation(0.0, 0.0, 0.0, 1.0));
        this.cauldron.setPositionRelativeToVehicle(new Position(-1.33, 0.0255, -2.38));
        this.cauldron.setSize(new Size(0.674, 0.487, 0.677));
    }

    private void initializeEventListeners() {
        this.addSceneActivationListener(new SceneActivationListener() {
            public void sceneActivated(final SceneActivationEvent e) {
                Scene.this.myFirstMethod();
            }
        });
    }

    protected void handleActiveChanged(final Boolean isActive, final Integer activationCount) {
        if (isActive) {
            if (activationCount == 1) {
                this.performGeneratedSetUp();
                this.performCustomSetup();
                this.initializeEventListeners();
            } else {
                this.restoreStateAndEventListeners();
            }
        } else {
            this.preserveStateAndEventListeners();
        }
    }

    public void myFirstMethod() {
        double witchDepth = witch.getDepth();
        double cauldronDepth = cauldron.getDepth();
        double personalSpace = (witchDepth/2.0) + (cauldronDepth/2.0);
        
        this.witch.say("I need to check on my brew!");
        this.witch.turnToFace(this.cauldron);
        this.witch.moveToward(this.cauldron, this.witch.getDistanceTo(this.cauldron)-personalSpace);
    }
}
