package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem {
    private String title;
    private String desc;
    private String current_date;


    public TodoItem(String title, String desc){ //스트링타입의 인자를 갖는 메소드 
        this.title=title; //클래스의 속성을 고대로 사용할때 this. 붙여준다 
        this.desc=desc;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date=f.format(new Date());
        
       
    }
    
    public String getTitle() {  
        return title;
    }

    public void setTitle(String title) { 
        this.title = title; 
    }

    public String getDesc() { 
        return desc;
    }

    public void setDesc(String desc) { 
        this.desc = desc;
    }

    public String getCurrent_date() { 
    	current_date.toString();
    	return current_date;
        
    }

    public void setCurrent_date(String current_date) {  
        this.current_date = current_date;
    }
    
    public String toSaveString() {
    	return title + "##" + desc + "##" + current_date + "\n";
 
    }
   
}
