package DSLStatic.Shape

import DSLStatic.Style.{Clear, Color, ColorRGB, ColorStyle, Fill, Gradient, Stroke, Style}
import org.scalajs.dom.CanvasRenderingContext2D

import scala.collection.mutable.ListBuffer


case class TriangleRectangle(from : (Double, Double), A: Double, B: Double, s: Int, o : Double) extends Triangle {
  override var a = (from._1, from._2)
  override var b = (from._1 + A, from._2)
  override var c = (from._1, from._2 + B)
  override var opacity: Double = o
  override var style : Style = if(s == 1) new Fill else if (s == 2) new Stroke else new Clear
  override var x: Double = from._1
  override var y: Double = from._2
  override var  vx : Double = 0
  override var vy : Double = 0
  override var size: Int = _
  override var rotation: Double = 0
  override var isMirror: Boolean = false
  override val rangeSize = getSize()

  def this(from : (Double, Double), A: Double, B: Double, s : Int, o : Double, ct : ColorRGB) {
    this(from, A, B, s , o)
    this.style.colorStyle = ct
  }

  def this(from : (Double, Double), A: Double, B: Double, s : Int, o : Double, ct : Gradient) {
    this(from, A, B, s , o)
    this.style.colorStyle = ct
  }

  override def getSize(): Double ={
    val minX = from._1
    val minY = from._2
    val maxX = if(from._1+A+from._2 > from._2+B+from._1) from._1 + A else from._1
    val maxY = if(from._1+A+from._2 > from._2+B+from._1) from._2  else from._2 + B
    if(maxX - minX  > maxY - minY)  (maxX - minX) * 1.15 else  (maxY - minY) * 1.15
  }

  override def draw(ctx: CanvasRenderingContext2D): Unit = {
    style match {
      case _: Stroke =>
        Shape.checkColor(this,ctx)
        Shape.checkOpacity(this,ctx)
        if(rotation != 0) {
          ctx.moveTo((a._1 + b._1 + c._1)/3, (a._2 + b._2 + c._2)/3)
          ctx.rotate(rotation * Math.PI / 180)
        }
        ctx.lineCap = "round"
        ctx.beginPath()
        ctx.moveTo(a._1, a._2)
        ctx.lineTo(b._1, b._2)
        ctx.lineTo(c._1, c._2)
        ctx.lineTo(a._1, a._2)
        ctx.stroke()
      case _: Fill =>
        Shape.checkColor(this,ctx)
        Shape.checkOpacity(this,ctx)
        if(rotation != 0) {
          ctx.moveTo((a._1 + b._1 + c._1)/3, (a._2 + b._2 + c._2)/3)
          ctx.rotate(rotation * Math.PI / 180)
        }
        ctx.lineCap = "round"
        ctx.beginPath()
        ctx.moveTo(a._1, a._2)
        ctx.lineTo(b._1, b._2)
        ctx.lineTo(c._1, c._2)
        ctx.lineTo(a._1, a._2)
        ctx.fill()
      case _ =>
    }
  }
}
