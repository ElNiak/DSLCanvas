package DSLStatic.Shape

import DSLStatic.Style.{Clear, Fill, Stroke, Style}

case class Text (X:Double, Y: Double, texte: String, SOX : Double , SOY : Double, SB : Double, SC : String, Font : String, strokeB : Boolean) extends Shape {
  override var opacity: Double = 1
  override var style: Style = new Fill
  override var x: Double = X
  override var y: Double = Y
  override var rotation: Double = 0
  var text : String = texte
  var soX : Double = SOX
  var soY : Double = SOY
  var sb : Double = SB
  var sc : String = SC
  var font : String = Font
  var stroke : Boolean = strokeB
  override var canRotate: Boolean = false
  override var size: Int = _

  def this(X:Double, Y: Double, texte: String, strokeB : Boolean, sizE : Int) {
    this(X,Y,texte,0,0,0,"000000",sizE.toString + "px Times New Roman", strokeB)
  }

  def apply(w: Stroke): Unit = {
    style = w
  }

  def apply(w: Fill): Unit = {
    style = w
  }

}
