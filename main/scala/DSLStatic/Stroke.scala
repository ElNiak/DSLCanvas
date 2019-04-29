package DSLStatic

import ColorRGB.ColorRGBUtils

class Stroke() {
  // Default value for an object stroke ?
  var width : Double = -1
  var color : String = ""

  def apply(w: Double): Unit = {
    width = w
  }

  def apply(c: String): Unit = {
    color = c
  }
}



