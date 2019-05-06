package DSLStatic.Shape

import DSLStatic.Style.{ColorRGB, ColorStyle, Fill, Gradient, Stroke, Style}
import DSLStatic._

import scala.collection.mutable.ListBuffer

trait Shape extends CanvasyElement {
  var style : Style
  var opacity: Double
  var x: Double
  var y: Double
  var size : Int
  var rotation : Double
  var isMirror : Boolean
  val rangeSize : Double

  def size(s: Int): Unit = {
    size = s
  }

  def getSize(): Double

  override def change(x: CanvasyElementModifier[Shape]): Shape = {
    x.change(this)
    this
  }

  override def and(x: Shape): ListBuffer[Shape] = {
    ListBuffer(this, x)
  }

  override def and(x: CanvasyElementModifier[Shape]): Shape = {
     this.change(x)
  }


  def translateY(Y: Int): Shape = {
    y += Y
    this
  }

  def translateX(X: Int): Shape = {
    x += X
    this
  }

  def rotate(X: Double) : Shape = {
    if(X <= 90 && X >= -90)
      rotation = X
    else {
      val i : Int = (X/90).toInt
      if(X > 90)
        rotation = 90*i - X
      else
        rotation = 90*i + X
    }
    println(rotation)
    this
  }

  def apply(w: Style): Unit = {
    style = w
  }

}


object Shape {
  implicit class Group[I <: Shape](group: ListBuffer[I]) {
    def translateY(Y: Int) : ListBuffer[I] = {
      group foreach(_ translateY(Y))
      return group
    }
    def translateX(X: Int) : ListBuffer[I] = {
      group foreach(_ translateX(X))
      return group
    }
    def rotate(X: Double) : ListBuffer[I] = {
      group foreach(_ rotate(X))
      return group
    }
  }
}
