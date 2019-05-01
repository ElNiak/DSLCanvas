package DSLStatic.Shape

import DSLStatic.Style.{Clear, Fill, Stroke, Style}

case class Text (X:Double, Y: Double, texte: String, SOX : Double , SOY : Double, SB : Double, SC : String, Font : String) extends Shape {
  override var opacity: Double = 1
  override var style: Style = new Fill
  override var x: Double = X
  override var y: Double = Y
  var text : String = texte
  var soX : Double = SOX
  var soY : Double = SOY
  var sb : Double = SB
  var sc : String = SC
  var font : String = Font

  override var size: Int = _

  def this(X:Double, Y: Double, texte: String) {
    this(X,Y,texte,0,0,0,"000000","20px Times New Roman")
  }

  def apply(w: Stroke): Unit = {
    style = w
  }

  def apply(w: Fill): Unit = {
    style = w
  }

}
