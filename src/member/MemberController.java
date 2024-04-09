package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.Main;

public class MemberController {
	//JDBC 드라이버 로딩
	//데이터베이스 연결 정보 준비
	//데이터베이스 연결 == 커넥션 객체 얻기
	//SQL 준비
	//SQL 실행을 위한 statement 준비
	//statement 에 SQL 담아주고 실행 및 결과 리턴받기
	//결과 출력
	//사용한 자원 반납
	//~~~~~
	public void printMenu() throws Exception {
	System.out.println("---MEMBER---");
	System.out.println("1.회원가입");
	System.out.println("2.로그인");
	
	System.out.println("번호를 입력하세요:");
	String input = Main.SC.nextLine();
	
	switch (input) {
	case "1" :
		join();
		break;
	case "2" :
		login();
		break;
		default :
			System.out.println("잘못입력하셨습니다.");
	}
	}
	private void join() throws Exception {
		System.out.println("---회원가입---");
		Connection conn =getConn();
		
		System.out.println("ID :");
		String inputId =Main.SC.nextLine();
		System.out.println("PWD :");
		String inputPwd = Main.SC.nextLine();
		System.out.println("NICK :");
		String inputNick =Main.SC.nextLine();
		
		//sql 준비 
		String sql ="INSERT INTO MEMBER(ID,PWD,NICK) VALUES (?,?,?) ";
		//SQL 실행을 위한 STATEMENT 준비
		PreparedStatement pstmt =conn.prepareStatement(sql);
		pstmt.setString(1, inputId);
		pstmt.setString(2, inputPwd);
		pstmt.setString(3, inputNick);
		//STATEMENT 에 SQL 담아주고 실행 및 결과 리턴받기 
		int result =pstmt.executeUpdate();
		//결과 출력 
		if (result != 1) {
			throw new RuntimeException("회원가입 실패");
	}
		System.out.println("회원가입 성공");
	}

		private void login() throws Exception{
			System.out.println("----로그인-----");
			Connection conn =getConn();
			
			//sql 준비 
			String sql ="SELECT *FROM MEMBER WHERE ID =? AND PWD =?";
			System.out.println("ID :");
			String inputId =Main.SC.nextLine();
			System.out.println("PWD :");
			String inputPwd =Main.SC.nextLine();
			//sql 생성을 위한 스테이트먼트 준비
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, inputId);
			pstmt.setString(2, inputPwd);
			
			//statement 에 sql 담아주고 실행 및 결과 리턴받기 
			ResultSet rs =pstmt.executeQuery();
			//결과 출력 
			if(rs.next()) {
				String dbId =rs.getString("ID");
				String dbPwd =rs.getString("PWD");
				String dbNick =rs.getString("NICK");
				System.out.println("로그인 성공");
				MemberVo vo=new MemberVo(dbId,dbPwd,dbNick);
				System.out.println(vo);
			}else {
				System.out.println("로그인 실패,,,");
			}
		}
		
		
	private Connection getConn() throws Exception {
		//jdbc 드라이버 로딩 
		String driver ="oracle.jdbc.driver.OracleDriver";
		Class.forName(driver);
		//데이터베이스 연결 정보 준비 
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "C##KH";
		String pwd ="1234";
		
		//데이터베이스 연결 == 커넥션 객체 얻기
		Connection conn = DriverManager.getConnection(url,id,pwd);
		
		return conn;
	}
}
	