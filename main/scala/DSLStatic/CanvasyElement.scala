package DSLStatic

trait CanvasyElement  {
  def and(x: CanvasyElement) : Array[CanvasyElement] = {
    val array = new Array[CanvasyElement](2)
    array(this)
    array(x)
    return array
  }
  def change(x: CanvasyElement): Unit
}


object CanvasyElement {
  implicit class Group[I <: CanvasyElement](group: Array[I]) {
    def and(groups: Array[I]): Array[I] = {
      val array = new Array[I](1 + groups.length)
      groups foreach (array(_))
      array(this)
      return array
    }

    def change(element: CanvasyElementModifier[Shape]): Unit = {

    }
  }
}