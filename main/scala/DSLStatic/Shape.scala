package DSLStatic

import DSLStatic.Color.Color


trait Shape extends CanvasyElement {
  var stroke: Stroke
  var color: Color
  var x: Int
  var y: Int
  var size : Int

  def stroke[A](v : A) : Unit

  def size(s: Int): Unit = {
    size = s
  }

  def and(shape: Shape): Array[Shape] = {
    val array = new Array[Shape](2)
    array(this)
    array(shape)
    return array
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
