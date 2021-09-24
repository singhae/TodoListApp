package com.todo.service;

import java.util.*;
import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;
import java.io.File;
import java.io.FileNotFoundException;
//import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.util.StringTokenizer;
import java.io.FileOutputStream;
import java.io.IOException;



public class TodoUtil {
	
	private static final char[] String = null;

	public static void createItem(TodoList list) { // ㅇ아이템 만드는 함수 
		
		String title, desc;  //타이틀 디스크립션 선언 
		Scanner sc = new Scanner(System.in); // 입력 받고 
		
		System.out.println("\n" //출력 
				+ "[항목추가]\n" 
				+ "제목을 입력하십시오 > ");
		
		title = sc.next(); //타이틀에 스트링 입력 
		
		if (list.isDuplicate(title)) { //만약 타이틀을 이즈듀플리케이트 함수에서 실행 된다면 
			System.out.printf("제목이 중복됩니다! "); // 타이틀은 복사되지못한다는 출력 
			return;
		}
		sc.nextLine();
		System.out.println("내용을 입력하십시오 > "); // 디스크립션을 쳐라. 출력 
		desc = sc.nextLine().trim(); //받은거 디스크립에 넣기 
		
		TodoItem t = new TodoItem(title, desc);  //투두아이템 타입의 티가 투두아이템 메소드 생성 
		list.addItem(t); //리스트 에드아이템 메소드에 티 값을 실행..?
		System.out.println("추가되었습니다");
	}

	public static void deleteItem(TodoList l) {  //아이템 지우는함수 
		
		Scanner sc = new Scanner(System.in); //입력 
		//String title = sc.next(); // 타이틀에 입력 
		
		System.out.println("[항목삭제]\n"
				+"삭제할 항목의 제목을 입력하시오 > ");
		String title = sc.next();
		
		for (TodoItem item : l.getList()) { //겟리스트 메소드로 생성된 리스트 엘을 
			if (title.equals(item.getTitle())) { //만약 겟타이틀메소드 아이템이 엘의 타이틀과 일치 한다면 
				l.deleteItem(item);
				System.out.println("삭제되었습니다.");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) { //업데이트아이템 메소드 
		
		Scanner sc = new Scanner(System.in); //입력 
		
		System.out.println("[항목수정]\n"
				+ "수정할 항목의 제목을 입력하시오 > ");
		String title = sc.next().trim(); // 타이틀에 입력 
		if (!l.isDuplicate(title)) { //만약 이 메소드가 아니라면 
			System.out.println("없는 제목입니다."); // 출력 
			return;
		}

		System.out.println("새 제목을 입력하시오 > ");  //출력 
		String new_title = sc.next().trim(); //뉴타이틀 입력받기 
		if (l.isDuplicate(new_title)) { //만약 뉴타이틀이 이 메소드를 성립못시키면 
			System.out.println("제목이 중복됩니다."); //출력 
			return;
		}
		sc.nextLine();
		System.out.println("새로운 내용을 입력하십시오. > "); //출력 
		String new_description = sc.next().trim();  // 뉴디스크립션에넣고 
		for (TodoItem item : l.getList()) { //겟리스트를 아이템에 넣는 반복문 
			if (item.getTitle().equals(title)) { //겟타이틀에 있는 아이템이랑 받은 타이틀이랑 일치하면..
				l.deleteItem(item); //아이템을 지우는 메소드를 실행 
				TodoItem t = new TodoItem(new_title, new_description); //투두아이템 메소드를 티에 입력 
				l.addItem(t); // 티를에드아이템메소드에 넣고 
				System.out.println("업데이트 되었습니다."); //출력 
			}
		}

	}

	public static void listAll(TodoList l) { //리스트 보여주는 메소드 
		int i = 0;
		for (TodoItem item : l.getList()) { 
			//System.out.println(item.toString());
			i++;
		}
		System.out.println("[전체목록, 총" + i + "개");
		for (TodoItem item : l.getList()) { 
			System.out.println(item.toString());
		}
		
	}
	public static void saveList(TodoList l, String filename) {
		
			try {
				FileWriter fw = new FileWriter(filename);
				for (TodoItem item : l.getList()) { 
					fw.write(item.toSaveString()); //왜 이렇게 나오는데 
				}

				fw.close();
				
				System.out.println("파일에 저장되었습니다.");
				
			}catch (IOException e) {
				e.printStackTrace();
			}

			//System.out.print("받아온 자료 저장하기 ");
			
	}
	
	public static void loadList(TodoList l,String filename){
		
		  try {
			  BufferedReader reader = new BufferedReader(new FileReader("todolist.txt"));
			  String str;
			  //System.out.println(str);
			  int i = 0;
			  while((str=reader.readLine())!=null){
				  StringTokenizer st = new StringTokenizer(str, "##");  // ##이 나오는 곳 까지 자른다 .
				  String title = st.nextToken();
				  String description = st.nextToken();
				  String current_date = st.nextToken();
				  TodoItem item = new TodoItem(title, description);
				  item.setCurrent_date(current_date);
				  l.addItem(item);
				  i++;
				  //System.out.println(str);
				  
			  }
			 reader.close();
			 //System.out.println(i);
			 System.out.println(i+"개의 정보 읽기 완료");
		  	}catch (FileNotFoundException e) {
		  		System.out.println(filename + "파일이없습니다.");
				//e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		  
		 }

	@Override
	public String toString() {
		return "TodoUtil [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	
		
}
