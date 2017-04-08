package com.game;


import com.utils.FileUtils;

public class Main {

	public static void main(String[] args) {
		String tabuleiro = "/home/math/pm/PM/TP1/Reference/testes-alunos/entrada/tabuleiro.txt";
		String jogadas = "/home/math/pm/PM/TP1/Reference/testes-alunos/entrada/jogadas.txt";

		FileUtils handler = new FileUtils(tabuleiro, jogadas);
		Monopoly monopoly = new Monopoly(handler);

		monopoly.runGame(handler);


	}
}