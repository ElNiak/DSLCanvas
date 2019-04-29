package DSLStatic

trait CanvasyElement {
  def and(x: CanvasyElementModifier[Shape]): Unit

  def and(x: Shape): Array[Shape]

  def change(x: CanvasyElementModifier[Shape]): Shape
}


object CanvasyElement {
  implicit class Group[I <: Shape](group: Array[I]) {
//    def and(groups: Array[I]): Array[I] = {
//      val array = new Array[I](1 + groups.length)
//      groups foreach (array(_))
//      array(this)
//      return array
//    }

    def change[J <: CanvasyElementModifier[Shape]] (modifier: J): Array[I]  = {
      group foreach(_ change(modifier))
      group
    }

    def and[J <: CanvasyElementModifier[Shape]] (modifier: J): Unit = {
      group foreach(modifier change _)
    }

  }
}