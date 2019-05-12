package DSLPerso.Style

/**
  * Theses objects are used in order to limit wrong user input
  */

object SV  extends Enumeration {
  type SV = SValue
  case class SValue(sv: Int) extends Val(sv)
  val stroke : SV = SValue(2)
  val fill : SV = SValue(1)
  val clear : SV = SValue(3)
}

object Cap  extends Enumeration {
  type Cap = CapValue
  case class CapValue(cap: String) extends Val(cap)
  val butt = CapValue("butt")
  val round = CapValue("round")
  val square = CapValue("square")
}

object Join extends Enumeration {
  type Join = JoinValue
  case class JoinValue(jn: String) extends Val(jn)
  val round = JoinValue("round")
  val bevel = JoinValue("bevel")
  val miter = JoinValue("miter")
}

