"use client"

import { JSX, useState } from 'react';
import Queen from './pieces/Queen';
import King from './pieces/King';
import Bishop from './pieces/Bishop';
import Knight from './pieces/Knight';
import Rook from './pieces/Rook';
import Pawn from './pieces/Pawn';

const initialBoard = [
    ['bR', 'bN', 'bB', 'bQ', 'bK', 'bB', 'bN', 'bR'],
    ['bP', 'bP', 'bP', 'bP', 'bP', 'bP', 'bP', 'bP'],
    ['--', '--', '--', '--', '--', '--', '--', '--'],
    ['--', '--', '--', '--', '--', '--', '--', '--'],
    ['--', '--', '--', '--', '--', '--', '--', '--'],
    ['--', '--', '--', '--', '--', '--', '--', '--'],
    ['wP', 'wP', 'wP', 'wP', 'wP', 'wP', 'wP', 'wP'],
    ['wR', 'wN', 'wB', 'wQ', 'wK', 'wB', 'wN', 'wR'],
  ];
  
  type PieceKey = 'bP' | 'wP' | 'bR' | 'wR' | 'bN' | 'wN' | 'bB' | 'wB' | 'bQ' | 'wQ' | 'bK' | 'wK' | '--';

  const pieceSymbols: Record<PieceKey, JSX.Element> = {
    bP: Pawn({color: "white"}), wP: Pawn({color: "white"}),
    bR: Rook({color: "black"}), wR: Rook({color: "white"}),
    bN: Knight({color: "black"}), wN: Knight({color: "white"}),
    bB: Bishop({color: "black"}), wB: Bishop({color: "white"}),
    bQ: Queen({color: "black"}), wQ: Queen({color: "white"}),
    bK: King({color: "black"}), wK: King({color: "white"}),
    '--': <span></span>
  };
  
  
  export default function ChessBoard() {
    const [board, setBoard] = useState(initialBoard);
  
    return (
      <div className="grid grid-cols-8 grid-rows-8 gap-0 w-96 h-96 border-2 border-gray-500">
        {board.map((row, rowIndex) =>
          row.map((cell, colIndex) => {
            const isDarkSquare = (rowIndex + colIndex) % 2 === 0;
            return (
              <div
                key={`${rowIndex}-${colIndex}`}
                className={`w-full h-full ${isDarkSquare ? 'bg-gray-700' : 'bg-white'} flex items-center justify-center`}
              >
                <p className="text-3xl">{pieceSymbols[cell]}</p> {/* Afficher le symbole de la pièce */}
              </div>
            );
          })
        )}
      </div>
    );
  }
  