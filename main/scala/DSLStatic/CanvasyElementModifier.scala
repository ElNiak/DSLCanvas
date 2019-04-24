package DSLStatic

abstract class CanvasyElementModifier[ApplyOn <: CanvasyElement] {
  def change(x: ApplyOn): Unit
}
