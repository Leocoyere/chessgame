interface PieceProps {
    symbol: string;
}

export default function Piece({ symbol }: PieceProps) {
    return (
        <img className="w-full" src={'/assets/pieces/' + symbol + ".svg"} alt="" />
    )
}