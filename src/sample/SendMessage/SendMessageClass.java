package sample.SendMessage;

import java.io.Serializable;

import sample.Organization.Admin;
import sample.Organization.AlternativesForMarks;


import java.util.ArrayList;
public interface SendMessageClass extends Serializable {
    long serialVersionUID = 1L; //assign a long value


    int ADD_NEW_DETAIL =0;
    int DELETE_DETAIL =1;
    int SHOW_ALL_DETAIL =2;
    int UDDATE_DETAIL=10;


    int CHECK_ADMIN =5;
    int ADMIN_ADD_NEW=6;
    int ADMIN_SHOW_ALL=7;
    int ADMIN_DELETE=8;
    int ADMIN_CHANGE=9;

    ArrayList<Admin> getAdminList();
    Admin getAdmin();

    ArrayList<AlternativesForMarks> getAlternatives();
    AlternativesForMarks getAlternative();


    int getType();
}

