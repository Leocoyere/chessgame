import Piece from "./Piece";

export default function Queen({ color }: { color: "black" | "white" }) {
  return <Piece symbol={color + "/queen"} />;
}