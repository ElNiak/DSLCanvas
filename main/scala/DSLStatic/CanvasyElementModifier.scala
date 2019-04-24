package DSLStatic

trait CanvasyElementModifier[ApplyOn <: CanvasyElement]  {
  def change(x: ApplyOn): Unit
}

