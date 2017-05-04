import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by zhf on 2017/1/5.
 */
public class MyRealms extends AuthorizingRealm {
    private  UserDAO userDAO=new UserDAO();
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        ArrayList<String> pers=userDAO.getPermissions(principalCollection.getPrimaryPrincipal().toString());
        info.addStringPermissions(pers);
        Iterator iterator=pers.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String psd=userDAO.getPassword(authenticationToken.getPrincipal().toString());
        if(psd==null){
            throw new AuthenticationException();
        }
     //   System.out.println("in realms:"+psd);
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(authenticationToken.getPrincipal(),
                psd,"MyRealms");
        return info;
    }
}
