package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static List<Article> articles;

	static {
		articles = new ArrayList<>();
	}

	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		makeTestDate();

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어 ) ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (command.equals("system exit")) {
				break;
			}

			if (command.equals("article write")) {
				int id = articles.size() + 1;
				String regDate = Util.getNowDateStr();

				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, title, body);
				articles.add(article);

				System.out.printf("%d번글이 생성되었습니다\n", id);

			} else if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
					continue;
				}
				// 보완 필요
				System.out.println("번호    날짜     제목       조회");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					// 보완 필요
					System.out.printf("%d  %s     %s    %d\n", article.id, article.regDate, article.title, article.hit);
				}

			} else if (command.startsWith("article detail ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				// 메서드 실행 누락했었음.ㅡㅡ;;
				foundArticle.increaseHit();

				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("날짜 : %s\n", foundArticle.regDate);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("조회 : %d\n", foundArticle.hit);

			}

			else if (command.startsWith("article modity ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				foundArticle.title = title;
				foundArticle.body = body;
				System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
			}
			// 삭제 코드에서는 인덱스를 손봐야 한다. 인덱스는 실제 번호보다 -1 해줘야 하는게 키포인트
			else if (command.startsWith("article delete ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				int foundIndex = -1;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundIndex = i;
						break;
					}
				}
				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				articles.remove(foundIndex);

				System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);

			}

			else {
				System.out.println("존재하지 않는 명령어 입니다");
			}
		}

		sc.close();

		System.out.println("==프로그램 끝==");
	}
	// 테스트 케이스 부분 // add로 자료를 불러온다. 실행되자마자 인자 5개를 불러온다.
	private static void makeTestDate() {
		System.out.println("테스트를 위한 ~~~~");
		articles.add(new Article(1, Util.getNowDateStr(), "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getNowDateStr(), "제목2", "내용2", 61));
		articles.add(new Article(3, Util.getNowDateStr(), "제목3", "내용3", 22));
		articles.add(new Article(4, Util.getNowDateStr(), "제목4", "내용4", 45));
		articles.add(new Article(5, Util.getNowDateStr(), "제목5", "내용5", 42));

	}
}

class Article {
	int id;
	int hit;
	String regDate;
	String title;
	String body;

	// 44번째 줄을 수정하기 싫기에 이 부분을 보완 // this를 한번에 작성 // 위코드중 인자가 4개짜리를 불러와서 this로 5개를 실행
	public Article(int id, String regDate, String title, String body) {
		this(id, title, body, regDate, 0);
	}

	// 인자 5개짜리를 불러와서 실행
	public Article(int id, String regDate, String title, String body, int hit) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.hit = hit;
	}

	// 조회수 1씩 증가 시키는 메소드 // 위 코드중 detail을 할 때마다 증가하도록 작성해야 하기에 해당 코드 내에 실행함수 추가
	public void increaseHit() {
		hit++;
	}
}