package DSLStatic

trait CanvasyElement {
  def and(x: CanvasyElementModifier[Shape]) : Unit
  def and(x: Shape) : Array[Shape]
  def change (x: CanvasyElementModifier[Shape]): Shape
}


object CanvasyElement {
  implicit class Group[I <: CanvasyElement](group: Array[I]) {
    def and(groups: Array[I]): Array[I] = {
      val array = new Array[I](1 + groups.length)
      groups foreach (array(_))
      array(this)
      return array
    }

    def change[I <: CanvasyElementModifier[Shape]] (x: I): Shape  = {

    }
  }
}