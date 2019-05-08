package DSLStatic.Shape

import DSLStatic.Style.{Clear, Color, ColorRGB, ColorStyle, Fill, Gradient, Stroke, Style}
import org.scalajs.dom.CanvasRenderingContext2D

case class Rectangle(from : (Double, Double), widthI: Double, heightI: Double, s : Int, o : Double) extends Shape {
  override var opacity: Double = o
  override var style : Style = if(s == 1) new Fill else if (s == 2) new Stroke else new Clear
  override var x : Double = from._1
  override var y : Double = from._2
  override var  vx : Double = 0
  override var vy : Double = 0
  override var rotation: Double = 0
  override var isMirror: Boolean = false
  var height  = heightI
  var width = widthI
  override val rangeSize = getSize
  override var size: Int = _

  def this(from : (Double, Double), widthI: Double, heightI: Double, s : Int, o : Double, ct : ColorRGB) {
    this(from, widthI, heightI, s , o)
    this.style.colorStyle = ct
  }

  def this(from : (Double, Double), widthI: Double, heightI: Double, s : Int, o : Double, ct : Gradient) {
    this(from, widthI, heightI, s , o)
    this.style.colorStyle = ct
  }

  def apply(w: Stroke): Unit = {
    style = w
  }

  def apply(w: Fill): Unit = {
    style = w
  }

  override def getSize(): Double = {
    if(rotation == 0) {
      if(width > height) width else height
    }
    else {
      if(rotation <= 45 && rotation >= -45)
        if(width > height) width + width/2 * Math.cos(Math.abs(rotation)*Math.PI/180) else height + height/2 * Math.cos(Math.abs(rotation)*Math.PI/180)
      else
      if(width > height) width - width/2 * Math.cos(Math.abs(rotation)*Math.PI/180) else height - height/2 * Math.cos(Math.abs(rotation)*Math.PI/180)
    }
  }

  override def draw(ctx: CanvasRenderingContext2D): Unit = {
    style match {
      case _: Stroke =>
        Shape.checkColor(this,ctx)
        Shape.checkOpacity(this,ctx)
        if(rotation != 0) {
          ctx.translate(x+ width/2, y+height/2)
          ctx.rotate(rotation * Math.PI / 180)
          ctx.strokeRect(-width/2,-height/2, width, height)
        }
        else {
          ctx.strokeRect(x,y,width,height)
        }
      case _: Fill =>
        Shape.checkColor(this,ctx)
        Shape.checkOpacity(this,ctx)
        if(rotation != 0) {
          ctx.translate(x+ width/2, y+height/2)
          ctx.rotate(rotation * Math.PI / 180)
          ctx.fillRect(-width/2,-height/2, width, height)
        }
        else {
          ctx.fillRect(x,y,width,height)
        }
      case _ =>
    }
  }
}
