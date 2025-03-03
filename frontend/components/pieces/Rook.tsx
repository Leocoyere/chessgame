import Piece from "./Piece";

export default function Rook({ color }: { color: "black" | "white" }) {
  return <Piece symbol={color + "/rook"} />;
}