module Pangram

let isPangram (input: string): bool =
  input
  |> String.map System.Char.ToUpper
  |> String.filter System.Char.IsLetter
  |> Seq.toList
  |> Seq.distinct
  |> Seq.length = 26