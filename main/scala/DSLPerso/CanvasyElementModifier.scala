package DSLPerso

import DSLPerso.Shape.Shape

trait CanvasyElementModifier [ApplyOn <: Shape]{
  def change(x: ApplyOn): Unit
}

