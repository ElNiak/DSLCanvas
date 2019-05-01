package DSLStatic

import DSLStatic.Shape.Shape

trait CanvasyElement {
  def and(x: CanvasyElementModifier[Shape]): Shape
  def and(x: Shape): Array[Shape]
  def change(x: CanvasyElementModifier[Shape]): Shape
}


object CanvasyElement {
  implicit class Group[I <: Shape](group: Array[I]) {
    def change[J <: CanvasyElementModifier[Shape]] (modifier: J): Array[I]  = {
      group foreach(_ change(modifier))
      group
    }

    def and[J <: CanvasyElementModifier[Shape]] (modifier: J): Unit = {
      group foreach(modifier change _)
    }

  }
}