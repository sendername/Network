package src.interf;

import flash.events.*;
import openfl.display.*;

/**
 * ...
 * @author Trollingchar
 */
class Panel extends Sprite
{

	public function new() 
	{
		super();
		var btn:Sprite;
			
		btn = new TextBox("TEST");
		btn.x = 590;
		btn.y = 200;
		addChild(btn);
		
		btn = new Button("Вход", function(e:MouseEvent) {});
		btn.x = 350;
		btn.y = 200;
		addChild(btn);
		btn = new Button("Играть", function(e:MouseEvent) {});
		btn.x = 350;
		btn.y = 100;
		addChild(btn);
		btn = new Button("Экран", function(e:MouseEvent) {
			if (stage.displayState == StageDisplayState.FULL_SCREEN_INTERACTIVE)			
				stage.displayState = StageDisplayState.NORMAL;
			else
				stage.displayState = StageDisplayState.FULL_SCREEN_INTERACTIVE;				
		});
		btn.x = 350;
		btn.y = 300;
		addChild(btn);
	}
	
}