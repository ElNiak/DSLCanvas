package DSLPerso.Shape

import DSLPerso.ShapeAttributeException
import DSLPerso.Style.{Clear, Fill, Stroke, Style}
import org.scalajs.dom
import org.scalajs.dom.{CanvasRenderingContext2D, document, html}

case class Text (from : (Double, Double), texte: String, SOX : Double , SOY : Double, SB : Double, SC : String, Font : String, strokeB : Boolean) extends Shape {
  override var opacity: Double = 1
  override var style: Style = new Fill
  override var x : Double = if(from._1 >= 0) from._1 else throw new ShapeAttributeException("x cannot be smaller than 0")
  override var y : Double = if(from._2 >= 0) from._2 else throw new ShapeAttributeException("y cannot be smaller than 0")
  override var  vx : Double = 0
  override var vy : Double = 0
  override var ax: Double = 0
  override var ay: Double = 1
  override var id : String = ""
  override var rotation: Double = 0
  var text : String = texte
  var soX : Double = if(SOX >= 0) SOX else throw new ShapeAttributeException("shadow offset x cannot be smaller than 0")
  var soY : Double = if(SOY >= 0) SOY else throw new ShapeAttributeException("shadow offset y cannot be smaller than 0")
  var sb : Double = if(SB >= 0) SB else throw new ShapeAttributeException("shadow blur size cannot be smaller than 0")
  var sc : String = SC
  var font : String = Font
  var stroke : Boolean = strokeB
  override var size: Int = _
  override var isMirror: Boolean = false
  override val rangeSize: Double = getSize()

  def this(from : (Double, Double), texte: String, strokeB : Boolean, sizE : Int) {
    this(from,texte,0,0,0,"000000",sizE.toString + "px Times New Roman", strokeB)
  }

  def this(i : String, from : (Double, Double), texte: String, strokeB : Boolean, sizE : Int) {
    this(from,texte,0,0,0,"000000",sizE.toString + "px Times New Roman", strokeB)
    this.id = i
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

  override def draw(ctx: CanvasRenderingContext2D): Unit = {
    style match {
      case _: Fill =>
        Shape.checkColor(this,ctx)
        //Shape.checkOpacity(this,ctx)
        ctx.rotate(rotation * Math.PI / 180)
        ctx.shadowOffsetX = soX
        ctx.shadowOffsetY = soY
        ctx.shadowBlur = sb
        ctx.shadowColor = sc
        ctx.shadowOffsetX = soX
        ctx.font = font
        if(!stroke)
          ctx.fillText(text, x, y)
        else
          ctx.strokeText(text,x,y)
      case _ =>
    }
  }

}
