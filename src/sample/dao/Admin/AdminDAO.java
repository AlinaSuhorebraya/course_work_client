package sample.dao.Admin;

import sample.Organization.AdminProperty;

public interface AdminDAO {
    void insertAdmin(AdminProperty adminProperty);

    void deleteAdmin(AdminProperty adminProperty);

    void changeAdmin(AdminProperty adminProperty);

    void getListFromServer();
}
