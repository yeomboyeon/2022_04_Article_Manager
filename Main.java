import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;

		List<Article> articles = new ArrayList<>();

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
				int id = lastArticleId + 1;
				lastArticleId = id;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, title, body);
				articles.add(article);

				System.out.printf("%d번글이 생성되었습니다\n", id);

			} else if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
					continue;
				}
				System.out.println("번호    |  제목");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);

					System.out.printf("%d  	 |  %s\n", article.id, article.title);
					System.out.printf("%d  	 |  %s\n", article.id, article.title);

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

				System.out.printf("%d번 게시물은 존재합니다.\n", id);
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("날짜 : 2022-12-12 12:12:12\n");
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);

			} else if (command.startsWith("article mod ")) {

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
				foundArticle.body = body;
				foundArticle.title = title;
				System.out.printf("%d번 게시물을 수정했습니다.\n", id);

			} else if (command.startsWith("article delete ")) {
				// split 함수를 적용함으로써 문자열을 쪼갠다. 쪼개는 것은 " "을 기준으로 쪼갠다
				String[] commandBits = command.split(" ");
				// 인덱스 번호 [2] 에 있는 번호를 id 변수에 저장한다
				int id = Integer.parseInt(commandBits[2]);
				// 인덱스 번호를 찾아서 실제 작성된 번호와 인덱스 번호를 일치시키도록 한다
				// -1은 0을 하게 되면 실제 시작되는 인덱스 번호 0번을 저장하는거기 때문에 오류가 발생
				// -1을 하게 됨으로써 0부터 찾을 수 있도록 한다
				int foundIndex = -1;
				// article size 실제 작성된 게시물 갯수를 기준으로 반복
				for (int i = 0; i < articles.size(); i++) {
					// article get로 찾아 article에 저장
					Article article = articles.get(i);

					if (article.id == id) {
						foundIndex = i;
						break;
					}
				}
				// 인덱스 번호 -1과 같다면 존재하지 않는다고 출력
				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				// 삭제 함수 적용
				articles.remove(foundIndex);
				System.out.printf("%d번 게시물을 삭제했습니다.\n", id);

			} else {
				System.out.println("존재하지 않는 명령어 입니다");
			}
		}

		sc.close();

		System.out.println("==프로그램 끝==");
	}
}

class Article {
	int id;
	String title;
	String body;

	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}
}