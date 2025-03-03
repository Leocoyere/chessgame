import Piece from "./Piece";

export default function Bishop({ color }: { color: "black" | "white" }) {
  return <Piece symbol={color + "/bishop"} />;
}