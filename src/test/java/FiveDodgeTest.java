import edu.sdccd.cisc190.altscenes.FiveDodge;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FiveDodgeTest {
    private FiveDodge fiveDodge;

    @Before
    public void setUp() {
        Stage stage = new Stage(); // Mock Stage for testing
        fiveDodge = new FiveDodge(stage); // Create an instance of FiveDodge
    }

    @Test
    public void testInitialStats() {
        // Check initial conviction and madness
        assertEquals(0, fiveDodge.getConviction());
        assertEquals(0, fiveDodge.getMadness());
    }

    @Test
    public void testAttackWithWaterBottle() {
        // Simulate the player attacking with the water bottle
        fiveDodge.attackWithWaterBottle();

        // Check the updated stats
        assertEquals(1, fiveDodge.getConviction());
        assertEquals(-1, fiveDodge.getMadness()); // Assuming you decrement madness for a successful attack
    }

    @Test
    public void testDodgeAttack() {
        // Simulate the player dodging an attack
        fiveDodge.dodgeAttack();

        // Check the updated stats
        assertEquals(1, fiveDodge.getConviction());
        assertEquals(0, fiveDodge.getMadness()); // Assuming dodging does not affect madness
    }

    @Test
    public void testMadnessThreshold() {
        // Set madness to a high value
        fiveDodge.setMadness(8); // Assuming a setter exists for test purposes

        // Try dodging when madness is too high
        fiveDodge.dodgeAttack();

        // Verify that the madness increased
        assertEquals(8, fiveDodge.getMadness()); // Should remain the same due to failed dodge
    }

    @Test
    public void testSaveLoadGameData() {
        // Simulate saving game data
        fiveDodge.saveGameData();

        // Simulate loading game data
        fiveDodge.loadGameData();

        // Check if stats are correctly loaded
        assertEquals(1, fiveDodge.getConviction()); // Change according to your test case
        assertEquals(0, fiveDodge.getMadness()); // Change according to your test case
    }
}