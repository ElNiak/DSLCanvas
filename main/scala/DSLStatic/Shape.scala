package DSLStatic

import DSLStatic.Color.Color

abstract class Shape{
  var stroke : Stroke
  var color : Color
  var x : Int
  var y : Int
  var canvasyElement : CanvasyElement

  def stroke(col : Color.Value) : Unit
  def stroke(w : Int) : Unit
  def and(shape: Shape) : Shape
  def and(groups: Groups) : Groups
  def translateY(y : Int) : Unit
  def translateX(x : Int) : Unit
}
