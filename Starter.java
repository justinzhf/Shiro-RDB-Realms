import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.PermissionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Starter {

    private static final transient Logger log = LoggerFactory.getLogger(Starter.class);

    public static void main(String[] args) {
        log.info("My First Apache Shiro Application");
        Factory<SecurityManager> factory = new IniSecurityManagerFactory();

        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject currentUser = SecurityUtils.getSubject();
        while (true) {
            Scanner sc = new Scanner(System.in);
            /*System.out.print("请输入用户名：");
            String usrname = sc.nextLine();

            System.out.print("请输入密码：");
            String psd = sc.nextLine();*/

            UsernamePasswordToken token = new UsernamePasswordToken("jyk", "jyk");

            try{
                currentUser.login(token);
            }catch (Exception e){
                System.out.println("用户名或密码错误！");
            }
            if(currentUser.isAuthenticated()){
                System.out.println("登录成功！");
                if(currentUser.isPermitted("2:2")){
                    System.out.println("您具有此权限");
                }else {
                    System.out.println("禁止访问！");
                }
                return;
            }
        }


    }
}