package src.interf;

import flash.events.Event;
import flash.events.MouseEvent;
import openfl.display.Sprite;

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
		btn = new Button("Экран", function(e:MouseEvent) {});
		btn.x = 350;
		btn.y = 300;
		addChild(btn);
	}
	
}