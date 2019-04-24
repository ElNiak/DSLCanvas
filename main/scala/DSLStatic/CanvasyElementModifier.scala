package DSLStatic

trait CanvasyElementModifier[ApplyOn <: CanvasyElement] extends CanvasyElement {
  def change(x: ApplyOn): Unit
  def and[I <: CanvasyElementModifier[Shape]](shape:I): Array[I] = {
    val array = new Array[I](2)
    array(this)
    array(shape)
    return array
  }
}

