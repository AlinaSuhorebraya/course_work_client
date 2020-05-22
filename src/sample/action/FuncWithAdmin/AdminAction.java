package sample.action.FuncWithAdmin;
import sample.Organization.AdminProperty;

public interface AdminAction
{
void addAdmin(AdminProperty adminProperty);
boolean isCheckResult();
void deleteAdmin(AdminProperty adminProperty);
}
