package DSLStatic.Shape

import DSLStatic.ShapeAttributeException
import DSLStatic.Style.{Clear, Color, ColorRGB, ColorStyle, Fill, Gradient, Stroke, Style}
import org.scalajs.dom.CanvasRenderingContext2D

case class Circle(from : (Double, Double), s: Int , o : Double, radiusv: Double) extends Shape {
  override var opacity: Double = if(o >= 0) o else throw new ShapeAttributeException("Opacity cannot be smaller than 0")
  override var style : Style =  if(s == 1) new Fill else if (s == 2) new Stroke else null
  override var x : Double = if(from._1 >= 0) from._1 else throw new ShapeAttributeException("x cannot be smaller than 0")
  override var y : Double = if(from._2 >= 0) from._2 else throw new ShapeAttributeException("y cannot be smaller than 0")
  override var size: Int = _
  override var rotation: Double = 0
  override var isMirror: Boolean = false
  var radius : Double = if(radiusv >= 0) radiusv else throw new ShapeAttributeException("Radius cannot be smaller than 0")
  override val rangeSize: Double = getSize()
  override var  vx : Double = 0
  override var vy : Double = 0

  override def getSize(): Double ={
    radius
  }

  def this(from : (Double, Double), s : Int, o : Double, rad: Double, ct : ColorRGB) {
    this(from, s , o, rad)
    this.style.colorStyle = ct
  }

  def this(from : (Double, Double), s : Int, o : Double, rad: Double, ct : Gradient) {
    this(from, s , o, rad)
    this.style.colorStyle = ct
  }

  def apply(w: Double): Unit = {
    radius = w
  }

  def apply(w: Stroke): Unit = {
    style = w
  }

  def apply(w: Fill): Unit = {
    style = w
  }

  override def draw(ctx: CanvasRenderingContext2D): Unit = {
    style match {
      case _: Stroke =>
        Shape.checkColor(this,ctx)
        Shape.checkOpacity(this,ctx)
        ctx.rotate(rotation * Math.PI / 180)
        ctx.beginPath()
        ctx.arc(x, y, radius, 0, 2 * Math.PI)
        ctx.stroke()
      case _: Fill =>
        Shape.checkColor(this,ctx)
        Shape.checkOpacity(this,ctx)
        ctx.rotate(rotation * Math.PI / 180)
        ctx.beginPath()
        ctx.arc(x, y, radius, 0, 2 * Math.PI)
        ctx.fill()
      case _ =>
    }
  }
}
