package;

import flash.text.*;
import format.*;
import openfl.*;
import flash.filters.GlowFilter;
import openfl.display.*;
import src.interf.*;

/**
 * ...
 * @author 
 */
class Main extends Sprite 
{

	public function new() 
	{
		super();		
				
		var btn:Button;
		btn = new Button("Test");
		btn.x = 350;
		btn.y = 200;
		addChild(btn);
		btn = new Button("Test");
		btn.x = 350;
		btn.y = 100;
		addChild(btn);
		btn = new Button("Test");
		btn.x = 350;
		btn.y = 300;
		addChild(btn);
	}

}