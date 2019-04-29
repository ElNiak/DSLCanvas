package DSLStatic

trait CanvasyElementModifier [ApplyOn <: CanvasyElement]{
  def change(x: ApplyOn): Unit

  def and[I <: CanvasyElementModifier[Shape]](modifier: I): Array[I] ={
    val array = new Array[I](2)
    array(this)
    array(modifier)
  }
}

