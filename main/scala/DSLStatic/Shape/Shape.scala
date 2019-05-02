package DSLStatic.Shape

import DSLStatic.Style.{ColorRGB, ColorStyle, Fill, Gradient, Stroke, Style}
import DSLStatic._

trait Shape extends CanvasyElement {
  var style : Style
  var opacity: Double
  var x: Double
  var y: Double
  var size : Int
  var rotation : Double
  var movable : Boolean

  def size(s: Int): Unit = {
    size = s
  }

  override def change(x: CanvasyElementModifier[Shape]): Shape = {
    x.change(this)
    this
  }

  override def and(x: Shape): Array[Shape] = {
    Array(this, x)
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
    rotation = X
    this
  }

  def moveMouse(X: Boolean) : Shape = {
    movable = X
    this
  }
}


object Shape {
  implicit class Group[I <: Shape](group: Array[I]) {
    def translateY(Y: Int) : Array[I] = {
      group foreach(_ translateY(Y))
      return group
    }
    def translateX(X: Int) : Array[I] = {
      group foreach(_ translateX(X))
      return group
    }
    def rotate(X: Double) : Array[I] = {
      group foreach(_ rotate(X))
      return group
    }
    def moveMouse(X: Boolean) : Array[I] = {
      group foreach(_ moveMouse(X))
      return group
    }
  }
}
