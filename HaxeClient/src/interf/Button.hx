package src.interf;

import flash.events.*;
import flash.filters.*;
import flash.text.*;
import format.*;
import openfl.*;
import openfl.display.*;

/**
 * ...
 * @author 
 */
class Button extends Sprite
{
	var tf:TextFormat;
	var textfield:TextField;
	
	public function new(s:String, func:MouseEvent->Void) 
	{
		super();
		
		// button
		var svg:SVG = new SVG(Assets.getText("img/button2.svg"));
		var shape:Shape = new Shape();
		svg.render(shape.graphics);
		addChild(shape);
		
		// text
		tf = new TextFormat(Assets.getFont("font/Jura-Medium.ttf").fontName, 42, 0x889999);
		tf.align = TextFormatAlign.CENTER;
		
		textfield = new TextField();
		textfield.width = 300;
		textfield.height = 100;
		textfield.defaultTextFormat = tf;
		textfield.selectable = false;
		textfield.text = s;
		textfield.embedFonts = true;
		textfield.y = 25;
		textfield.autoSize = TextFieldAutoSize.CENTER;
		
		addChild(textfield);		
		addEventListener(MouseEvent.MOUSE_OVER, MouseOver);
		addEventListener(MouseEvent.MOUSE_OUT, MouseOut);
		addEventListener(MouseEvent.CLICK, func);		
	}
	
	function MouseOut(event:MouseEvent)
	{
		textfield.textColor = 0x889999;
		
		var filt = textfield.filters;
		filt.pop();
		textfield.filters = filt;
	}
	
	function MouseOver(event:MouseEvent)
	{		
		textfield.textColor = 0xFFFFFF;
		
		var filt = textfield.filters;
		filt.push(new GlowFilter(0xFFFF00));
		textfield.filters = filt;
	}
}