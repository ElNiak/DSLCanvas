package DSLStatic

case class StrokeWidth(w: Double) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = x.stroke.width = w
}
