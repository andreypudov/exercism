module QueenAttack

let create (position: int * int) =
  match position with
  | (x, y) when 0 <= x && x <= 7 && 0 <= y && y <= 7 -> true
  | _ -> false

let canAttackByRow (queen1: int * int) (queen2: int * int) =
  let (queen1X, _) = queen1
  let (queen2X, _) = queen2
  queen1X = queen2X

let canAttackByColumn (queen1: int * int) (queen2: int * int) =
  let (_, queen1Y) = queen1
  let (_, queen2Y) = queen2
  queen1Y = queen2Y

let canAttackByDiagonal (queen1: int * int) (queen2: int * int) =
  let (queen1X, queen1Y) = queen1
  let (queen2X, queen2Y) = queen2
  abs (queen1X - queen2X) = abs (queen1Y - queen2Y)

let canAttack (queen1: int * int) (queen2: int * int) =
  create queen1 &&
  create queen2 &&
  (canAttackByRow queen1 queen2 ||
  canAttackByColumn queen1 queen2 ||
  canAttackByDiagonal queen1 queen2)
