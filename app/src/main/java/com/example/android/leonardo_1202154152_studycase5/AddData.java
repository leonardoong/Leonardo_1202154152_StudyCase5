package com.example.android.leonardo_1202154152_studycase5;

/**
 * Created by Leonardo on 3/25/2018.
 */

//Membuat class AddData
public class AddData {
    //deklarasi variable
    String todo, desc, prior;

    //Constructor dari class AddData
    public AddData(String todo, String desc, String prior){
        this.todo=todo;
        this.desc=desc;
        this.prior=prior;
    }

    //method setter dan getter untuk to do , description dan priority
    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }
}
