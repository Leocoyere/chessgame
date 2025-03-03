import Piece from "./Piece";

export default function Pawn({ color }: { color: "black" | "white" }) {
  return <Piece symbol={color + "/pawn"} />;
}