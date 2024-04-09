package main;

import java.util.Scanner;

import member.MemberController;

public class Main {

	public static final Scanner SC = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		System.out.println("==== JDBC (MEMBER) ====");

		MemberController mc = new MemberController();

		try {

			mc.printMenu();

		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}