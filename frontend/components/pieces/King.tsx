import Piece from "./Piece";

export default function King({ color }: { color: "black" | "white" }) {
  return <Piece symbol={color + "/king"} />;
}