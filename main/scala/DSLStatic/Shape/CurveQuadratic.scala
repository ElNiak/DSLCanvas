package DSLStatic.Shape
import DSLStatic.Style.{Clear, Fill, Stroke, Style}

import scala.collection.mutable.ListBuffer

case class CurveQuadratic(from : (Double, Double), to : (Double, Double), s: Int, o : Double, cp1 : (Double, Double)) extends Shape {
  override var opacity: Double = o
  override var style : Style =  if(s == 1) new Fill else if (s == 2) new Stroke else new Clear
  override var x : Double = from._1
  override var y : Double = from._2
  var tx : Double = to._1
  var ty : Double = to._2
  override var size: Int = _
  override var rotation: Double = 0
  override var isMirror: Boolean = false
  var cp1x : Double = cp1._1
  var cp1y : Double = cp1._2
  var coordinates = ListBuffer[(Double, Double)]((x,y),(tx,ty),(cp1x,cp1y))
  override val rangeSize = getSize()

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
    if(maxX - minX  > maxY - minY)  (maxX - minX) * 2 else  (maxY - minY) * 2
  }
}
