package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in); 
		TodoList l = new TodoList();  
		boolean isList = false; 
		boolean quit = false;  
		
		Menu.displaymenu();
		
		do { 
			Menu.prompt(); 
			isList = false; 
			String choice = sc.next(); 
			
			switch (choice) { 

			case "add": 
				TodoUtil.createItem(l);  
				TodoUtil.loadList(l, "todolist.txt");
				break; 
			
			case "del": 
				TodoUtil.deleteItem(l); 
				break;
				
			case "edit":
				TodoUtil.updateItem(l);  
				break;
				
			case "ls":
				TodoUtil.listAll(l); 
				
				break;

			case "ls_name_asc": 
				l.sortByName(); 
				isList = true;
				break; 

			case "ls_name_desc": 
				l.sortByName(); 
				l.reverseList();
				isList = true; 
				break; 
				
			case "ls_date": 
				l.sortByDate(); 
				System.out.println("날짜순으로 정렬합니다."); //추가 
				isList = true; 
				break;
				
			case "help":
				Menu.displaymenu();
				break;

			case "exit": 
				quit = true; 
				TodoUtil.saveList(l,"todolist.txt");
				break; 

			default: 
				System.out.println("정확한 명령어를 입력하십시오. (도움말 : help) ");  //추
				break;
			}
			
			if(isList) TodoUtil.listAll(l); //추가 
		} while (!quit);
	}
}
