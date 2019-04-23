package DSLStatic

import DSLStatic.Color.Color

trait Shape{
  var stroke : Stroke
  var color : Color
  var x : Int
  var y : Int
  var canvasyElement : CanvasyElement


  def stroke(col : Color.Value) : Unit
  def stroke(w : Int) : Unit
  def and(shape: Shape) : Shape
  def and(groups: Groups) : Groups
  def translateY(Y : Int) : Unit = {y += Y}
  def translateX(X : Int) : Unit = {x += X}

  implicit class arrayShape(group: Array[Circle]) {
    def translateY(Y: Int) : Unit = {group foreach(_ translateY(Y))}
    def translateX(X: Int) : Unit = {group foreach(_ translateX(X))}
  }

object Shape {
  def and(shape: Shape) = {

  }

  def change(shape: Shape) = {

  }

  def change(canvasyElement: CanvasyElement) = {

  }
}
