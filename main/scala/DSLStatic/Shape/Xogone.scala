package DSLStatic.Shape

import DSLStatic.Style.{Clear, ColorRGB, Fill, Gradient, Stroke, Style}

import scala.collection.mutable.ListBuffer

case class Xogone(X:Double, Y:Double, s: Int , o : Double, list: ListBuffer[(Double,Double)]) extends Shape {
  override var opacity: Double = o
  override var style : Style =  if(s == 1) new Fill else if (s == 2) new Stroke else new Clear
  override var x : Double = X
  override var y : Double = Y
  override var size: Int = _
  override var rotation: Double = 0
  val coordinates : ListBuffer[(Double,Double)]= list
  val rangeSize = getSize()

  def this(X:Double, Y: Double, s : Int, o : Double, list: ListBuffer[(Double,Double)], ct : ColorRGB) {
    this(X, Y, s , o, list)
    this.style.colorStyle = ct
  }

  def this(X:Double, Y: Double, widthI: Double, heightI: Double, s : Int, o : Double,list: ListBuffer[(Double,Double)], ct : Gradient) {
    this(X, Y, s , o, list)
    this.style.colorStyle = ct
  }

  def getSize(): (Double,Double,Double,Double) ={
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
    (minX,minY,maxX,maxY)
  }

}
