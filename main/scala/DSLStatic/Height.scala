package DSLStatic

import java.awt.Rectangle

case class Height(h: Double) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = x.height = h

}

