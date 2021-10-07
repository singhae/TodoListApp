package com.todo.dao;

import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	private List<TodoItem> list;

	public TodoList() { //새로운 어레이 투두아이템의 형식으로 만드는 메소
		this.list = new ArrayList<TodoItem>();
	}

	public void addItem(TodoItem t) { //리스트에 티(내용)를 추가해주는 함
		list.add(t);
	}

	public void deleteItem(TodoItem t) { //리스트에 티를 제거해주는 함수
		list.remove(t);
	}

	void editItem(TodoItem t, TodoItem updated) { //
		int index = list.indexOf(t); 
		list.remove(index);
		list.add(updated);
	}

	public ArrayList<TodoItem> getList() {
		return new ArrayList<TodoItem>(list); //투두아이템형식의 list 용량의 어레이리스트 리턴하기 
	}

	public void sortByName() {
		Collections.sort(list, new TodoSortByName()); //정렬함수 리스o랑,comparator로 구현한 클래스의 인스턴스

	}

	public void listAll() { //모든 리스트불러오는 함수 
		System.out.println("\n"
				+ "inside list_All method\n");  //출력 리스트안에있는 모든 메소
		for (TodoItem myitem : list) {  //반복문 : 리스트에 있는 값을 myitem에 넣기 
			System.out.println(myitem.getTitle() + myitem.getDesc()); //타이틀함수 + 디스크립션 출
		}
	}
	
	public void reverseList() { 
		Collections.reverse(list);  //list안의 값을 역순으로
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate()); //comparator투두솔트바이데이트 객체로 정렬하는 함
	}

	public int indexOf(TodoItem t) { //전달된 인자가 리스트에 존재한다면 해당 객체의 인덱스를 리턴. 아니면 -1
		return list.indexOf(t);
	}

	public Boolean isDuplicate(String title) {   //
		for (TodoItem item : list) { //리스트를 아이템에 넣는 반복
			if (title.equals(item.getTitle())) return true; //만약 타이틀이 리스트에 있는 타이틀이랑 같으면 true 리
		}
		return false; // 같지 않으면 거짓 리턴 
	}
	
	public TodoItem getItem(int num) {
		return list.get(num);
	}

	
}
