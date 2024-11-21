import java.awt.event.KeyEvent;

import com.phase2.*;

public class GameTest {

    //@Test
    public void playerMovement() {

        // Constructors
        Handler handler = new Handler(null);

        Doug doug = new Doug(500, 500, ID.DOUG, handler);

        Game game = new Game();

        // Add Doug to the handler
        handler.addObject(doug);
        
        KeyInput keyInput = new KeyInput(handler, game);
        
        KeyEvent keyEventRight = new KeyEvent(game, 0, 0, 0, 0);

    }


}
