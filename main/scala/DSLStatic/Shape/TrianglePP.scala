package DSLStatic.Shape

import DSLStatic.ShapeAttributeException
import DSLStatic.Style.{Clear, ColorRGB, Fill, Gradient, Stroke, Style}
import org.scalajs.dom.CanvasRenderingContext2D

import scala.collection.mutable.ListBuffer

case class TrianglePP( A : (Double, Double),B : (Double, Double),C : (Double, Double), s: Int, o : Double) extends Triangle {
  override var a: (Double, Double) = if(A._1 >= 0 && A._2 >= 0) (A._1, A._2) else throw new ShapeAttributeException("(A,A) cannot be smaller than 0")
  override var b: (Double, Double) = if(B._1 >= 0 && B._2 >= 0) (B._1, B._2) else throw new ShapeAttributeException("(B,B) cannot be smaller than 0")
  override var c: (Double, Double) = if(C._1 >= 0 && C._2 >= 0) (C._1, C._2) else throw new ShapeAttributeException("(C,C) cannot be smaller than 0")
  override var opacity: Double = if(o >= 0) o else throw new ShapeAttributeException("Opacity cannot be smaller than 0")
  override var style : Style = if(s == 1) new Fill else if (s == 2) new Stroke else null
  override var x : Double = if(A._1 >= 0) A._1 else throw new ShapeAttributeException("x cannot be smaller than 0")
  override var y : Double = if(A._2 >= 0) A._2 else throw new ShapeAttributeException("y cannot be smaller than 0")
  override var  vx : Double = 0
  override var vy : Double = 0
  override var size: Int = _
  override var rotation: Double = 0
  override var isMirror: Boolean = false
  var coordinates: ListBuffer[(Double, Double)] = ListBuffer[(Double, Double)](a,b,c)
  override val rangeSize: Double = getSize()

  def this(A : (Double, Double),B : (Double, Double),C : (Double, Double), s: Int, o : Double, ct : ColorRGB) {
    this(A,B,C, s , o)
    this.style.colorStyle = ct
  }

  def this(A : (Double, Double),B : (Double, Double),C : (Double, Double), s: Int, o : Double, ct : Gradient) {
    this(A,B,C, s , o)
    this.style.colorStyle = ct
  }

  override def getSize(): Double ={
    var minX = Double.MaxValue
    var minY = Double.MaxValue
    var maxX = Double.MinValue
    var maxY = Double.MinValue
    for(shape <- coordinates){
      if(shape._1+shape._2 < minX + minY){
        minX = shape._1
        minY = shape._2
      }
      if(shape._1+shape._1 > maxX + maxY){
        maxX = shape._1
        maxY = shape._1
      }
    }
    if(maxX - minX  > maxY - minY)  (maxX - minX) * 1.15 else  (maxY - minY) * 1.15
  }

  override def draw(ctx: CanvasRenderingContext2D): Unit = {
    if(vx != 0 || vy != 0){
      a  = (x, y)
      b  = (B._1+x, B._2+y)
      c  = (C._1+x, C._2+y)
    }
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
