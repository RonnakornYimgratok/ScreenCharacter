package sut.game01.core.character;


import static playn.core.PlayN.*;
import playn.core.Image;
import playn.core.*;
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

	public enum State {
		IDLE, RUN, ATTK
	};

	private State state = State.IDLE;

	private int e = 0;
	private int offset = 4;

	public Zealot(final float x, final float y){
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

		e = e +delta;
		if (e > 150) {
			spriteIndex = offset + ((spriteIndex +1 ) %4);
			sprite.setSprite(spriteIndex);
			e = 0;
		}
	}
	
  
  }


 