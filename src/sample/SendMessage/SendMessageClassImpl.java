package sample.SendMessage;

import sample.Organization.Admin;
import sample.Organization.AlternativesForMarks;

import java.util.ArrayList;
import java.util.Objects;


public class SendMessageClassImpl implements SendMessageClass {
    static final long serialVersionUID = 1L; //assign a long value
    private int type;
    private int id;

    private ArrayList<Admin> adminList;
    private Admin admin;

    private ArrayList<AlternativesForMarks> alternatives;
    private AlternativesForMarks alternative;


    private String login;
    private String password;





    public SendMessageClassImpl(int type, String login, String password) {
        this.type = type;
        this.login = login;
        this.password = password;
    }


    public SendMessageClassImpl(int type) {
        this.type = type;
    }

    public SendMessageClassImpl(int type, int id) {
        this.type = type;
        this.id = id;
    }

    public SendMessageClassImpl(int type, AlternativesForMarks alternative) {
        this.type = type;
        this.alternative = alternative;
    }


    public SendMessageClassImpl(int type, ArrayList<AlternativesForMarks> alternatives) {
        this.type = type;
        this.alternatives = alternatives;
    }

    public SendMessageClassImpl(ArrayList<Admin> adminList, int type) {
        super();
        this.adminList = adminList;
        this.type=type;
    }

    public SendMessageClassImpl(Admin admin, int type){
        super();
        this.admin=admin;
        this.type=type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public ArrayList<Admin> getAdminList() {
        return adminList;
    }

    public void setAdminList(ArrayList<Admin> adminList) {
        this.adminList = adminList;
    }

    @Override
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setAlternatives(ArrayList<AlternativesForMarks> alternatives) {
        this.alternatives = alternatives;
    }

    public void setAlternative(AlternativesForMarks alternative) {
        this.alternative = alternative;
    }





    @Override
    public ArrayList<AlternativesForMarks> getAlternatives() {
        return alternatives;
    }

    @Override
    public AlternativesForMarks getAlternative() {
        return alternative;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendMessageClassImpl message = (SendMessageClassImpl) o;
        return type == message.type &&
                id == message.id &&
                Objects.equals(adminList, message.adminList) &&
                Objects.equals(admin, message.admin) &&
                Objects.equals(alternatives, message.alternatives) &&
                Objects.equals(alternative, message.alternative) &&
                Objects.equals(login, message.login) &&
                Objects.equals(password, message.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, id, adminList, admin, alternatives, alternative, login, password);
    }
}
