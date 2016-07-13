package src.interf;

import flash.events.*;
import flash.display.Stage;
import openfl.display.*;

/**
 * ...
 * @author Trollingchar
 */
class Panel extends Sprite
{
	var st:Stage;

	public function new() 
	{
		super();
		addEventListener(Event.ADDED_TO_STAGE, Added);
		
		var btn:Sprite;
			
		btn = new TextBox("72");
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
	
	function Added(event:Event) {
		stage.addEventListener(Event.RESIZE, Resize);
	}
	
	function Resize(event:Event) {
		scaleX = stage.stageWidth / 1000;
		scaleY = stage.stageHeight / 600;
	}
	
}