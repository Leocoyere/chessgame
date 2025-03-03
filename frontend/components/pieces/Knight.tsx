import Piece from "./Piece";

export default function Knight({ color }: { color: "black" | "white" }) {
  return <Piece symbol={color + "/knight"} />;
}