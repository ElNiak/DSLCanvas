package DSLStatic.Shape

import DSLStatic.ShapeAttributeException
import DSLStatic.Style.SV.SValue
import DSLStatic.Style._
import org.scalajs.dom.CanvasRenderingContext2D

import scala.collection.mutable.ListBuffer

//Point to Point Lined shape
case class PPLShape(from : (Double, Double), s: SValue, o : Double, list: ListBuffer[(Double,Double)]) extends Shape {
  override var opacity: Double = if(o >= 0) o else throw new ShapeAttributeException("Opacity cannot be smaller than 0")
  override var style : Style =  if(s ==  SV.fill) new Fill else if (s == SV.stroke) new Stroke else null
  override var x : Double = if(from._1 >= 0) from._1 else throw new ShapeAttributeException("x cannot be smaller than 0")
  override var y : Double = if(from._2 >= 0) from._2 else throw new ShapeAttributeException("y cannot be smaller than 0")
  override var  vx : Double = 0
  override var vy : Double = 0
  override var size: Int = _
  override var rotation: Double = 0
  override var isMirror: Boolean = false
  val coordinates : ListBuffer[(Double,Double)]= list
  override val rangeSize: Double = getSize()


  def this(from : (Double, Double), s : SValue, o : Double, list: ListBuffer[(Double,Double)], ct : ColorRGB) {
    this(from, s , o, list)
    this.style.colorStyle = ct
  }

  def this(from : (Double, Double), widthI: Double, heightI: Double, s : SValue, o : Double,list: ListBuffer[(Double,Double)], ct : Gradient) {
    this(from, s , o, list)
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
    style match {
      case _: Stroke =>
        Shape.checkColor(this,ctx)
        Shape.checkOpacity(this,ctx)
        ctx.rotate(rotation * Math.PI / 180)
        ctx.translate(x, y)
        ctx.beginPath()
        ctx.moveTo(coordinates.head._1, coordinates.head._2)
        for(i <- 1 until coordinates.length) {
          ctx.lineTo(coordinates(i)._1, coordinates(i)._2)
        }
        ctx.lineTo(list.head._1, list.head._2)
        ctx.stroke()
      case _: Fill =>
        Shape.checkColor(this,ctx)
        Shape.checkOpacity(this,ctx)
        ctx.rotate(rotation * Math.PI / 180)
        ctx.translate(x, y)
        ctx.beginPath()
        ctx.moveTo(coordinates.head._1, coordinates.head._2)
        for(i <- 1 until coordinates.length) {
          ctx.lineTo(coordinates(i)._1, coordinates(i)._2)
        }
        ctx.lineTo(coordinates.head._1, coordinates.head._2)
        ctx.fill()
      case _ =>
    }
  }
}
