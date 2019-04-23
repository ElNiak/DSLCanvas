package DSLStatic

abstract class CanvasyElementModifier[ApplyOn <: CanvasyElement] {
  def change(x: ApplyOn): Unit
}

object CanvasyElementModifier {
  def and(canvasyElementModifier: CanvasyElementModifier[Shape]) = {

  }
}
