package sut.game01.core.character;


import static playn.core.PlayN.*;
import playn.core.Image;
import playn.core.*;
import playn.core.Keyboard;
import playn.core.ImageLayer;
import playn.core.ImageLayer;
import playn.core.Mouse;
import playn.core.PlayN;
import tripleplay.game.Screen;
import react.UnitSlot;
import tripleplay.game.UIScreen;
import tripleplay.game.ScreenStack;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;
import static playn.core.PlayN.graphics;
import sut.game01.core.sprite.*;
import playn.core.util.*;




public class Zealot   {
	private Sprite sprite;
	private int spriteIndex = 0;
	private boolean hasLoaded = false;
    private int action =0;
	public enum State {
		IDLE, RUN, ATTK
	};

	private State state = State.IDLE;

	private int e = 0;
	private int offset = 0;

	public Zealot(final float x, final float y){
        //int action =0;
		PlayN.keyboard().setListener(new Keyboard.Adapter(){	
			@Override
			public void onKeyUp(Keyboard.Event event){
                if (event.key() == Key.SPACE) {
                    action = 0;
                    switch (state){
                        case IDLE: if(action == 0){state = State.ATTK;} break;
                        //case RUN: state = State.ATTK; break;
                        case ATTK: state = State.IDLE; break;
                    }
                }

            }

            public void onKeyDown(Keyboard.Event event){
                if (event.key() == Key.SPACE) {
                    action = 1;
                    switch (state){
                        case IDLE:if(action == 1){ state = State.ATTK;} break;
                       // case RUN: state = State.ATTK; break;
                        case ATTK: state = State.IDLE; break;
                    }
                }

            }
		});

		sprite = SpriteLoader.getSprite("images/zealot.json");
		sprite.addCallback(new Callback<Sprite>(){
			@Override
			public void onSuccess(Sprite result){
				sprite.setSprite(spriteIndex);
				sprite.layer().setOrigin(sprite.width() /2f,
										 sprite.height() /2f);
				sprite.layer().setTranslation(300, 225);
				hasLoaded = true;
			}
			@Override
			public void onFailure(Throwable cause){
				PlayN.log().error("Error loading image!", cause);
			}

		});
		/*sprite.layer().addListener(new Pointer.Adapter(){
			@Override
			public void onPointerEnd(Pointer.Event event) {
				state = State.ATTK;
				spriteIndex =
			}
		});*/
	}
	public Layer layer() {
		return sprite.layer();
	}
	
	public void update(int delta) {
		if (hasLoaded == false) return;
        System.out.println(action);
		e = e +delta;
		if (e > 150) {
			switch(state){
				case IDLE: offset =0; break;
				case RUN:  offset =4; break;
				case ATTK: offset =8; break;
			}
		
			spriteIndex = offset + ((spriteIndex +1 ) %4);
			sprite.setSprite(spriteIndex);
			e = 0;
		}
	}
	
  
  }


 
