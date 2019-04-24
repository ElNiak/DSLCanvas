package DSLStatic

import DSLStatic.Color.Color

trait Shape {
  var stroke: Stroke
  var color: Color
  var x: Int
  var y: Int
  var canvasyElement: CanvasyElement


  def stroke(col: Color.Value): Unit

  def stroke(w: Int): Unit

  def and(shape: Shape): Shape

  def and(groups: Groups): Groups

  def translateY(Y: Int): Unit = {
    y += Y
  }

  def translateX(X: Int): Unit = {
    x += X
  }
}




object Shape {
  def and(shape: Shape) = {
    val array = new Array[Shape]()
  }

  implicit class Group(group: Array[Circle]) {

    def translateY(Y: Int) : Array[Circle] = {
      group foreach(_ translateY(Y))
      return group
    }
    def translateX(X: Int) : Array[Circle] = {
      group foreach(_ translateX(X))
      return group
    }
  }

  def change(shape: Shape) = {

  }

  def change(canvasyElement: CanvasyElement) = {

  }
}
