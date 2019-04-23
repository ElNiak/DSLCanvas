package DSLStatic

case class Width(w: Double) extends CanevasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = x.width = w
}
