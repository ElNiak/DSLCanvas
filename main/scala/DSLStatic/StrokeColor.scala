package DSLStatic

case class StrokeColor(c: String) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = x.stroke.color = c

}

