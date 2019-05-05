package DSLStatic.Shape

import DSLStatic.Style.{Clear, Fill, Stroke, Style}
import org.scalajs.dom
import org.scalajs.dom.{document, html}

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
  override var size: Int = _
  override var isMirror: Boolean = false
  override val rangeSize = getSize()

  def this(X:Double, Y: Double, texte: String, strokeB : Boolean, sizE : Int) {
    this(X,Y,texte,0,0,0,"000000",sizE.toString + "px Times New Roman", strokeB)
  }

  def apply(w: Stroke): Unit = {
    style = w
  }

  def apply(w: Fill): Unit = {
    style = w
  }

  override def getSize(): Double = {
    val c = document.createElement("canvas").asInstanceOf[html.Canvas]
    val ctx = c.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    ctx.measureText(text).width
  }

}
