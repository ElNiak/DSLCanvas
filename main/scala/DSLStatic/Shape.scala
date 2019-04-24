package DSLStatic


trait Shape {
  var stroke: Stroke
  var color: Color
  var x: Int
  var y: Int
  var canvasyElement: CanvasyElement
  var size : Int


  def stroke(col: Color.Value): Unit

  def stroke(w: Int): Unit

  def size(s: Int): Unit = {
    size = s
  }

  def and(shape: Shape): Array[Shape] = {
    val array = new Array[Shape](2)
    array(this)
    array(shape)
    return array
  }

  def and(groups: Array[Shape]): Array[Shape] = {
    val array = new Array[Shape](1 + groups.length)
    groups foreach (array(_))
    array(this)
    return array
  }

  def translateY(Y: Int): Unit = {
    y += Y
  }

  def translateX(X: Int): Unit = {
    x += X
  }
}


object Shape {
  def and(shape: Shape): Array[Shape] = {

  }

  implicit class Group(group: Array[Shape]) {

    def translateY(Y: Int) : Array[Shape] = {
      group foreach(_ translateY(Y))
      return group
    }
    def translateX(X: Int) : Array[Shape] = {
      group foreach(_ translateX(X))
      return group
    }
  }

  implicit class Group[ApplyOn <: Shape](group: Array[Shape]) {

    def translateY[ApplyOn <: Shape](Y: Int) : Array[Shape] = {
      group foreach(_ translateY(Y))
      return group
    }
    def translateX[ApplyOn <: Shape](X: Int) : Array[Shape] = {
      group foreach(_ translateX(X))
      return group
    }
  }

  implicit class Group(group: Array[Rectangle]) {

    def translateY(Y: Int) : Array[Rectangle] = {
      group foreach(_ translateY(Y))
      return group
    }
    def translateX(X: Int) : Array[Rectangle] = {
      group foreach(_ translateX(X))
      return group
    }
  }

  def change(shape: Shape) = {

  }

  def change(canvasyElement: CanvasyElement) = {

  }
}
