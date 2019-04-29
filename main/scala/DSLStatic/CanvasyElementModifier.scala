package DSLStatic

trait CanvasyElementModifier [ApplyOn <: CanvasyElement]{
  def change(x: ApplyOn): Unit

  def and(modifier: CanvasyElementModifier[Shape]): Array[CanvasyElementModifier[Shape]] ={
    val array = new Array[CanvasyElementModifier[Shape]](2)
    array:+ this
    array:+ modifier
  }
}

