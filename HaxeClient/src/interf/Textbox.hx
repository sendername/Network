package interf;

/**
 * ...
 * @author 
 */
class TextBox
{

	public function new() 
	{
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
		textfield.text = s;
		textfield.embedFonts = true;
		textfield.y = 25;
		textfield.autoSize = TextFieldAutoSize.CENTER;
	}
	
}