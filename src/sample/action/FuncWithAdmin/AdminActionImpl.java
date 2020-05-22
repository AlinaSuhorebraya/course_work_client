package sample.action.FuncWithAdmin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.Admin.AdminDAO;
import sample.Organization.Admin;
import sample.Organization.AdminProperty;

import java.util.ArrayList;
import java.util.Iterator;

public class AdminActionImpl implements AdminAction
{
    private  static ObservableList<AdminProperty> adminPropertyList = FXCollections.observableArrayList();
    private static ArrayList<Admin> adminArrayList = new ArrayList<Admin>();
    private final String log ="admin";
    private final String pass="admin";

    private AdminDAO adminDAO;
    private  static boolean checkResult;

    public AdminActionImpl(AdminDAO adminDAO){
        this.adminDAO=adminDAO;
        this.adminDAO.getListFromServer();
    }
    @Override
    public boolean isCheckResult() {
        return checkResult;
    }

    public static void setCheckResult(boolean info) {
        checkResult = info;
    }

    @Override
    public void addAdmin(AdminProperty adminProperty)
    {
        if(!(adminProperty.getLogin().equals("")&&
                adminProperty.getPassword().equals("")&&
                adminProperty.getName().equals("")&&
                adminProperty.getSurname().equals("")
        )){
            adminPropertyList.add(adminProperty);
            adminDAO.insertAdmin(adminProperty);//sql
        }
    }
    public ObservableList<AdminProperty> getAdminList(){
        return this.adminPropertyList;
    }

    public static ArrayList<Admin> getAdminArrayList() {
        return adminArrayList;
    }

    public static void setAdminPropertyList(ArrayList<Admin> list){
        Iterator<Admin> iter = list.iterator();
        adminPropertyList.clear();
        adminArrayList.clear();
        adminArrayList=list;
        while(iter.hasNext())
        {
            Admin adminnew= iter.next();

            AdminProperty adminProperty = new AdminProperty();
            adminProperty.setLogin(adminnew.getLogin());
            adminProperty.setPassword(adminnew.getPassword());
            adminProperty.setName(adminnew.getName());
            adminProperty.setSurname(adminnew.getSurname());
            adminPropertyList.add(adminProperty);
        }
    }
    @Override
    public void deleteAdmin(AdminProperty adminProperty) {
        adminPropertyList.remove(adminProperty);
        adminDAO.deleteAdmin(adminProperty);
    }
    public void fillTestData(){
     //    adminPropertyList.add(new AdminProperty("1","1","1","1"));
        //  adminPropertyList.add(new AdminProperty("2","2","2","2"));
    }

}
