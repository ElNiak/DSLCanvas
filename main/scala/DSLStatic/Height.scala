package DSLStatic

case class Height(h: Double) extends CanevasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = x.height = h
}

