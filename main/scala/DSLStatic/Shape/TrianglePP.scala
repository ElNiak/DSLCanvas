package DSLStatic.Shape

import DSLStatic.Style.{Clear, ColorRGB, Fill, Gradient, Stroke, Style}

import scala.collection.mutable.ListBuffer

case class TrianglePP( A : (Double, Double),B : (Double, Double),C : (Double, Double), s: Int, o : Double) extends Triangle {
  override var a: (Double, Double) = (A._1, A._2)
  override var b: (Double, Double) = (B._1, B._2)
  override var c: (Double, Double) = (C._1, C._2)
  override var opacity: Double = o
  override var style : Style = if(s == 1) new Fill else if (s == 2) new Stroke else new Clear
  override var x: Double = A._1
  override var y: Double = A._2
  override var size: Int = _
  override var rotation: Double = 0
  override var isMirror: Boolean = false
  var coordinates = ListBuffer[(Double, Double)](a,b,c)
  override val rangeSize = getSize()

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
}
