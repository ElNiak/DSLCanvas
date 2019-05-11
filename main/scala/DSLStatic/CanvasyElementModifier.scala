package DSLStatic

import DSLStatic.Shape.Shape

trait CanvasyElementModifier [ApplyOn <: Shape]{
  def change(x: ApplyOn): Unit
}

