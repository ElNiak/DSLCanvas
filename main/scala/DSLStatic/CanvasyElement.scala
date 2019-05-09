package DSLStatic

import DSLStatic.Shape.Shape
import scala.collection.mutable.ListBuffer

trait CanvasyElement {
  def and(x: CanvasyElementModifier[Shape]): Shape
  def and(x: Shape): ListBuffer[Shape]
  def change(x: CanvasyElementModifier[Shape]): Shape
}

object CanvasyElement {
  implicit class Group[I <: Shape](group: ListBuffer[I]) {
    def change[J <: CanvasyElementModifier[Shape]] (modifier: J): ListBuffer[I]  = {
      group foreach(_ change(modifier))
      group
    }

    def and[J <: CanvasyElementModifier[Shape]] (modifier: J): Unit = {
      group foreach(modifier change _)
    }

  }
}