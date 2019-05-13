package DSLStatic

import DSLStatic.Color.Color

trait Shape extends CanvasyElement {
  var stroke: Stroke
  var color: String
  var x: Double
  var y: Double

  def size(size: Int): Unit

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


  def translateY(Y: Int): Unit = {
    y += Y
  }

  def translateX(X: Int): Unit = {
    x += X
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
  }
}