package DSLStatic

import DSLStatic.Color.Color

trait Shape{
  val stroke : Stroke
  val color : Color
  val x,y : Int
  val canevasyElementModifier : CanvasyElementModifier[Shape]

  def stroke(col : Color) : Unit
  def and(shape: Shape) : Groups
  def translateY(y : Int) : Unit
  def translateX(x : Int) : Unit
}
