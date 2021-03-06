package de.tu_darmstadt.gdi1.gorillas.ui.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.slick.BasicTWLGameState;
import de.matthiasmann.twl.slick.RootPane;
import de.tu_darmstadt.gdi1.gorillas.main.Gorillas;
import de.tu_darmstadt.gdi1.gorillas.util.MusicPlayer;
import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.ANDEvent;
import eea.engine.event.basicevents.KeyPressedEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;

/**
 * AboutState
 * 
 * @author Deniz Tobias Buruncuk, Dennis Hasenstab, Philip Stauder, Marcel Dieter
 * @version 1.0
 */
public class InstructionState extends BasicTWLGameState {
	
	private int stateID;
	private StateBasedEntityManager entityManager;
	
	private Label instruction_Label;

	/**
	 * The constructor. Creates a new state.
	 * 
	 * @param sid  
	 * 				this state's id. it can be identified by it and is unique!
	 */
	public InstructionState(int sid) {
		stateID = sid;
		entityManager = StateBasedEntityManager.getInstance();
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// Creating required Entities
		// <---
		Entity background = new Entity("aboutBack");
		Entity escListener = new Entity("ESC_Listener");
    	Entity zur�ck_Entity = new Entity("Zur�ck");
		// --->

		// Giving the Entities a picture.... If we aren't testing!
		// <---
    	if (!Gorillas.data.guiDisabled) { // really.... 
        	background.addComponent(new ImageRenderComponent(new Image("assets/gorillas/backgrounds/backgroundInstruction.png")));
        	zur�ck_Entity.addComponent(new ImageRenderComponent(new Image("assets/gorillas/button.png")));
    	}
		// --->

		// Setting the Entities positions!
		// <---
		background.setPosition(new Vector2f(400,300));
    	zur�ck_Entity.setPosition(new Vector2f(400, 450));
		// --->

		// Scaling the Entities pictures!
		// <---
    	zur�ck_Entity.setScale(0.18f);
		// --->

		// Creating the Events for all buttons and keylisteners!
		// <---
    	KeyPressedEvent escPressed = new KeyPressedEvent(Input.KEY_ESCAPE);
    	ANDEvent mainEvents_z = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
    	// --->

    	// Creating and adding the Actions!
    	// Care: One-line-actions are >literally< summarized as one-line-actions but given a comment on what they do.
    	// <--- Creating
    	Action zur�ck_Action = new ChangeStateAction(Gorillas.MAINMENUSTATE);
    		// Sound-action when a button is pressed :: SFX
    	Action buttonPressed = new Action() {@Override public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {MusicPlayer.playButton();}};
    	// ---> 
    	// <--- Adding
		escPressed.addAction(new ChangeStateAction(Gorillas.MAINMENUSTATE)); //
    	mainEvents_z.addAction(zur�ck_Action);
    	mainEvents_z.addAction(buttonPressed);
		// --->

		// Assigning the previously created Events to our Entities!
		// Note: A game would be very boring without events..
		// <---
		escListener.addComponent(escPressed);
    	zur�ck_Entity.addComponent(mainEvents_z);
    	// --->

    	// Finally: Adding all local created Entities into our game-wide entity manager!
    	// <---
        entityManager.addEntity(this.stateID, background);
		entityManager.addEntity(stateID, escListener);
    	entityManager.addEntity(this.stateID, zur�ck_Entity);
    	// --->
    	
    	// Fun-fact: This is identical to About, Congratulation and Highscore init but with another background picture...
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		entityManager.renderEntities(container, game, g);

		// Draw our menu and draw our menu and draw our menu and draw our menu and ...
		// <---
		g.drawString("Back", 370, 445);
		// --->
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		entityManager.updateEntities(container, game, delta);
	}

	@Override
	public int getID() {
		return stateID;
	} 
	
	@Override
	protected RootPane createRootPane() {
    	// Custom rootpane
		RootPane rp = super.createRootPane();

        // Creating label ...
        // <---
		instruction_Label = new Label("Do you remember the QBasic Game Gorilla? \n\nIt is the classic game, where two gorillas would fight to the death on top of skyscrapers. \n\nThrow explosive Bananas at your enemy! \n\nHowever, you have to find the right angle and the right speed to hit and kill him. \nPay extra attention to the wind! \n\nYou can play this game with two people only. \n\nIf you type in a positive number in the game-setup window,\nthe game ends once a gorilla reached that amount of points. \nIf you type in a negative number, you will play for that amount of rounds. \n\nChallenge your friends and become a master gorilla!");// --->
        // Finally: Adding the label to our rootpane ...
        // <---
    	rp.add(instruction_Label);
        // --->
    	
    	return rp;
	}
	
	@Override
	protected void layoutRootPane() {
        // Literally layout-ing our rootpane!
        // <---
		instruction_Label.setPosition(50, 200);
        // --->
	}

}