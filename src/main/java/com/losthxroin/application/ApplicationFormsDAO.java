package com.losthxroin.application;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Component
public class ApplicationFormsDAO {
    private static int  APPLICATION_FORM_ID;
    private List<ApplicationForm> db;


    public ApplicationFormsDAO(){
        db = new ArrayList<>();
        Date date = new Date();
        Date startDate1 = new Date(date.getTime()-1000 * 60 * 60 * 24*8);
        String strDate1 = startDate1.getDate() + "."+(startDate1.getMonth()+1)+"."+(startDate1.getYear()+1900);
        Date startDate2 = new Date(date.getTime()-1000 * 60 * 60 * 24*4);
        String strDate2 = startDate2.getDate() + "."+(startDate2.getMonth()+1)+"."+(startDate2.getYear()+1900);
        db.add( new ApplicationForm(++APPLICATION_FORM_ID,strDate1,"телефон","не работает","крутой был","Ярик","В ожидании","Игорь"));
        db.add( new ApplicationForm(++APPLICATION_FORM_ID,strDate2,"наушники","не работают","крутые был","Мелисса","В ожидании","Миша"));
        db.get(0).setStartDate(startDate1);
        db.get(1).setStartDate(startDate2);
    }
    /**
     * CRUD
     **/
    public void create(ApplicationForm applicationForm){
        applicationForm.setId(++APPLICATION_FORM_ID);
        db.add(applicationForm);
    }
    public List<ApplicationForm> readAll(){
        return db;
    }
    public ApplicationForm read (int id){
        for (int i = 0; i < db.size();i++){
            if (db.get(i).getId() == id ) return db.get(i);
        }
        return null;
    }
    public void update (ApplicationForm applicationForm, int id){
        for (int i = 0; i < db.size();i++){
            if (db.get(i).getId() == id ){
                applicationForm.setDate(db.get(i).getDate());
                db.set(i, applicationForm);
            }
        }
    }
    public void delete (int id){
        for (int i = 0; i < db.size();i++){
            if (db.get(i).getId() == id ) db.remove(id);
        }
    }
    /**
     * Статистика
     * */
    public List<ApplicationForm> completedOrders(){
        List<ApplicationForm> completedOrders = new ArrayList<>();
        for (int i = 0; i < db.size(); i++) {
            if (db.get(i).getStatus().equals("Выполнено")){
                completedOrders.add(db.get(i));
            }
        }
        return completedOrders;
    }
    public int averageOrderCompletionTime(){
        List<ApplicationForm> completedOrders = completedOrders();
        int count = completedOrders().size();
        long timeInDay = 0;
        for (int i = 0; i < completedOrders.size(); i++) {
            timeInDay += (completedOrders.get(i).getCompletionDate().getTime()-completedOrders.get(i).getStartDate().getTime())/1000/60/60/24;
        }
        if (count ==0||timeInDay==0){
            return 0;
        }
        int res = (int) (timeInDay/count);

        return res;
    }
    public String frequentMalfunctions(){
        int[] sos = new int[db.size()];
        for (int i = 0; i < db.size(); i++) {
            for (int j = 0; j < db.size(); j++) {
                if (db.get(i).getTypeOfFault().equals(db.get(j).getTypeOfFault())){
                    sos[i]++;
                }
            }
        }
        int m = 0;
        for (int i = 0; i < sos.length; i++) {
            if (sos[i]>m){
                m = i;
            }
        }
        return db.get(m).getTypeOfFault();
    }
}
