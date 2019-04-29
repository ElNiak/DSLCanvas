package DSLStatic

import DSLStatic.Color.Color

class Stroke {
  // Default value for an object stroke ?
  var width : Double = 10
  var color : String = Color.red

  def apply(w: Double): Unit = {
    width = w
  }

  def apply(c: String): Unit = {
    color = c
  }

}



